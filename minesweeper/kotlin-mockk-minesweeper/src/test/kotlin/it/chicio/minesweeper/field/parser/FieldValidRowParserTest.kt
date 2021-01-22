package it.chicio.minesweeper.field.parser

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("FieldValidRowParser")
@ExtendWith(MockKExtension::class)
class FieldValidRowParserTest {
    @MockK
    private lateinit var fieldRowContentParser: FieldRowContentParser

    private lateinit var fieldValidRowParser: FieldValidRowParser

    @BeforeEach
    fun setUp() {
        fieldValidRowParser = FieldValidRowParser(fieldRowContentParser)
    }

    @Nested
    @DisplayName("parse")
    inner class Parse {
        @Test
        fun `a valid row`() {
            every { fieldRowContentParser.tryToParseRowAndUpdate(any()) } returns fieldsParsingStatus

            val newFieldsParsingStatus = fieldValidRowParser.parse(". *", FieldsParsingStatusBuilder().build())

            assertEquals(newFieldsParsingStatus.currentRowContent, ". *")
            assertEquals(newFieldsParsingStatus.currentField, FieldFactory().make(
                    arrayOf(
                            arrayOf("*", "."),
                            arrayOf(".", "*")
                    )
            ))
            verify(exactly = 1) { fieldRowContentParser.tryToParseRowAndUpdate(any()) }
        }

        @Test
        fun `an invalid row`() =
                assertEquals(
                        fieldValidRowParser.parse("", FieldsParsingStatusBuilder(currentRowContent = "* *").build()).currentRowContent,
                        "* *"
                )
    }

    companion object {
        private val fieldsParsingStatus = FieldsParsingStatusBuilder(
                currentRowContent = ". *",
                currentField = FieldFactory().make(
                        arrayOf(
                                arrayOf("*", "."),
                                arrayOf(".", "*")
                        )
                )
        ).build()
    }
}