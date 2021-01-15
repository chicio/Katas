package it.chicio.minesweeper

import it.chicio.minesweeper.field.Field
import it.chicio.minesweeper.field.parser.FieldsParsingStatus

data class FieldsParsingStatusBuilder (
    val currentField: Field? = null,
    val currentRowContent: String? = null,
    val currentRow: Int = 0,
    val headerNumberOfRowsForCurrentField: Int = 0,
    val fieldsParsed: MutableList<Field> = mutableListOf()
) {
    fun build(): FieldsParsingStatus {
        return FieldsParsingStatus(
                currentField,
                currentRowContent,
                currentRow,
                headerNumberOfRowsForCurrentField,
                fieldsParsed
        )
    }
}