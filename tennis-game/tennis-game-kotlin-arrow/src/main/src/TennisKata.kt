import arrow.optics.Lens
import arrow.optics.PLens

sealed class Score
object Love : Score()
object Fifteen : Score()
object Thirty : Score()
object Forty : Score()
object Advantage : Score()
object Wins : Score()

data class Game(
        val player1: Player,
        val player2: Player
)

data class Player(
        val score: Score
)

val gamePlayer1: Lens<Game, Player> = Lens(
        get = { match -> match.player1 },
        set = { match, player ->  match.copy(player1 = player)}
)

val gamePlayer2: Lens<Game, Player> = Lens(
        get = { match -> match.player1 },
        set = { match, player ->  match.copy(player1 = player)}
)

val playerScore: Lens<Player, Score> = Lens(
        get = { player -> player.score },
        set = { player, score -> player.copy(score = score) }
)

val gamePlayer1Score: PLens<Game, Game, Score, Score> = gamePlayer1.compose(playerScore)

val gamePlayer2Score: PLens<Game, Game, Score, Score> = gamePlayer2.compose(playerScore)

val nextPoint: (currentScore: Score) -> Score = { score ->
    when (score) {
        Love -> Fifteen
        Fifteen -> Thirty
        Thirty -> Forty
        Forty -> Advantage
        Advantage -> Wins
        Wins -> Wins
    }
}

val play: (game: Game, scoringPlayerScore: PLens<Game, Game, Score, Score>, opponentPlayerScore: PLens<Game, Game, Score, Score>) -> Game
        = { game, scoringPlayerScore, opponentPlayerScore ->
    val nextScoreScoringPlayer = nextPoint(scoringPlayerScore.get(game))
    val currentScoreOpponentPlayer = opponentPlayerScore.get(game)
    when {
        nextScoreScoringPlayer == Advantage && currentScoreOpponentPlayer == Advantage -> {
            val gameWithUpdatedScoreForScoringPlayer = scoringPlayerScore.set(game, Forty)
            opponentPlayerScore.set(gameWithUpdatedScoreForScoringPlayer, Forty)
        }
        else -> scoringPlayerScore.set(game, nextScoreScoringPlayer)
    }
}

val player1Plays: (Game) -> Game = { game -> play(game, gamePlayer1Score, gamePlayer2Score)}

val player2Plays: (Game) -> Game = { game -> play(game, gamePlayer2Score, gamePlayer1Score)}

val getGameScore: (Game) -> String = {game ->
    val gamePlayer1Score = gamePlayer1Score.get(game)
    val gamePlayer2Score = gamePlayer2Score.get(game)
    when {
        gamePlayer1Score == Forty && gamePlayer2Score == Forty -> "Deuce"
        gamePlayer1Score == Wins -> "Player 1 wins"
        gamePlayer2Score == Wins -> "Player 2 wins"
        else -> "$gamePlayer1Score - $gamePlayer2Score"
    }
}