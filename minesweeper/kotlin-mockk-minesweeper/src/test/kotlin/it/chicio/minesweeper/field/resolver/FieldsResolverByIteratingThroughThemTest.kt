package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.FieldFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@DisplayName("FieldsResolverByIteratingThroughThem")
class FieldsResolverByIteratingThroughThemTest {
    @Nested
    @DisplayName("resolve")
    inner class Resolve {
        @Test
        fun `single field list`() {
            val fieldResolver = mock(FieldResolver::class.java)
            `when`(fieldResolver.resolve(field)).thenReturn(resolvedField)
            val fieldsResolverByIteratingThroughThem = FieldsResolverByIteratingThroughThem(fieldResolver)
            val fields = fieldsResolverByIteratingThroughThem.resolve(listOf(field))
            assertEquals(fields, listOf(resolvedField))
        }

        @Test
        fun `fields list`() {
            val fieldResolver = mock(FieldResolver::class.java)
            `when`(fieldResolver.resolve(field)).thenReturn(resolvedField)
            `when`(fieldResolver.resolve(anotherField)).thenReturn(anotherResolvedField)
            val fieldsResolverByIteratingThroughThem = FieldsResolverByIteratingThroughThem(fieldResolver)
            val fields = fieldsResolverByIteratingThroughThem.resolve(listOf(field, anotherField))
            assertEquals(fields, listOf(resolvedField, anotherResolvedField))
        }
    }

    companion object {
        private val field = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", ".", ".", "."),
                        arrayOf(".", ".", ".", ".", "."),
                        arrayOf(".", "*", ".", ".", ".")
                )
        )
        private val resolvedField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", "1", "0", "0"),
                        arrayOf("3", "3", "2", "0", "0"),
                        arrayOf("1", "*", "1", "0", "0")
                )
        )
        private val anotherField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", ".", ".", "."),
                        arrayOf(".", ".", ".", "."),
                        arrayOf(".", "*", ".", "."),
                        arrayOf(".", ".", ".", ".")
                )
        )
        private val anotherResolvedField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", "1", "0", "0"),
                        arrayOf("3", "3", "2", "0", "0"),
                        arrayOf("1", "*", "1", "0", "0")
                )
        )
    }
}