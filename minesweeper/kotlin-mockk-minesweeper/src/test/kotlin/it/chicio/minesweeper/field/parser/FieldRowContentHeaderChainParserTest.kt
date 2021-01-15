package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import it.chicio.minesweeper.field.Field
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class FieldRowContentHeaderChainParserTest {
    private lateinit var fieldRowContentHeaderChainParser: FieldRowContentHeaderChainParser

    @BeforeEach
    fun setUp() {
        fieldRowContentHeaderChainParser = FieldRowContentHeaderChainParser()
    }

    @Test
    fun canParseValidHeaderRow() =
            assertTrue(fieldRowContentHeaderChainParser.canParse("1 1"))

    @Test
    fun canNotParseValidHeaderRow() {
        assertFalse(fieldRowContentHeaderChainParser.canParse("1"))
        assertFalse(fieldRowContentHeaderChainParser.canParse(""))
    }

    @Test
    fun parseHeader() {
        val previousField = FieldFactory().make(arrayOf(arrayOf("*")))
        val newFieldsParsingStatus = fieldRowContentHeaderChainParser.parse(
                FieldsParsingStatusBuilder(
                        currentField = previousField,
                        currentRowContent = "2 2",
                        headerNumberOfRowsForCurrentField = 1,
                ).build()
        )
        assertEquals(newFieldsParsingStatus.fieldsParsed.size, 1)
        assertEquals(newFieldsParsingStatus.currentField!!.numberOfColumn, 2)
        assertEquals(newFieldsParsingStatus.currentField!!.numberOfRows, 2)
        assertNotEquals(newFieldsParsingStatus.currentField, previousField)
    }

    @Test
    fun failParseForInvalidField() {

        assertThrows(RuntimeException::class.java) {
            fieldRowContentHeaderChainParser.parse(
                    FieldsParsingStatusBuilder(
                            currentRowContent = "2 2",
                            currentField = FieldFactory().make(arrayOf()),
                            headerNumberOfRowsForCurrentField = 1,
                    ).build()
            )
        }
    }
}