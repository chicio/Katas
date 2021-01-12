package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field

interface FieldsParser {
    @Throws(RuntimeException::class)
    fun parse(fields: String): List<Field>
}