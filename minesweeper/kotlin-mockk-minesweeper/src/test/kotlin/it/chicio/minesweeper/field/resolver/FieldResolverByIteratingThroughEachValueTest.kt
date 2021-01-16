package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.FieldFactory

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("FieldResolverByIteratingThroughEachValue")
class FieldResolverByIteratingThroughEachValueTest {
    private lateinit var fieldResolverByIteratingThroughEachValue: FieldResolverByIteratingThroughEachValue

    @BeforeEach
    fun setUp() {
        fieldResolverByIteratingThroughEachValue = FieldResolverByIteratingThroughEachValue()
    }

    @Nested
    @DisplayName("resolve")
    inner class Resolve {
        @Test
        fun `1x1 field with a bomb`() =
                assertEquals(
                        fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*")))),
                        FieldFactory().make(arrayOf(arrayOf("*")))
                )

        @Nested
        @DisplayName("1x2 field")
        inner class Field1x2 {
            @Test
            fun `with a bomb on the left`() =
                    assertEquals(
                            fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", ".")))),
                            FieldFactory().make(arrayOf(arrayOf("*", "1")))
                    )

            @Test
            fun `with a bomb on the right`() =
                    assertEquals(
                            fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf(".", "*")))),
                            FieldFactory().make(arrayOf(arrayOf("1", "*"))
                            )
                    )
        }

        @Test
        fun `2x2 field with 3 bomb`() =
                assertEquals(
                        fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf(".", "*"), arrayOf("*", "*")))),
                        FieldFactory().make(arrayOf(arrayOf("3", "*"), arrayOf("*", "*")))
                )

        @Test
        fun `2x3 field with 3 bomb`() =
                assertEquals(
                        fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))),
                        FieldFactory().make(arrayOf(arrayOf("*", "5", "*"), arrayOf("*", "*", "*")))
                )

        @Test
        fun `3x3 field with 3 bomb`() =
                assertEquals(
                        fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))),
                        FieldFactory().make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", "8", "*"), arrayOf("*", "*", "*")))
                )

        @Nested
        @DisplayName("a test field")
        inner class TestField {
            @Test
            fun `3x5`() =
                    assertEquals(
                            fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", "*", ".", ".", "."), arrayOf(".", ".", ".", ".", "."), arrayOf(".", "*", ".", ".", ".")))),
                            FieldFactory().make(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0")))
                    )

            @Test
            fun `4x4`() =
                    assertEquals(
                            fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", ".", ".", "."), arrayOf(".", ".", ".", "."), arrayOf(".", "*", ".", "."), arrayOf(".", ".", ".", ".")))),
                            FieldFactory().make(arrayOf(arrayOf("*", "1", "0", "0"), arrayOf("2", "2", "1", "0"), arrayOf("1", "*", "1", "0"), arrayOf("1", "1", "1", "0")))
                    )
        }
    }
}