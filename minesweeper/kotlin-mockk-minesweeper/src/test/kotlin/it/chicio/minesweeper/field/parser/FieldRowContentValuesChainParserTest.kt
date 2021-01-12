package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FieldRowContentValuesChainParserTest {
    private var fieldRowContentValuesChainParser: FieldRowContentValuesChainParser? = null
    @Before
    fun setUp() {
        fieldRowContentValuesChainParser = FieldRowContentValuesChainParser()
    }

    @Test
    fun canParseValidRow() {
        Assert.assertThat(fieldRowContentValuesChainParser!!.canParse("* . . *"), CoreMatchers.`is`(true))
    }

    @Test
    fun canNotParseOtherRows() {
        Assert.assertThat(fieldRowContentValuesChainParser!!.canParse("1 1"), CoreMatchers.`is`(false))
        Assert.assertThat(fieldRowContentValuesChainParser!!.canParse("0 0"), CoreMatchers.`is`(false))
    }

    @Test
    fun parseRow() {
        val fieldsParsingStatus = fieldRowContentValuesChainParser!!.parse(FieldsParsingStatusBuilder()
                .withCurrentField(FieldFactory().make(arrayOf(arrayOf("*", "."), arrayOf("", ""))))
                .withCurrentRow(1)
                .withCurrentRowContent("* *")
                .build()
        )
        Assert.assertThat(fieldsParsingStatus.currentField!![1, 0], CoreMatchers.`is`(CoreMatchers.equalTo("*")))
        Assert.assertThat(fieldsParsingStatus.currentField!![1, 1], CoreMatchers.`is`(CoreMatchers.equalTo("*")))
    }

    @Test(expected = RuntimeException::class)
    fun failParseRow() {
        fieldRowContentValuesChainParser!!.parse(FieldsParsingStatusBuilder()
                .withCurrentField(FieldFactory().make(arrayOf(arrayOf("*", "."), arrayOf("", ""))))
                .withCurrentRow(1)
                .withCurrentRowContent("* * *")
                .build()
        )
    }
}