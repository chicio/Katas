package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

data class FieldsParsingStatus(
    @kotlin.jvm.JvmField
    var currentField: Field?,
    @kotlin.jvm.JvmField
    var currentRowContent: String?,
    var currentRow: Int,
    var headerNumberOfRowsForCurrentField: Int,
    @kotlin.jvm.JvmField
    var fieldsParsed: ArrayList<Field>?
)