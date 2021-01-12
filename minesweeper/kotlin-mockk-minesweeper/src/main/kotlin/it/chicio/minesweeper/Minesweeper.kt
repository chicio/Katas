package it.chicio.minesweeper

import it.chicio.minesweeper.field.formatter.FieldsFormatter
import it.chicio.minesweeper.field.parser.FieldsParser
import it.chicio.minesweeper.field.resolver.FieldsResolver

class Minesweeper(private val fieldsParser: FieldsParser,
                  private val fieldsResolver: FieldsResolver,
                  private val fieldsFormatter: FieldsFormatter,
                  private val fields: String) {
    fun play(): String {
        return fieldsFormatter.format(fieldsResolver.resolve(fieldsParser.parse(fields)))
    }
}