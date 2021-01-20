package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

class FieldsFormatterWithHeader(private val fieldFormatter: FieldFormatter) : FieldsFormatter {
    override fun format(fields: List<Field>): String = fields
            .mapIndexed { index, field -> "field #${index + 1}:\n".plus(fieldFormatter.format(field)) }
            .joinToString(System.getProperty("line.separator"))
}