package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field

class FieldRowContentTerminationChainParser : FieldRowContentChainParser() {
    public override fun canParse(row: String?): Boolean {
        return isTermination(row)
    }

    private fun isTermination(row: String?): Boolean {
        return row == "0 0"
    }

    @Throws(RuntimeException::class)
    public override fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        val newFieldsParsingStatus = fieldsParsingStatus.copy()
        if (isValid(newFieldsParsingStatus.currentField)) {
            newFieldsParsingStatus.fieldsParsed!!.add(newFieldsParsingStatus.currentField!!)
        }
        return newFieldsParsingStatus
    }

    private fun isValid(field: Field?): Boolean {
        return field != null
    }
}