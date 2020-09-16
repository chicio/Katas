import arrow.typeclasses.Show

sealed class CellStatus
object Alive: CellStatus()
object Dead: CellStatus()

typealias Matrix = List<List<out CellStatus>>

val showMatrix: Show<Matrix> = Show {
    fold("", { matrixString, row ->
        row.fold(matrixString, { string, cell ->
            string + when(cell) {
                Alive -> "* "
                Dead -> "X "
            }
        }) + "\n"
    })
}