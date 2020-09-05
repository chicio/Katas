import Console.ask
import Console.puts
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import arrow.fx.IO
import arrow.fx.extensions.fx

object GameInteraction {

    val welcome: IO<Unit> = puts("Welcome to the Tennis Game!")

    val showGameScore: (Game) -> IO<Game> = { game -> puts(displayableGameScore(game)).map { game.copy() } }

    val displayableGameScore: (Game) -> String = { game ->
        val player1Score = gameToPlayer1Score.get(game)
        val player2Score = gameToPlayer2Score.get(game)

        when {
            player1Score == Forty && player2Score == Forty ->
                "Deuce"
            player1Score == Wins ->
                "Player 1 wins"
            player2Score == Wins ->
                "Player 2 wins"
            else ->
                "Player 1 ${showScore.run { player1Score.show() }} - " +
                "Player 2 ${showScore.run { player2Score.show() }}"
        }
    }

    fun readPlayer(): IO<ScoringPlayer> =
        IO.fx {
            val input = !ask("Which player will play (1 or 2)?")
            parsePlayer(input).fold(
                {
                    !puts("Invalid player selected. Try again.")
                    !readPlayer()
                },
                { it }
            )
        }

    private val parsePlayer: (String) -> Option<ScoringPlayer> = { input ->
        when (input) {
            "1" -> Player1.some()
            "2" -> Player2.some()
            else -> None
        }
    }
}
