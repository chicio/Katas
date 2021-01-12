package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field
import java.util.*

class FieldsResolverByIteratingThroughThem(private val fieldResolver: FieldResolver) : FieldsResolver {
    override fun resolve(fields: List<Field>): List<Field?> {
        val resolvedFields = ArrayList<Field?>()
        for (field in fields) {
            resolvedFields.add(fieldResolver.resolve(field))
        }
        return resolvedFields
    }
}