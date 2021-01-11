package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field

class FieldResolverByIteratingThroughEachValue : FieldResolver {
    override fun resolve(field: Field): Field {
        val resolvedField = Field(field.numberOfRows(), field.numberOfColumn())
        for (row in 0 until field.numberOfRows()) {
            for (column in 0 until field.numberOfColumn()) {
                if (isABomb(field[row, column])) {
                    resolvedField[row, column] = "*"
                } else {
                    resolvedField[row, column] = getNumberOfBombsInNeighbours(field, row, column).toString()
                }
            }
        }
        return resolvedField
    }

    private fun isABomb(value: String?): Boolean {
        return value == "*"
    }

    private fun getNumberOfBombsInNeighbours(field: Field, row: Int, column: Int): Int {
        var numberOfBombsNearCell = 0
        for (value in field.neighboursOf(row, column)) {
            if (isABomb(value)) {
                numberOfBombsNearCell++
            }
        }
        return numberOfBombsNearCell
    }
}