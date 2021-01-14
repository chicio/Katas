package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

class FieldsFormatterWithHeader(private val fieldFormatter: FieldFormatter) : FieldsFormatter {
    override fun format(fields: List<Field>): String = fields
            .mapIndexed { index, field -> createHeaderFor(index + 1).plus(fieldFormatter.format(field)) }
            .joinToString(System.getProperty("line.separator"))

    private fun createHeaderFor(currentField: Int): String =
            "field #" + currentField + ":" + System.getProperty("line.separator")
}