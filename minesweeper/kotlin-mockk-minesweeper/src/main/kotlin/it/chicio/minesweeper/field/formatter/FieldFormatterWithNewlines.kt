package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

class FieldFormatterWithNewlines : FieldFormatter {
    override fun format(field: Field): String {
        val fieldAsString = StringBuilder()
        for (row in 0 until field.numberOfRows) {
            for (column in 0 until field.numberOfColumn) {
                fieldAsString.append(field[row, column])
                appendSpaceIfIsNotLastColumn(field, fieldAsString, column)
            }
            fieldAsString.append(System.getProperty("line.separator"))
        }
        return fieldAsString.toString()
    }

    private fun appendSpaceIfIsNotLastColumn(field: Field?, fieldAsString: StringBuilder, column: Int) {
        if (isNotLastColumn(field, column)) {
            fieldAsString.append(" ")
        }
    }

    private fun isNotLastColumn(field: Field?, column: Int): Boolean = column != (field?.numberOfColumn ?: 0) - 1
}