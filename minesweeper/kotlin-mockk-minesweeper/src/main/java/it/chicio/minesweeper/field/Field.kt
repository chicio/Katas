package it.chicio.minesweeper.field

import java.util.*

class Field {
    private var matrixFieldRepresentation: Array<Array<String?>>

    constructor(matrixFieldRepresentation: Array<Array<String?>>) {
        this.matrixFieldRepresentation = matrixFieldRepresentation
    }

    constructor(numberOfRows: Int, numberOfColumn: Int) {
        matrixFieldRepresentation = Array(numberOfRows) { arrayOfNulls(numberOfColumn) }
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val field = o as Field
        return Arrays.deepEquals(matrixFieldRepresentation, field.matrixFieldRepresentation)
    }

    private fun has(row: Int, column: Int): Boolean {
        return row >= 0 && row < numberOfRows() && column >= 0 && column < numberOfColumn()
    }

    operator fun get(row: Int, column: Int): String? {
        return matrixFieldRepresentation[row][column]
    }

    operator fun set(row: Int, column: Int, value: String?) {
        matrixFieldRepresentation[row][column] = value
    }

    fun numberOfRows(): Int {
        return matrixFieldRepresentation.size
    }

    fun numberOfColumn(): Int {
        return matrixFieldRepresentation[0].size
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