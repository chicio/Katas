package it.chicio.minesweeper.field.formatter

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import it.chicio.minesweeper.FieldFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("FieldsFormatterWithHeader")
@ExtendWith(MockKExtension::class)
class FieldsFormatterWithHeaderTest {
    @MockK
    private lateinit var fieldFormatter: FieldFormatter

    private lateinit var fieldsFormatterWithHeader: FieldsFormatterWithHeader

    @BeforeEach
    internal fun setUp() {
        fieldsFormatterWithHeader = FieldsFormatterWithHeader(fieldFormatter)
    }

    @Test
    fun `format field`() {
        every { fieldFormatter.format(FIELD) } returns FORMATTED_FIELD

        val fieldsFormatted: String = fieldsFormatterWithHeader.format(listOf(FIELD))

        assertEquals(FORMATTED_FIELD_WITH_HEADER, fieldsFormatted)
        verify(exactly = 1) { fieldFormatter.format(FIELD) }
    }

    @Test
    fun `format multiple fields`() {
        every { fieldFormatter.format(FIELD) } returns FORMATTED_FIELD
        every { fieldFormatter.format(ANOTHER_FIELD) } returns ANOTHER_FORMATTED_FIELD

        val fieldsFormatted = fieldsFormatterWithHeader.format(listOf(FIELD, ANOTHER_FIELD))

        assertEquals(
                FORMATTED_FIELD_WITH_HEADER + System.getProperty("line.separator") + ANOTHER_FORMATTED_FIELD_WITH_HEADER,
                fieldsFormatted
        )
        verify(exactly = 1) { fieldFormatter.format(FIELD) }
        verify(exactly = 1) { fieldFormatter.format(ANOTHER_FIELD) }
    }

    companion object {
        private val FIELD = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "1", "0", "0"),
                        arrayOf("2", "2", "1", "0"),
                        arrayOf("1", "*", "1", "0"),
                        arrayOf("1", "1", "1", "0")
                )
        )
        private val FORMATTED_FIELD = """
                * 1 0 0
                2 2 1 0
                1 * 1 0
                1 1 1 0
        
                """.trimIndent()
        private val FORMATTED_FIELD_WITH_HEADER =
                """
                field #1:
                * 1 0 0
                2 2 1 0
                1 * 1 0
                1 1 1 0
                
                """.trimIndent()
        private val ANOTHER_FIELD = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", "1", "0", "0"),
                        arrayOf("3", "3", "2", "0", "0"),
                        arrayOf("1", "*", "1", "0", "0")
                )
        )
        private val ANOTHER_FORMATTED_FIELD =
                """
                * * 1 0 0
                3 3 2 0 0
                1 * 1 0 0
                
                """.trimIndent()
        private val ANOTHER_FORMATTED_FIELD_WITH_HEADER =
                """
                field #2:
                * * 1 0 0
                3 3 2 0 0
                1 * 1 0 0

                """.trimIndent()
    }
}