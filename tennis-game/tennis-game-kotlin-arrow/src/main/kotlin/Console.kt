import Common.ask
import Common.puts
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import arrow.fx.IO
import arrow.fx.extensions.fx

object GameInteraction {
    val welcome: IO<Unit> = puts("Welcome to the Tennis Game!")

    val showGameScore: (Game) -> IO<Unit> = { game -> puts(displayableGameScore(game)) }

    val displayableGameScore: (Game) -> String = { game ->
        val gamePlayer1Score = gameToPlayer1Score.get(game)
        val gamePlayer2Score = gameToPlayer2Score.get(game)

        when {
            gamePlayer1Score == Forty && gamePlayer2Score == Forty -> "Deuce"
            gamePlayer1Score == Wins -> "Player 1 wins"
            gamePlayer2Score == Wins -> "Player 2 wins"
            else -> "Player 1 ${showScore.run { gamePlayer1Score.show() }} - Player 2 ${showScore.run { gamePlayer2Score.show() }}"
        }
    }

    val showPlayerSelectionError: IO<Unit> = puts("Invalid player selected. Try again.")

    fun readPlayer(): IO<ScoringPlayer>  =
        IO.fx {
            val input = !ask("Which player will play (1 or 2)?")
            PARSE_PLAYER(input).fold(
                {
                    !showPlayerSelectionError
                    !readPlayer()
                },
                { it }
            )
        }

    val PARSE_PLAYER: (String) -> Option<ScoringPlayer> = { input ->
        when (input) {
            "1" -> Player1.some()
            "2" -> Player2.some()
            else -> None
        }
    }
}

object Common {
    fun ask(question: String): IO<String> = puts(question).flatMap { reads }

    val puts: (String) -> IO<Unit> = { str -> IO { println(str) } }

    private val reads: IO<String> = IO { readLine()!! }
}