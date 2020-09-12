import arrow.core.None
import arrow.core.Option
import arrow.core.Some

sealed class CellStatus
object Alive: CellStatus()
object Dead: CellStatus()

typealias Matrix = Array<Array<out CellStatus>>


