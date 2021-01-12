package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

class FieldsParsingStatus {
    @kotlin.jvm.JvmField
    var currentField: Field?
    @kotlin.jvm.JvmField
    var currentRowContent: String?
    var currentRow: Int
    var headerNumberOfRowsForCurrentField: Int
    @kotlin.jvm.JvmField
    var fieldsParsed: ArrayList<Field>?

    constructor(currentField: Field?, currentRowContent: String?, currentRow: Int, headerNumberOfRowsForCurrentField: Int, fieldsParsed: ArrayList<Field>?) {
        this.currentField = currentField
        this.currentRowContent = currentRowContent
        this.currentRow = currentRow
        this.headerNumberOfRowsForCurrentField = headerNumberOfRowsForCurrentField
        this.fieldsParsed = fieldsParsed
    }

    constructor(anotherFieldParsingStatus: FieldsParsingStatus) {
        currentField = anotherFieldParsingStatus!!.currentField
        currentRowContent = anotherFieldParsingStatus.currentRowContent
        currentRow = anotherFieldParsingStatus.currentRow
        headerNumberOfRowsForCurrentField = anotherFieldParsingStatus.headerNumberOfRowsForCurrentField
        fieldsParsed = anotherFieldParsingStatus.fieldsParsed
    }
}