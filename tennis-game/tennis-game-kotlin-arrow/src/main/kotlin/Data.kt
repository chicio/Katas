import arrow.optics.optics
import arrow.typeclasses.Show

@optics
data class Player(val score: Score = Love) { companion object }

sealed class ScoringPlayer
object Player1 : ScoringPlayer()
object Player2 : ScoringPlayer()

@optics
data class Game(
    val player1: Player = Player(),
    val player2: Player = Player()
) {
    companion object
}

val gameToPlayer1Score = Game.player1.score
val gameToPlayer2Score = Game.player2.score

sealed class Score
object Love : Score()
object Fifteen : Score()
object Thirty : Score()
object Forty : Score()
object Advantage : Score()
object Wins : Score()

val showScore: Show<Score> = Show { javaClass.simpleName }