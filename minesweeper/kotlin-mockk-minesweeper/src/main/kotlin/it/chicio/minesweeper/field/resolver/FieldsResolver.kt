package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field

interface FieldsResolver {
    fun resolve(fields: List<Field>): List<Field?>
}