package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import java.util.*

class FieldsParserByDivingInputIntoRows(private val fieldRowParser: FieldRowParser) : FieldsParser {
    @Throws(RuntimeException::class)
    override fun parse(fields: String): List<Field> {
        var fieldsParsingStatus: FieldsParsingStatus? = FieldsParsingStatus(null, null, 0, 0, ArrayList())
        for (row in fields.split(System.getProperty("line.separator")).toTypedArray()) {
            fieldsParsingStatus = fieldRowParser.parse(row, fieldsParsingStatus)
        }
        return fieldsParsingStatus!!.fieldsParsed!!
    }
}