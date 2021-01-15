package it.chicio.minesweeper.field.parser

class FieldValidRowParser(private val fieldRowContentParser: FieldRowContentParser): FieldRowParser {
    @Throws(RuntimeException::class)
    override fun parse(row: String?, fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus =
            if (isAValid(row)) {
                val newFieldsParsingStatus = fieldsParsingStatus.copy()
                newFieldsParsingStatus.currentRowContent = row
                fieldRowContentParser.tryToParseRowAndUpdate(newFieldsParsingStatus)
            } else {
                fieldsParsingStatus.copy()
            }

    private fun isAValid(row: String?): Boolean = row != ""
}