import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.fx.IO

private val ask: (String) -> IO<String> = { question -> puts(question).flatMap(reads) }

private val puts: (String) -> IO<Unit> = { str -> IO { println(str) } }

private val reads: (Unit) -> IO<String> = { IO { readLine()!! } }

val welcome: () -> IO<Unit> = { puts("Welcome to the Tennis Game!") }

val validatePlayer: (String) -> Option<PossiblePlayer> = {input ->
    when (input) {
        "1" -> Some(Player1)
        "2" -> Some(Player2)
        else -> None
    }
}

val askPlayerThatWillPlay: () -> IO<Option<PossiblePlayer>> = {
    ask("Which player will play (1 or 2)?").map(validatePlayer)
}

val showGameScore: (Game) -> IO<Unit> = { game -> puts(displayableGameScore(game)) }

val showPlayerSelectionError: () -> IO<Unit> = { puts("Invalid player selected. Try again.") }
