import arrow.fx.IO

object Console {
    val ask: (String) -> IO<String> = { question -> puts(question).flatMap { reads } }

    val puts: (String) -> IO<Unit> = { str -> IO { println(str) } }

    private val reads: IO<String> = IO { readLine()!! }
}
