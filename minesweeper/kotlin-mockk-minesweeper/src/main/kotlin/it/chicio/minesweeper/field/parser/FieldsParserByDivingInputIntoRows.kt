package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field

class FieldsParserByDivingInputIntoRows(private val fieldRowParser: FieldRowParser) : FieldsParser {
    override fun parse(fields: String): List<Field> {
        var fieldsParsingStatus = FieldsParsingStatus(null, null, 0, 0, mutableListOf())
        fields
                .split(System.getProperty("line.separator"))
                .toTypedArray()
                .forEach { row ->
                    fieldsParsingStatus = fieldRowParser.parse(row, fieldsParsingStatus)
                }
        return fieldsParsingStatus.fieldsParsed
    }
}