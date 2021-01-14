package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

interface FieldsFormatter {
    fun format(fields: List<Field>): String
}