import arrow.fx.IO

private val ask: (String) -> IO<String> = { question -> puts(question).flatMap(reads) }

private val puts: (String) -> IO<Unit> = { str -> IO { println(str) } }

private val reads: (Unit) -> IO<String> = { IO { readLine()!! } }

val welcome: () -> IO<Unit> = { puts("Welcome to the Tennis Game!") }

private val playerMakeScore: (String, Game) -> (Game) = { input, game ->
    when (input) {
        "1" -> player1Plays(game)
        "2" -> player2Plays(game)
        else -> game
    }
}

val askPlayerPlays: (Game) -> IO<Game> = { game ->
    ask("Which player will play (1 or 2)?").map { input -> playerMakeScore(input, game) }
}

val printGameScore: (Game) -> IO<Unit> = { game -> puts(displayableGameScore(game)) }
