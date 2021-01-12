package it.chicio.minesweeper

import it.chicio.minesweeper.field.Field

class FieldFactory {
    fun make(data: Array<Array<String>>): Field {
        val field = Field(data.size, data[0].size)
        for (row in data.indices) {
            for (column in data[0].indices) {
                field[row, column] = data[row][column]
            }
        }
        return field
    }
}