package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

class FieldsFormatterWithHeader(private val fieldFormatter: FieldFormatter) : FieldsFormatter {
    override fun format(fields: List<Field?>): String {
        val formattedField = StringBuilder()
        var currentFieldPosition = 1
        for (field in fields) {
            formattedField.append(createHeaderFor(currentFieldPosition)).append(fieldFormatter.format(field))
            appendNewlineIfNeeded(fields, formattedField, currentFieldPosition)
            currentFieldPosition++
        }
        return formattedField.toString()
    }

    private fun appendNewlineIfNeeded(fields: List<Field?>, formattedField: StringBuilder, currentFieldPosition: Int) {
        if (currentFieldPosition != fields.size) {
            formattedField.append(System.getProperty("line.separator"))
        }
    }

    private fun createHeaderFor(currentField: Int): String {
        return "field #" + currentField + ":" + System.getProperty("line.separator")
    }
}