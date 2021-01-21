package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("FieldRowContentValuesChainParser")
class FieldRowContentValuesChainParserTest {
    private lateinit var fieldRowContentValuesChainParser: FieldRowContentValuesChainParser

    @BeforeEach
    fun setUp() {
        fieldRowContentValuesChainParser = FieldRowContentValuesChainParser()
    }

    @Test
    fun `can parse valid row`() {
        assertTrue(fieldRowContentValuesChainParser.canParse("* . . *"))
    }

    @Test
    fun `cannot parse other rows`() {
        assertFalse(fieldRowContentValuesChainParser.canParse("1 1"))
        assertFalse(fieldRowContentValuesChainParser.canParse("0 0"))
    }

    @Test
    fun `parse row`() {
        val fieldsParsingStatus = fieldRowContentValuesChainParser.parse(FieldsParsingStatusBuilder(
                currentField = FieldFactory().make(arrayOf(arrayOf("*", "."), arrayOf("", ""))),
                currentRow = 1,
                currentRowContent = "* *"
        ).build())
        assertEquals(fieldsParsingStatus.currentField!![1, 0], "*")
        assertEquals(fieldsParsingStatus.currentField!![1, 1], "*")
    }

    @Test
    fun `fail parse row`() {
        assertThrows(RuntimeException::class.java) {
            fieldRowContentValuesChainParser.parse(FieldsParsingStatusBuilder(
                    currentField = FieldFactory().make(arrayOf(arrayOf("*", "."), arrayOf("", ""))),
                    currentRow = 1,
                    currentRowContent = "* * *"
            ).build())
        }
    }
}