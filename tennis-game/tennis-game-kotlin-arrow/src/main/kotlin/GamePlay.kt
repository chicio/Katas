import GameInteraction.readPlayer
import GameInteraction.showGameScore
import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.optics.Lens

fun playTennisGame(game: Game): IO<Game> =
    IO.fx {
        val playerScoring = !readPlayer()

        val updatedGame = trackScoredPoint(playerScoring, game)

        !showGameScore(updatedGame)

        when {
            gameCompleted(updatedGame) -> updatedGame
            else -> !playTennisGame(updatedGame)
        }
    }

val trackScoredPoint: (ScoringPlayer, Game) -> (Game) = { scoringPlayer, game ->
    when (scoringPlayer) {
        Player1 -> update(game, gameToPlayer1Score, gameToPlayer2Score)
        Player2 -> update(game, gameToPlayer2Score, gameToPlayer1Score)
    }
}

private val update: (
    game: Game,
    scoringPlayerScore: Lens<Game, Score>,
    opponentPlayerScore: Lens<Game, Score>
) -> Game = { game, scoringPlayerScore, opponentPlayerScore ->
    when (scoringPlayerScore.get(game)) {
        Love -> scoringPlayerScore.set(game, Fifteen)
        Fifteen -> scoringPlayerScore.set(game, Thirty)
        Thirty -> scoringPlayerScore.set(game, Forty)
        Forty -> when (opponentPlayerScore.get(game)) {
            Advantage -> opponentPlayerScore.set(game, Forty)
            Forty -> scoringPlayerScore.set(game, Advantage)
            else -> scoringPlayerScore.set(game, Wins)
        }
        Advantage -> scoringPlayerScore.set(game, Wins)
        Wins -> game
    }
}

val gameCompleted: (Game) -> Boolean = { game ->
    gameToPlayer1Score.get(game) == Wins || gameToPlayer2Score.get(game) == Wins
}