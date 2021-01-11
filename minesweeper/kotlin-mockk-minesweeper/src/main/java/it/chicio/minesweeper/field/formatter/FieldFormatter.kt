package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field

interface FieldFormatter {
    fun format(field: Field?): String
}