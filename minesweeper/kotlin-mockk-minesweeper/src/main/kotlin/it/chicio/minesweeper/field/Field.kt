package it.chicio.minesweeper.field

import java.util.*

class Field(val numberOfRows: Int, val numberOfColumn: Int) {
    private val matrixFieldRepresentation: Array<Array<String>> = Array(numberOfRows) { Array(numberOfColumn) { "" } }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val field = other as Field
        return matrixFieldRepresentation.contentDeepEquals(field.matrixFieldRepresentation)
    }

    override fun hashCode(): Int {
        var result = numberOfRows
        result = 31 * result + numberOfColumn
        result = 31 * result + matrixFieldRepresentation.contentDeepHashCode()
        return result
    }

    private fun has(row: Int, column: Int): Boolean {
        return row in 0 until numberOfRows && column in 0 until numberOfColumn
    }

    operator fun get(row: Int, column: Int): String? {
        return matrixFieldRepresentation[row][column]
    }

    operator fun set(row: Int, column: Int, value: String) {
        matrixFieldRepresentation[row][column] = value
    }

    fun neighboursOf(row: Int, column: Int): ArrayList<String?> {
        val neighbours = ArrayList<String?>()
        if (has(row, column - 1)) {
            neighbours.add(get(row, column - 1))
        }
        if (has(row, column + 1)) {
            neighbours.add(get(row, column + 1))
        }
        if (has(row + 1, column + 1)) {
            neighbours.add(get(row + 1, column + 1))
        }
        if (has(row + 1, column)) {
            neighbours.add(get(row + 1, column))
        }
        if (has(row + 1, column - 1)) {
            neighbours.add(get(row + 1, column - 1))
        }
        if (has(row - 1, column - 1)) {
            neighbours.add(get(row - 1, column - 1))
        }
        if (has(row - 1, column)) {
            neighbours.add(get(row - 1, column))
        }
        if (has(row - 1, column + 1)) {
            neighbours.add(get(row - 1, column + 1))
        }
        return neighbours
    }
}