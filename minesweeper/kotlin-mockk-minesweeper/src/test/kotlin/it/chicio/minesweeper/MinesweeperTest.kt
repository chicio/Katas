package it.chicio.minesweeper

import it.chicio.minesweeper.field.Field
import it.chicio.minesweeper.field.formatter.FieldsFormatter
import it.chicio.minesweeper.field.parser.FieldsParser
import it.chicio.minesweeper.field.resolver.FieldsResolver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class MinesweeperTest {
    private val field = FieldFactory().make(arrayOf(arrayOf("*", "*", ".", ".", "."), arrayOf(".", ".", ".", ".", "."), arrayOf(".", "*", ".", ".", ".")))
    private val playedField = FieldFactory().make(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0")))
    private val FORMATTED_PLAYED_FIELD = "field #2:" + System.getProperty("line.separator") +
            "* * 1 0 0" + System.getProperty("line.separator") +
            "3 3 2 0 0" + System.getProperty("line.separator") +
            "1 * 1 0 0" + System.getProperty("line.separator")
    private val anotherField = FieldFactory().make(arrayOf(arrayOf("*", ".", ".", "."), arrayOf(".", ".", ".", "."), arrayOf(".", "*", ".", "."), arrayOf(".", ".", ".", ".")))
    private val anotherPlayedField = FieldFactory().make(arrayOf(arrayOf("*", "1", "0", "0"), arrayOf("2", "2", "1", "0"), arrayOf("1", "*", "1", "0"), arrayOf("1", "1", "1", "0")))
    private val ANOTHER_FORMATTED_PLAYED_FIELD = "field #1:" + System.getProperty("line.separator") +
            "* 1 0 0" + System.getProperty("line.separator") +
            "2 2 1 0" + System.getProperty("line.separator") +
            "1 * 1 0" + System.getProperty("line.separator") +
            "1 1 1 0" + System.getProperty("line.separator")

    @Test
    fun shouldTerminateWhenReceive0and0Input() {
        val fieldsParser = Mockito.mock(FieldsParser::class.java)
        val fields = ArrayList<Field>()
        Mockito.`when`(fieldsParser.parse(FIELD + TERMINATION)).thenReturn(fields)
        val fieldsResolver = Mockito.mock(FieldsResolver::class.java)
        Mockito.`when`(fieldsResolver.resolve(fields)).thenReturn(fields)
        val fieldsFormatter = Mockito.mock(FieldsFormatter::class.java)
        Mockito.`when`(fieldsFormatter.format(fields)).thenReturn("")
        val minesweeper = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, "0 0")
        val gameResult = minesweeper.play()
        assertEquals(gameResult, "")
    }

    @Test
    fun shouldPlayOneField() {
        val fields = listOf(playedField)
        val fieldsParser = Mockito.mock(FieldsParser::class.java)
        Mockito.`when`(fieldsParser.parse(FIELD + TERMINATION)).thenReturn(listOf(field))
        val fieldsResolver = Mockito.mock(FieldsResolver::class.java)
        Mockito.`when`(fieldsResolver.resolve(listOf(field))).thenReturn(fields)
        val fieldsFormatter = Mockito.mock(FieldsFormatter::class.java)
        Mockito.`when`(fieldsFormatter.format(fields)).thenReturn(FORMATTED_PLAYED_FIELD)
        val minesweeper = Minesweeper(
                fieldsParser,
                fieldsResolver,
                fieldsFormatter,
                FIELD + TERMINATION
        )
        val gameResult = minesweeper.play()
        assertEquals(gameResult, FORMATTED_PLAYED_FIELD)
    }

    @Test
    fun shouldPlayMoreThanOneField() {
        val fields = Arrays.asList(playedField, anotherPlayedField)
        val fieldsParser = Mockito.mock(FieldsParser::class.java)
        Mockito.`when`(fieldsParser.parse(FIELD + ANOTHER_FIELD + TERMINATION)).thenReturn(Arrays.asList(field, anotherField))
        val fieldsResolver = Mockito.mock(FieldsResolver::class.java)
        Mockito.`when`(fieldsResolver.resolve(Arrays.asList(field, anotherField))).thenReturn(fields)
        val fieldsFormatter = Mockito.mock(FieldsFormatter::class.java)
        Mockito.`when`(fieldsFormatter.format(fields))
                .thenReturn(FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD)
        val minesweeper = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, FIELD + ANOTHER_FIELD + TERMINATION)
        val gameResult = minesweeper.play()
        assertEquals(
                gameResult,
                FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD
        )
    }

    companion object {
        private const val TERMINATION = "0 0"
        private val FIELD = "3 5" + System.getProperty("line.separator") +
                "* * . . ." + System.getProperty("line.separator") +
                ". . . . ." + System.getProperty("line.separator") +
                ". * . . ." + System.getProperty("line.separator")
        private val ANOTHER_FIELD = "4 4" + System.getProperty("line.separator") +
                "* . . ." + System.getProperty("line.separator") +
                ". . . ." + System.getProperty("line.separator") +
                ". * . ." + System.getProperty("line.separator") +
                ". . . ." + System.getProperty("line.separator")
    }
}