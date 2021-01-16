package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.FieldFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FieldsFormatterWithHeaderTest {

    @Test
    fun formatField() {
        val fieldFormatter = mock(FieldFormatter::class.java)
        `when`(fieldFormatter.format(FIELD)).thenReturn(FORMATTED_FIELD)
        val fieldsFormatterWithHeader = FieldsFormatterWithHeader(fieldFormatter)
        val fieldsFormatted = fieldsFormatterWithHeader.format(listOf(FIELD))
        assertEquals(fieldsFormatted, FORMATTED_FIELD_WITH_HEADER)
    }

    @Test
    fun formatMultipleFields() {
        val fieldFormatter = mock(FieldFormatter::class.java)
        `when`(fieldFormatter.format(FIELD)).thenReturn(FORMATTED_FIELD)
        `when`(fieldFormatter.format(ANOTHER_FIELD)).thenReturn(ANOTHER_FORMATTED_FIELD)
        val fieldsFormatterWithHeader = FieldsFormatterWithHeader(fieldFormatter)
        val fieldsFormatted = fieldsFormatterWithHeader.format(listOf(FIELD, ANOTHER_FIELD))
        assertEquals(fieldsFormatted, FORMATTED_FIELD_WITH_HEADER + System.getProperty("line.separator") + ANOTHER_FORMATTED_FIELD_WITH_HEADER)
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
        private const val FORMATTED_FIELD =
                """
                * 1 0 0
                2 2 1 0
                1 * 1 0
                1 1 1 0
        
                """
        private const val FORMATTED_FIELD_WITH_HEADER =
                """
                field #1:
                $FORMATTED_FIELD
                """
        private val ANOTHER_FIELD = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", "1", "0", "0"),
                        arrayOf("3", "3", "2", "0", "0"),
                        arrayOf("1", "*", "1", "0", "0")
                )
        )
        private const val ANOTHER_FORMATTED_FIELD =
                """
                * * 1 0 0
                3 3 2 0 0
                1 * 1 0 0
                
                """
        private val ANOTHER_FORMATTED_FIELD_WITH_HEADER =
                """
                field #2:
                $ANOTHER_FORMATTED_FIELD
                """
    }
}