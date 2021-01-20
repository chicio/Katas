package it.chicio.minesweeper.field.resolver

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.mockk.verifySequence
import it.chicio.minesweeper.FieldFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("FieldsResolverByIteratingThroughThem")
@ExtendWith(MockKExtension::class)
class FieldsResolverByIteratingThroughThemTest {
    @MockK
    private lateinit var fieldResolver: FieldResolver

    private lateinit var fieldsResolverByIteratingThroughThem: FieldsResolverByIteratingThroughThem

    @BeforeEach
    internal fun setUp() {
        fieldsResolverByIteratingThroughThem = FieldsResolverByIteratingThroughThem(fieldResolver)
    }

    @Nested
    @DisplayName("resolve")
    inner class Resolve {
        @Test
        fun `single field list`() {
            every { fieldResolver.resolve(field) } returns (resolvedField)

            val fields = fieldsResolverByIteratingThroughThem.resolve(listOf(field))

            assertEquals(fields, listOf(resolvedField))
            verify { fieldResolver.resolve(field) }
        }

        @Test
        fun `fields list`() {
            every { fieldResolver.resolve(field) } returns resolvedField
            every { fieldResolver.resolve(anotherField) } returns anotherResolvedField

            val fields = fieldsResolverByIteratingThroughThem.resolve(listOf(field, anotherField))

            assertEquals(fields, listOf(resolvedField, anotherResolvedField))
            verifySequence {
                fieldResolver.resolve(field)
                fieldResolver.resolve(anotherField)
            }
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