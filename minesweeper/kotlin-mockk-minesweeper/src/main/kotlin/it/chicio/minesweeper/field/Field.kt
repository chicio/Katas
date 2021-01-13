package it.chicio.minesweeper.field

class Field(val numberOfRows: Int, val numberOfColumn: Int) {
    private val matrixFieldRepresentation: MutableList<MutableList<String>> = MutableList(numberOfRows) { MutableList(numberOfColumn) { "" } }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val field = other as Field
        return matrixFieldRepresentation == field.matrixFieldRepresentation
    }


    override fun hashCode(): Int {
        var result = numberOfRows
        result = 31 * result + numberOfColumn
        result = 31 * result + matrixFieldRepresentation.hashCode()
        return result
    }

    operator fun get(row: Int, column: Int): String? = matrixFieldRepresentation.getOrNull(row)?.getOrNull(column)

    operator fun set(row: Int, column: Int, value: String) {
        matrixFieldRepresentation[row][column] = value
    }

    fun neighboursOf(row: Int, column: Int): List<String> {
        val neighbours = mutableListOf<String>()
        get(row, column - 1)?.let { neighbours.add(it) }
        get(row, column + 1)?.let { neighbours.add(it) }
        get(row + 1, column + 1)?.let { neighbours.add(it) }
        get(row + 1, column)?.let { neighbours.add(it) }
        get(row + 1, column - 1)?.let { neighbours.add(it) }
        get(row - 1, column - 1)?.let { neighbours.add(it) }
        get(row - 1, column)?.let { neighbours.add(it) }
        get(row - 1, column + 1)?.let { neighbours.add(it) }
        return neighbours.toList()
    }
}