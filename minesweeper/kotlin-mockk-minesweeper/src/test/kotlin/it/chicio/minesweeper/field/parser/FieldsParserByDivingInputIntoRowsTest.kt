package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.hamcrest.MockitoHamcrest
import java.util.*

@DisplayName("FieldsParserByDivingInputIntoRows")
class FieldsParserByDivingInputIntoRowsTest {
    @Nested
    @DisplayName("parse")
    inner class Parse {
        private lateinit var fieldRowParser: FieldRowParser
        private lateinit var fieldsParserByDivingInputIntoRows: FieldsParserByDivingInputIntoRows

        @BeforeEach
        fun setUp() {
            fieldRowParser = mock(FieldRowParser::class.java)
            fieldsParserByDivingInputIntoRows = FieldsParserByDivingInputIntoRows(fieldRowParser)
        }

        @Test
        fun termination() {
            `when`(fieldRowParser.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                    .thenReturn(FieldsParsingStatusBuilder().build())

            val fields = fieldsParserByDivingInputIntoRows.parse("0 0")

            assertEquals(fields, emptyList<Field>())
        }

        @Test
        fun `a valid field`() {
            `when`(fieldRowParser.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                    .thenReturn(FieldsParsingStatusBuilder(fieldsParsed =
                    ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*")))))
                    ).build())
            `when`(fieldRowParser.parse(MockitoHamcrest.argThat(CoreMatchers.not("0 0")), any(FieldsParsingStatus::class.java)))
                    .thenReturn(FieldsParsingStatusBuilder().build())

            val fields = fieldsParserByDivingInputIntoRows.parse(
                    """
                1 1
                *
                0 0
                """.trimIndent()
            )

            assertEquals(fields, listOf(FieldFactory().make(arrayOf(arrayOf("*")))))
        }

        @Test
        fun `two valid fields`() {
            `when`(fieldRowParser.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                    .thenReturn(
                            FieldsParsingStatusBuilder(fieldsParsed =
                            ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*"))), FieldFactory().make(arrayOf(arrayOf("*")))))
                            ).build()
                    )
            `when`(fieldRowParser.parse(MockitoHamcrest.argThat(CoreMatchers.not("0 0")), any(FieldsParsingStatus::class.java)))
                    .thenReturn(FieldsParsingStatusBuilder().build())

            val fields = fieldsParserByDivingInputIntoRows.parse(
                    """
                1 1
                *
                
                1 1
                *
                0 0
                """.trimIndent()
            )
            assertEquals(fields, listOf(
                    FieldFactory().make(arrayOf(arrayOf("*"))),
                    FieldFactory().make(arrayOf(arrayOf("*")))
            ))
        }

        private fun <T> any(type: Class<T>): T = Mockito.any(type)
    }
}