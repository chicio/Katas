package it.chicio.minesweeper.field.parser

abstract class FieldRowContentChainParser : FieldRowContentParser {
    private var nextFieldRowContentChainParser: FieldRowContentChainParser? = null
    override fun tryToParseRowAndUpdate(parsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        if (canParse(parsingStatus.currentRowContent)) {
            return parse(parsingStatus)
        } else if (hasAValidNextFieldRowParser()) {
            return nextFieldRowContentChainParser!!.tryToParseRowAndUpdate(parsingStatus)
        }
        return parsingStatus
    }

    private fun hasAValidNextFieldRowParser(): Boolean {
        return nextFieldRowContentChainParser != null
    }

    fun setNextFieldRowContentChainParser(fieldRowContentChainParser: FieldRowContentChainParser?) {
        nextFieldRowContentChainParser = fieldRowContentChainParser
    }

    abstract fun canParse(row: String?): Boolean
    abstract fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus
}