package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("FieldRowContentTerminationChainParser")
class FieldRowContentTerminationChainParserTest {
    private lateinit var fieldRowContentTerminationChainParser: FieldRowContentTerminationChainParser
    @BeforeEach
    fun setUp() {
        fieldRowContentTerminationChainParser = FieldRowContentTerminationChainParser()
    }

    @Test
    fun `can parse termination row`() {
        assertTrue(fieldRowContentTerminationChainParser.canParse("0 0"))
    }

    @Test
    fun `can not parse termination row`() {
        assertFalse(fieldRowContentTerminationChainParser.canParse("0"))
        assertFalse(fieldRowContentTerminationChainParser.canParse("* *"))
    }

    @Test
    fun `parse valid termination row`() {
        val fieldsParsingStatus = fieldRowContentTerminationChainParser.parse(FieldsParsingStatusBuilder(
                currentField = FieldFactory().make(arrayOf(arrayOf("*"))),
                currentRow = 1
        ).build())

        assertEquals(fieldsParsingStatus.fieldsParsed.size, 1)
        assertEquals(fieldsParsingStatus.fieldsParsed[0], FieldFactory().make(arrayOf(arrayOf("*"))))
    }
}