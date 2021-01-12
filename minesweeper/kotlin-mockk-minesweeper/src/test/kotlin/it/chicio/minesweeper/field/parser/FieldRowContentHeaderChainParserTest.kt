package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class FieldRowContentHeaderChainParserTest {
    private var fieldRowContentHeaderChainParser: FieldRowContentHeaderChainParser? = null
    @Before
    fun setUp() {
        fieldRowContentHeaderChainParser = FieldRowContentHeaderChainParser()
    }

    @Test
    fun canParseValidHeaderRow() {
        Assert.assertThat(fieldRowContentHeaderChainParser!!.canParse("1 1"), CoreMatchers.`is`(true))
    }

    @Test
    fun canNotParseValidHeaderRow() {
        Assert.assertThat(fieldRowContentHeaderChainParser!!.canParse("1"), CoreMatchers.`is`(false))
        Assert.assertThat(fieldRowContentHeaderChainParser!!.canParse(""), CoreMatchers.`is`(false))
    }

    @Test
    fun parseHeader() {
        val previousField = Field(arrayOf(arrayOf("*")))
        val newFieldsParsingStatus = fieldRowContentHeaderChainParser!!.parse(
                FieldsParsingStatusBuilder()
                        .withCurrentRowContent("2 2")
                        .withHeaderNumberOfRowsForCurrentField(1)
                        .withFieldsParsed(ArrayList())
                        .withCurrentField(previousField)
                        .build()
        )
        Assert.assertThat(newFieldsParsingStatus.fieldsParsed!!.size, CoreMatchers.`is`(1))
        Assert.assertThat(newFieldsParsingStatus.currentField!!.numberOfColumn(), CoreMatchers.`is`(2))
        Assert.assertThat(newFieldsParsingStatus.currentField!!.numberOfRows(), CoreMatchers.`is`(2))
        Assert.assertThat(newFieldsParsingStatus.currentField, CoreMatchers.`is`(CoreMatchers.not(previousField)))
    }

    @Test(expected = RuntimeException::class)
    fun failParseForInvalidField() {
        val previousField = Field(arrayOf())
        fieldRowContentHeaderChainParser!!.parse(
                FieldsParsingStatusBuilder()
                        .withCurrentRowContent("2 2")
                        .withCurrentField(Field(arrayOf(arrayOf("*"))))
                        .withHeaderNumberOfRowsForCurrentField(1)
                        .withFieldsParsed(ArrayList())
                        .withCurrentField(previousField)
                        .build()
        )
    }
}