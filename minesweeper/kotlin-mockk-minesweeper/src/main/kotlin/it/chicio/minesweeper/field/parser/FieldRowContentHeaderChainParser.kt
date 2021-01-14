package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

class FieldRowContentHeaderChainParser : FieldRowContentChainParser() {
    override fun canParse(row: String?): Boolean {
        return isHeader(row)
    }

    private fun isHeader(row: String?): Boolean {
        return row!!.matches(Regex("(\\d \\d)"))
    }

    @Throws(RuntimeException::class)
    override fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        val newFieldsParsingStatus = fieldsParsingStatus.copy()
        if (isValid(fieldsParsingStatus.currentField)) {
            addFieldIfItHasAValidNumberOfRows(
                    newFieldsParsingStatus.fieldsParsed,
                    newFieldsParsingStatus.currentField,
                    newFieldsParsingStatus.headerNumberOfRowsForCurrentField
            )
        }
        parseHeaderAndUpdateNewParsingStatus(newFieldsParsingStatus)
        return newFieldsParsingStatus
    }

    private fun parseHeaderAndUpdateNewParsingStatus(newFieldsParsingStatus: FieldsParsingStatus) {
        val fieldDimensions = newFieldsParsingStatus.currentRowContent!!.split(" ").toTypedArray()
        newFieldsParsingStatus.headerNumberOfRowsForCurrentField = Integer.valueOf(fieldDimensions[0])
        newFieldsParsingStatus.currentRow = 0
        newFieldsParsingStatus.currentField = Field(
                newFieldsParsingStatus.headerNumberOfRowsForCurrentField,
                Integer.valueOf(fieldDimensions[1])
        )
    }

    @Throws(RuntimeException::class)
    private fun addFieldIfItHasAValidNumberOfRows(fieldsParsed: ArrayList<Field>?,
                                                  field: Field?,
                                                  currentNumberOfRows: Int) {
        if (field!!.numberOfRows < currentNumberOfRows) {
            throw RuntimeException("Invalid number of row")
        }
        fieldsParsed!!.add(field)
    }

    private fun isValid(field: Field?): Boolean {
        return field != null
    }
}