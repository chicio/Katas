package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class FieldsFormatterWithHeaderTest {
    private val field = Field(arrayOf(arrayOf("*", "1", "0", "0"), arrayOf("2", "2", "1", "0"), arrayOf("1", "*", "1", "0"), arrayOf("1", "1", "1", "0")))
    private val FORMATTED_FIELD = "* 1 0 0" + System.getProperty("line.separator") +
            "2 2 1 0" + System.getProperty("line.separator") +
            "1 * 1 0" + System.getProperty("line.separator") +
            "1 1 1 0" + System.getProperty("line.separator")
    private val FORMATTED_FIELD_WITH_HEADER = "field #1:" + System.getProperty("line.separator") +
            FORMATTED_FIELD
    private val anotherField = Field(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0")))
    private val ANOTHER_FORMATTED_FIELD = "* * 1 0 0" + System.getProperty("line.separator") +
            "3 3 2 0 0" + System.getProperty("line.separator") +
            "1 * 1 0 0" + System.getProperty("line.separator")
    private val ANOTHER_FORMATTED_FIELD_WITH_HEADER = "field #2:" + System.getProperty("line.separator") +
            ANOTHER_FORMATTED_FIELD

    @Test
    fun formatField() {
        val fieldFormatter = Mockito.mock(FieldFormatter::class.java)
        Mockito.`when`(fieldFormatter.format(field)).thenReturn(FORMATTED_FIELD)
        val fieldsFormatterWithHeader = FieldsFormatterWithHeader(fieldFormatter)
        val fieldsFormatted = fieldsFormatterWithHeader.format(listOf(field))
        Assert.assertThat(fieldsFormatted, CoreMatchers.`is`(FORMATTED_FIELD_WITH_HEADER))
    }

    @Test
    fun formatMultipleFields() {
        val fieldFormatter = Mockito.mock(FieldFormatter::class.java)
        Mockito.`when`(fieldFormatter.format(field)).thenReturn(FORMATTED_FIELD)
        Mockito.`when`(fieldFormatter.format(anotherField)).thenReturn(ANOTHER_FORMATTED_FIELD)
        val fieldsFormatterWithHeader = FieldsFormatterWithHeader(fieldFormatter)
        val fieldsFormatted = fieldsFormatterWithHeader.format(Arrays.asList(field, anotherField))
        Assert.assertThat(fieldsFormatted, CoreMatchers.`is`(FORMATTED_FIELD_WITH_HEADER + System.getProperty("line.separator") + ANOTHER_FORMATTED_FIELD_WITH_HEADER))
    }
}