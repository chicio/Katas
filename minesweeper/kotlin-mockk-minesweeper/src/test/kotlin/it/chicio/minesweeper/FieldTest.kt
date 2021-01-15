package it.chicio.minesweeper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Field")
class FieldTest {
    @Nested
    @DisplayName("neighbours for")
    inner class NeighboursTest {
        @Test
        fun `1x1 field`() =
                assertEquals(FieldFactory().make(arrayOf(arrayOf("*"))).neighboursOf(0, 0), emptyList<String>())

        @Test
        fun `1x2 on left`() =
                assertEquals(FieldFactory().make(arrayOf(arrayOf("*", "."))).neighboursOf(0, 1), listOf("*"))

        @Test
        fun `2x1 on right`() =
                assertEquals(FieldFactory().make(arrayOf(arrayOf(".", "*"))).neighboursOf(0, 0),listOf("*"))

        @Test
        fun `2x2 on right`() =
                assertEquals(
                        FieldFactory()
                                .make(arrayOf(arrayOf(".", "*"), arrayOf("*", "*")))
                                .neighboursOf(0, 0),
                        listOf("*", "*", "*")
                )

        @Test
        fun `2x3 on right and left`() =
                assertEquals(
                        FieldFactory()
                                .make(arrayOf(arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))
                                .neighboursOf(0, 1),
                        listOf("*", "*", "*", "*", "*")
                )

        @Test
        fun `3x3 with 3 bombs`() = assertEquals(
                FieldFactory()
                        .make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))
                        .neighboursOf(1, 1), listOf("*", "*", "*", "*", "*", "*", "*", "*")
        )
    }
}