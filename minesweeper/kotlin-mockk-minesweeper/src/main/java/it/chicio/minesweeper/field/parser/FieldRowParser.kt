package it.chicio.minesweeper.field.parser

interface FieldRowParser {
    @Throws(RuntimeException::class)
    fun parse(row: String, fieldsParsingStatus: FieldsParsingStatus?): FieldsParsingStatus?
}