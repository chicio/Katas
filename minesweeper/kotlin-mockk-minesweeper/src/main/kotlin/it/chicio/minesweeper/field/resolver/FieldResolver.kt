package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field

interface FieldResolver {
    fun resolve(field: Field): Field
}