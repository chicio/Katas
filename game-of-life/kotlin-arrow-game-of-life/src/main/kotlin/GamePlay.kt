import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.fx.IO

object GamePlay {
    fun calculateNextGeneration(matrix: Matrix): IO<Matrix> = IO.just(nextGeneration(matrix))

    private fun nextGeneration(currentGeneration: Matrix): Matrix =
            currentGeneration.mapIndexed { rowPosition, row ->
                row.mapIndexed { columnPosition, cell ->
                    getNextGenerationCellStatusFor(cell, rowPosition, columnPosition, currentGeneration)
                }
            }

    private fun getNextGenerationCellStatusFor(cellStatus: CellStatus, row: Int, column: Int, matrix: Matrix): CellStatus {
        val numberOfNeighboursFor = getNumberOfNeighboursFor(row, column, matrix)
        return when {
            (numberOfNeighboursFor == 2 || numberOfNeighboursFor == 3) && cellStatus == Alive -> Alive
            (numberOfNeighboursFor == 3) && cellStatus == Dead -> Alive
            else -> Dead
        }
    }

    private fun getNumberOfNeighboursFor(row: Int, column: Int, matrix: Matrix): Int =
            getNeighboursValueUsing(row - 1, column - 1, matrix) +
                    getNeighboursValueUsing(row - 1, column, matrix) +
                    getNeighboursValueUsing(row - 1, column + 1, matrix) +
                    getNeighboursValueUsing(row, column - 1, matrix) +
                    getNeighboursValueUsing(row, column + 1, matrix) +
                    getNeighboursValueUsing(row + 1, column - 1, matrix) +
                    getNeighboursValueUsing(row + 1, column, matrix) +
                    getNeighboursValueUsing(row + 1, column + 1, matrix)

    private fun getNeighboursValueUsing(row: Int, column: Int, matrix: Matrix): Int =
            getCellFrom(matrix, row, column).fold(
                    { 0 },
                    { cell ->
                        when (cell) {
                            Alive -> 1
                            Dead -> 0
                        }
                    })

    private fun getCellFrom(matrix: Matrix, rowPosition: Int, columnPosition: Int): Option<CellStatus> {
        matrix.getOrNull(rowPosition)?.let { row ->
            row.getOrNull(columnPosition)?.let { cell ->
                return Some(cell)
            }
        }
        return None
    }
}