package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.FieldFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FieldFormatterWithNewlinesTest {
    @Test
    fun formatField() = assertEquals(
            FieldFormatterWithNewlines().format(
                    FieldFactory().make(
                            arrayOf(
                                    arrayOf("*", "1", "0", "0"),
                                    arrayOf("2", "2", "1", "0"),
                                    arrayOf("1", "*", "1", "0"),
                                    arrayOf("1", "1", "1", "0")
                            )
                    )
            ),
            """
            * 1 0 0
            2 2 1 0
            1 * 1 0
            1 1 1 0
            
            """.trimIndent()
    )
}