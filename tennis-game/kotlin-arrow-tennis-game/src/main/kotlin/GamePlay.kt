import GameInteraction.readPlayer
import GameInteraction.showGameScore
import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.optics.Lens

object GamePlay {

    fun playTennisGame(game: Game): IO<Game> =
            IO.fx {
                val playerScoring = !readPlayer()
                val updatedGame = !trackScoredPoint(playerScoring, game)
                val shownGameScore = !showGameScore(updatedGame)
                when {
                    gameCompleted(shownGameScore) -> shownGameScore
                    else -> !playTennisGame(shownGameScore)
                }
            }

    private val trackScoredPoint: (ScoringPlayer, Game) -> IO<Game> = { scoringPlayer, game ->
        IO.fx {
            trackScorePointForPlayer(scoringPlayer, game)
        }
    }

    val trackScorePointForPlayer: (ScoringPlayer, Game) -> Game = { scoringPlayer, game ->
        when (scoringPlayer) {
            Player1 -> update(game, gameToPlayer1Score, gameToPlayer2Score)
            Player2 -> update(game, gameToPlayer2Score, gameToPlayer1Score)
        }
    }

    private val gameCompleted: (Game) -> Boolean = { game ->
        gameToPlayer1Score.get(game) == Wins || gameToPlayer2Score.get(game) == Wins
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
}
