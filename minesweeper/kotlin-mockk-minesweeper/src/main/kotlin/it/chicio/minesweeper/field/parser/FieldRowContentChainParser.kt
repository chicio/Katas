package it.chicio.minesweeper.field.parser

abstract class FieldRowContentChainParser : FieldRowContentParser {
    private var nextFieldRowContentChainParser: FieldRowContentChainParser? = null
    override fun tryToParseRowAndUpdate(parsingStatus: FieldsParsingStatus): FieldsParsingStatus =
            when {
                canParse(parsingStatus.currentRowContent) -> parse(parsingStatus)
                else -> nextFieldRowContentChainParser?.tryToParseRowAndUpdate(parsingStatus) ?: parsingStatus
            }

    fun setNextFieldRowContentChainParser(fieldRowContentChainParser: FieldRowContentChainParser?) {
        nextFieldRowContentChainParser = fieldRowContentChainParser
    }

    abstract fun canParse(row: String?): Boolean
    abstract fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus
}