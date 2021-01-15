package it.chicio.minesweeper.field.parser

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.FieldsParsingStatusBuilder
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.hamcrest.MockitoHamcrest
import java.util.*

class FieldsParserByDivingInputIntoRowsTest {
    private var fieldRowParser: FieldRowParser? = null
    private var fieldsParserByDivingInputIntoRows: FieldsParserByDivingInputIntoRows? = null
    @Before
    fun setUp() {
        fieldRowParser = Mockito.mock(FieldRowParser::class.java)
        fieldsParserByDivingInputIntoRows = FieldsParserByDivingInputIntoRows(fieldRowParser!!)
        Mockito.`when`(fieldRowParser!!.parse(MockitoHamcrest.argThat(CoreMatchers.not("0 0")), any(FieldsParsingStatus::class.java)))
                .thenReturn(FieldsParsingStatusBuilder().build())
    }

    @Test
    fun parseTermination() {
        Mockito.`when`(fieldRowParser!!.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                .thenReturn(FieldsParsingStatusBuilder().build())
        val fieldsParserByDivingInputIntoRows = FieldsParserByDivingInputIntoRows(fieldRowParser!!)
        val fields = fieldsParserByDivingInputIntoRows.parse("0 0")
        Assert.assertThat(fields, CoreMatchers.`is`(CoreMatchers.equalTo(emptyList())))
    }

    @Test
    fun parseAValidField() {
        Mockito.`when`(fieldRowParser!!.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                .thenReturn(FieldsParsingStatusBuilder(fieldsParsed =
                        ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*")))))
                ).build())
        val fields = fieldsParserByDivingInputIntoRows!!.parse(
                "1 1" + System.getProperty("line.separator") +
                        "*" + System.getProperty("line.separator") +
                        "0 0"
        )
        Assert.assertThat(fields, CoreMatchers.`is`(CoreMatchers.equalTo(Arrays.asList(FieldFactory().make(arrayOf(arrayOf("*")))))))
    }

    @Test
    fun parseTwoValidFields() {
        Mockito.`when`(fieldRowParser!!.parse(MockitoHamcrest.argThat(CoreMatchers.`is`("0 0")), any(FieldsParsingStatus::class.java)))
                .thenReturn(
                        FieldsParsingStatusBuilder(fieldsParsed =
                                ArrayList(listOf(FieldFactory().make(arrayOf(arrayOf("*"))), FieldFactory().make(arrayOf(arrayOf("*")))))
                        ).build()
                )
        val fields = fieldsParserByDivingInputIntoRows!!.parse(
                "1 1" + System.getProperty("line.separator") +
                        "*" + System.getProperty("line.separator") +
                        System.getProperty("line.separator") +
                        "1 1" + System.getProperty("line.separator") +
                        "*" + System.getProperty("line.separator") +
                        "0 0"
        )
        Assert.assertThat(fields, CoreMatchers.`is`(CoreMatchers.equalTo(Arrays.asList(
                FieldFactory().make(arrayOf(arrayOf("*"))),
                FieldFactory().make(arrayOf(arrayOf("*")))
        ))))
    }

    private fun <T> any(type: Class<T>): T = Mockito.any(type)
}