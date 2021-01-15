package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class FieldRowContentTerminationChainParserTest {
    private var fieldRowContentTerminationChainParser: FieldRowContentTerminationChainParser? = null
    @Before
    fun setUp() {
        fieldRowContentTerminationChainParser = FieldRowContentTerminationChainParser()
    }

    @Test
    fun canParseTerminationRow() {
        Assert.assertThat(fieldRowContentTerminationChainParser!!.canParse("0 0"), CoreMatchers.`is`(true))
    }

    @Test
    fun canNotParseTerminationRow() {
        Assert.assertThat(fieldRowContentTerminationChainParser!!.canParse("0"), CoreMatchers.`is`(false))
        Assert.assertThat(fieldRowContentTerminationChainParser!!.canParse("* *"), CoreMatchers.`is`(false))
    }

    @Test
    fun parseValidTerminationRow() {
        val currentField = FieldFactory().make(arrayOf(arrayOf("*")))
        val fieldsParsingStatus = fieldRowContentTerminationChainParser!!.parse(FieldsParsingStatusBuilder(
                currentField = currentField,
                currentRow = 1
        ).build())
        Assert.assertThat(fieldsParsingStatus.fieldsParsed!!.size, CoreMatchers.`is`(1))
        Assert.assertThat(fieldsParsingStatus.fieldsParsed!![0], CoreMatchers.`is`(CoreMatchers.equalTo(currentField)))
    }
}