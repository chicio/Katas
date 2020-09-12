import arrow.core.None
import arrow.core.Option
import arrow.core.Some

fun getCellFrom(matrix: Matrix, rowPosition: Int, columnPosition: Int): Option<CellStatus> {
    matrix.getOrNull(rowPosition)?.let { row ->
        row.getOrNull(columnPosition)?.let { cell ->
            Some(cell)
        }
    }
    return None
}

fun getNeighboursValueUsing(row: Int, column: Int, matrix: Matrix): Int =
        getCellFrom(matrix, row, column).fold( { 0 }, { 1 })

fun getNumberOfNeighboursFor(row: Int, column: Int, matrix: Matrix): Int =
        getNeighboursValueUsing(row - 1, column - 1, matrix) +
                getNeighboursValueUsing(row - 1, column, matrix) +
                getNeighboursValueUsing(row - 1, column + 1, matrix) +
                getNeighboursValueUsing(row, column - 1, matrix) +
                getNeighboursValueUsing(row, column + 1, matrix) +
                getNeighboursValueUsing(row + 1, column - 1, matrix) +
                getNeighboursValueUsing(row + 1, column, matrix) +
                getNeighboursValueUsing(row + 1, column + 1, matrix)

fun getNextGenerationCellStatusFor(row: Int, column: Int, matrix: Matrix): CellStatus = getCellFrom(matrix, row, column).fold<CellStatus>(
        { Dead },
        { cellStatus ->
            val numberOfNeighboursFor = getNumberOfNeighboursFor(row, column, matrix)
             when {
                (numberOfNeighboursFor == 2 || numberOfNeighboursFor == 3) && cellStatus == Alive -> Alive
                (numberOfNeighboursFor == 3) && cellStatus == Dead -> Alive
                else -> Dead
            }
        })

