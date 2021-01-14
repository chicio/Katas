package it.chicio.minesweeper.field.parser

class FieldValidRowParser(private val fieldRowContentParser: FieldRowContentParser): FieldRowParser {
    @Throws(RuntimeException::class)
    override fun parse(row: String?, fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        val newFieldsParsingStatus = fieldsParsingStatus.copy()
        return if (isAValid(row)) {
            newFieldsParsingStatus.currentRowContent = row
            fieldRowContentParser.tryToParseRowAndUpdate(newFieldsParsingStatus)
        } else {
            newFieldsParsingStatus
        }
    }

    private fun isAValid(row: String?): Boolean = row != ""
}