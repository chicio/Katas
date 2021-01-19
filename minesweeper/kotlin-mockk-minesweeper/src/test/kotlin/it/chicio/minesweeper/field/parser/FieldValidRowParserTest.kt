package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@DisplayName("FieldValidRowParser")
class FieldValidRowParserTest {
    private lateinit var fieldRowContentParser: FieldRowContentParser
    private lateinit var fieldValidRowParser: FieldValidRowParser

    @BeforeEach
    fun setUp() {
        fieldRowContentParser = mock(FieldRowContentParser::class.java)
        fieldValidRowParser = FieldValidRowParser(fieldRowContentParser)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @Test
    fun `parse a valid row`() {
        val newRow = ". *"
        `when`(fieldRowContentParser.tryToParseRowAndUpdate(any(FieldsParsingStatus::class.java)))
                .thenReturn(FieldsParsingStatusBuilder(
                        currentRowContent = newRow,
                        currentField = FieldFactory().make(
                                arrayOf(
                                        arrayOf("*", "."),
                                        arrayOf(".", "*")
                                )
                        )
                ).build())
        val newFieldsParsingStatus = fieldValidRowParser.parse(
                newRow,
                FieldsParsingStatusBuilder().build()
        )
        assertEquals(newFieldsParsingStatus.currentRowContent, newRow)
        assertEquals(newFieldsParsingStatus.currentField, FieldFactory().make(
                arrayOf(
                        arrayOf("*", "."),
                        arrayOf(".", "*")
                )
        ))
    }

    @Test
    fun `parse invalid row`() {
        val currentRowContent = "* *"
        val newFieldsParsingStatus = fieldValidRowParser.parse(
                "",
                FieldsParsingStatusBuilder(currentRowContent = currentRowContent).build()
        )
        assertEquals(newFieldsParsingStatus.currentRowContent, currentRowContent)
    }
}