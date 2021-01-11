package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

class FieldsParsingStatusBuilder {
    private var currentField: Field? = null
    private var currentRowContent: String? = null
    private var currentRow = 0
    private var headerNumberOfRowsForCurrentField = 0
    private var fieldsParsed: ArrayList<Field>? = null
    fun withCurrentField(currentField: Field?): FieldsParsingStatusBuilder {
        this.currentField = currentField
        return this
    }

    fun withCurrentRowContent(currentRowContent: String?): FieldsParsingStatusBuilder {
        this.currentRowContent = currentRowContent
        return this
    }

    fun withCurrentRow(currentRow: Int): FieldsParsingStatusBuilder {
        this.currentRow = currentRow
        return this
    }

    fun withHeaderNumberOfRowsForCurrentField(headerNumberOfRowsForCurrentField: Int): FieldsParsingStatusBuilder {
        this.headerNumberOfRowsForCurrentField = headerNumberOfRowsForCurrentField
        return this
    }

    fun withFieldsParsed(fieldsParsed: ArrayList<Field>?): FieldsParsingStatusBuilder {
        this.fieldsParsed = fieldsParsed
        return this
    }

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