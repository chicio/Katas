import arrow.typeclasses.Show

sealed class Score
object Love : Score()
object Fifteen : Score()
object Thirty : Score()
object Forty : Score()
object Advantage : Score()
object Wins : Score()

val showScore: Show<Score> = Show { javaClass.simpleName }
