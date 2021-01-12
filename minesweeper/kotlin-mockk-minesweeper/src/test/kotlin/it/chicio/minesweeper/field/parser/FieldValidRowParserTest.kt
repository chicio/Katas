package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import it.chicio.minesweeper.field.parser.FieldsParsingStatus
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FieldValidRowParserTest {
    private var fieldRowContentParser: FieldRowContentParser? = null
    private var fieldValidRowParser: FieldValidRowParser? = null
    @Before
    fun setUp() {
        fieldRowContentParser = mock(FieldRowContentParser::class.java)
        fieldValidRowParser = FieldValidRowParser(fieldRowContentParser!!)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

    @Test
    fun parseAValidRow() {
        val newRow = ". *"
        `when`(fieldRowContentParser!!.tryToParseRowAndUpdate(any(FieldsParsingStatus::class.java)))
                .thenReturn(FieldsParsingStatusBuilder()
                        .withCurrentRowContent(newRow)
                        .withCurrentField(Field(arrayOf(arrayOf("*", "."), arrayOf(".", "*"))))
                        .build()
                )
        val newFieldsParsingStatus = fieldValidRowParser!!.parse(
                newRow,
                FieldsParsingStatusBuilder().build()
        )
        Assert.assertThat(newFieldsParsingStatus!!.currentRowContent, CoreMatchers.`is`(CoreMatchers.equalTo(newRow)))
        Assert.assertThat(newFieldsParsingStatus.currentField, CoreMatchers.`is`(CoreMatchers.equalTo(Field(arrayOf(arrayOf("*", "."), arrayOf(".", "*"))))))
    }

    @Test
    fun parseInvalidRow() {
        val currentRowContent = "* *"
        val newFieldsParsingStatus = fieldValidRowParser!!.parse(
                "",
                FieldsParsingStatusBuilder().withCurrentRowContent(currentRowContent).build()
        )
        Assert.assertThat(newFieldsParsingStatus!!.currentRowContent, CoreMatchers.`is`(CoreMatchers.equalTo(currentRowContent)))
    }
}