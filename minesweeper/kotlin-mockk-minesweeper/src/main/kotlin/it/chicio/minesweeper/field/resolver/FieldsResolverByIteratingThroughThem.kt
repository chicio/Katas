package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field

class FieldsResolverByIteratingThroughThem(private val fieldResolver: FieldResolver) : FieldsResolver {
    override fun resolve(fields: List<Field>): List<Field> =
            fields
                    .map { fieldResolver.resolve(it) }
                    .toList()
}