package it.chicio.minesweeper.field.parser

class FieldRowContentTerminationChainParser : FieldRowContentChainParser() {
    override fun canParse(row: String?): Boolean = isTermination(row)

    private fun isTermination(row: String?): Boolean = row == "0 0"

    override fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus = fieldsParsingStatus
            .currentField
            ?.let {
                val newFieldParsingStatus = fieldsParsingStatus.copy()
                newFieldParsingStatus.fieldsParsed.add(it)
                newFieldParsingStatus
            } ?: fieldsParsingStatus.copy()
}