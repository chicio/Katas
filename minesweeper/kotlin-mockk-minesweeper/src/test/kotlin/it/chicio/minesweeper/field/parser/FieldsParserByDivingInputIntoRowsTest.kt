package it.chicio.minesweeper.field.parser

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import it.chicio.minesweeper.field.Field
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@DisplayName("FieldsParserByDivingInputIntoRows")
@ExtendWith(MockKExtension::class)
class FieldsParserByDivingInputIntoRowsTest {
    @Nested
    @DisplayName("parse")
    inner class Parse {
        @MockK
        private lateinit var fieldRowParser: FieldRowParser

        private lateinit var fieldsParserByDivingInputIntoRows: FieldsParserByDivingInputIntoRows

        @BeforeEach
        fun setUp() {
            fieldsParserByDivingInputIntoRows = FieldsParserByDivingInputIntoRows(fieldRowParser)
        }

        @Test
        fun termination() {
            every { fieldRowParser.parse(eq("0 0"), any()) } returns FieldsParsingStatusBuilder().build()

            val fields = fieldsParserByDivingInputIntoRows.parse("0 0")

            assertEquals(fields, emptyList<Field>())
            verify(exactly = 1) { fieldRowParser.parse(eq("0 0"), any()) }
        }

        @Test
        fun `a valid field`() {
            every { fieldRowParser.parse(eq("0 0"), any()) } returns FieldsParsingStatusBuilder(
                    fieldsParsed = ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*")))))
            ).build()
            every { fieldRowParser.parse(neq("0 0"), any()) } returns FieldsParsingStatusBuilder().build()

            val fields = fieldsParserByDivingInputIntoRows.parse(
                    """
                1 1
                *
                0 0
                """.trimIndent()
            )

            assertEquals(fields, listOf(FieldFactory().make(arrayOf(arrayOf("*")))))
            verify(exactly = 1) { fieldRowParser.parse(eq("0 0"), any())  }
            verify { fieldRowParser.parse(neq("0 0"), any())  }
        }

        @Test
        fun `two valid fields`() {
            every { fieldRowParser.parse(eq("0 0"), any()) } returns FieldsParsingStatusBuilder(
                    fieldsParsed = ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*"))), FieldFactory().make(arrayOf(arrayOf("*")))))
            ).build()
            every { fieldRowParser.parse(neq("0 0"), any()) } returns FieldsParsingStatusBuilder().build()

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
            verify(exactly = 1) { fieldRowParser.parse(eq("0 0"), any())  }
            verify { fieldRowParser.parse(neq("0 0"), any())  }
        }
    }
}