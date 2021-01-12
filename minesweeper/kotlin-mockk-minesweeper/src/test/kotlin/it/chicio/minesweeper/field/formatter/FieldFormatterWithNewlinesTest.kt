package it.chicio.minesweeper.field.formatter

import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class FieldFormatterWithNewlinesTest {
    @Test
    fun formatField() {
        val fieldFormatterWithNewlines = FieldFormatterWithNewlines()
        val formattedField = fieldFormatterWithNewlines.format(Field(arrayOf(arrayOf("*", "1", "0", "0"), arrayOf("2", "2", "1", "0"), arrayOf("1", "*", "1", "0"), arrayOf("1", "1", "1", "0"))))
        Assert.assertThat(formattedField, CoreMatchers.`is`(
                "* 1 0 0" + System.getProperty("line.separator") +
                        "2 2 1 0" + System.getProperty("line.separator") +
                        "1 * 1 0" + System.getProperty("line.separator") +
                        "1 1 1 0" + System.getProperty("line.separator")
        ))
    }
}