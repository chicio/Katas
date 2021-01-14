package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

data class FieldsParsingStatus(
    var currentField: Field?,
    var currentRowContent: String?,
    var currentRow: Int,
    var headerNumberOfRowsForCurrentField: Int,
    var fieldsParsed: MutableList<Field>
)