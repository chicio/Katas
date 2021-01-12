package it.chicio.minesweeper.field.parser

interface FieldRowContentParser {
    fun tryToParseRowAndUpdate(parsingStatus: FieldsParsingStatus): FieldsParsingStatus
}