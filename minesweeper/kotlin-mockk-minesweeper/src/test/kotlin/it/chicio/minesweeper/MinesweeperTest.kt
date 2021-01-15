package it.chicio.minesweeper

import it.chicio.minesweeper.field.Field
import it.chicio.minesweeper.field.formatter.FieldsFormatter
import it.chicio.minesweeper.field.parser.FieldsParser
import it.chicio.minesweeper.field.resolver.FieldsResolver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

@DisplayName("Minesweeper")
class MinesweeperTest {
    @Nested
    @DisplayName("should terminate")
    inner class ShouldTerminateTest {
        @Test
        fun `when receive 0 and 0 as input`() {
            val fieldsParser = mock(FieldsParser::class.java)
            val fieldsResolver = mock(FieldsResolver::class.java)
            val fieldsFormatter = mock(FieldsFormatter::class.java)
            val fields = ArrayList<Field>()
            `when`(fieldsParser.parse("$FIELD$TERMINATION")).thenReturn(fields)
            `when`(fieldsResolver.resolve(fields)).thenReturn(fields)
            `when`(fieldsFormatter.format(fields)).thenReturn("")
            val minesweeper = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, "0 0")
            val gameResult = minesweeper.play()
            assertEquals(gameResult, "")
        }
    }

    @Nested
    @DisplayName("should play")
    inner class ShouldPlayTest {
        @Test
        fun `one field`() {
            val fields = listOf(playedField)
            val fieldsParser = mock(FieldsParser::class.java)
            val fieldsResolver = mock(FieldsResolver::class.java)
            val fieldsFormatter = mock(FieldsFormatter::class.java)
            `when`(fieldsParser.parse(FIELD + TERMINATION)).thenReturn(listOf(field))
            `when`(fieldsResolver.resolve(listOf(field))).thenReturn(fields)
            `when`(fieldsFormatter.format(fields)).thenReturn(formattedPlayedField)
            val minesweeper = Minesweeper(
                    fieldsParser,
                    fieldsResolver,
                    fieldsFormatter,
                    FIELD + TERMINATION
            )
            val gameResult = minesweeper.play()
            assertEquals(gameResult, formattedPlayedField)
        }

        @Test
        fun `more than one field`() {
            val fields = listOf(playedField, anotherPlayedField)
            val fieldsParser = mock(FieldsParser::class.java)
            val fieldsResolver = mock(FieldsResolver::class.java)
            val fieldsFormatter = mock(FieldsFormatter::class.java)
            `when`(fieldsParser.parse(FIELD + ANOTHER_FIELD + TERMINATION)).thenReturn(listOf(field, anotherField))
            `when`(fieldsResolver.resolve(listOf(field, anotherField))).thenReturn(fields)
            `when`(fieldsFormatter.format(fields))
                    .thenReturn(formattedPlayedField + System.getProperty("line.separator") + anotherFormattedPlayedField)
            val minesweeper = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, FIELD + ANOTHER_FIELD + TERMINATION)
            val gameResult = minesweeper.play()
            assertEquals(
                    gameResult,
                    formattedPlayedField + System.getProperty("line.separator") + anotherFormattedPlayedField
            )
        }
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
        private val field = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", ".", ".", "."),
                        arrayOf(".", ".", ".", ".", "."),
                        arrayOf(".", "*", ".", ".", ".")
                )
        )
        private val playedField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "*", "1", "0", "0"),
                        arrayOf("3", "3", "2", "0", "0"),
                        arrayOf("1", "*", "1", "0", "0")
                )
        )
        private const val formattedPlayedField =
            """
            field #2:
            * * 1 0 0
            3 3 2 0 0
            1 * 1 0 0
            """
        private val anotherField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", ".", ".", "."),
                        arrayOf(".", ".", ".", "."),
                        arrayOf(".", "*", ".", "."),
                        arrayOf(".", ".", ".", ".")
                )
        )
        private val anotherPlayedField = FieldFactory().make(
                arrayOf(
                        arrayOf("*", "1", "0", "0"),
                        arrayOf("2", "2", "1", "0"),
                        arrayOf("1", "*", "1", "0"),
                        arrayOf("1", "1", "1", "0")
                )
        )
        private const val anotherFormattedPlayedField =
            """
            field #1:
            * 1 0 0
            2 2 1 0
            1 * 1 0
            1 1 1 0
            """
    }
}