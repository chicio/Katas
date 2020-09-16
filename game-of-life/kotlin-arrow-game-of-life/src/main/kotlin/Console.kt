import arrow.fx.IO
import arrow.fx.IO.Companion.just

object Console {
    val puts: (String) -> IO<Unit> = { str -> just(println(str)) }
}
