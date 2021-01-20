package it.chicio.minesweeper

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verifySequence
import it.chicio.minesweeper.field.Field
import it.chicio.minesweeper.field.formatter.FieldsFormatter
import it.chicio.minesweeper.field.parser.FieldsParser
import it.chicio.minesweeper.field.resolver.FieldsResolver
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@DisplayName("Minesweeper")
@ExtendWith(MockKExtension::class)
class MinesweeperTest {
    @MockK
    private lateinit var fieldsParser: FieldsParser
    @MockK
    private lateinit var fieldsResolver: FieldsResolver
    @MockK
    private lateinit var fieldsFormatter: FieldsFormatter

    @Nested
    @DisplayName("should terminate")
    inner class ShouldTerminateTest {
        @Test
        fun `when receive 0 and 0 as input`() {
            val fields = emptyList<Field>()
            every { fieldsParser.parse(termination) } returns fields
            every { fieldsResolver.resolve(fields) } returns fields
            every { fieldsFormatter.format(fields) } returns ""

            val gameResult = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, "0 0").play()

            assertEquals(gameResult, "")
            verifySequence {
                fieldsParser.parse(termination)
                fieldsResolver.resolve(fields)
                fieldsFormatter.format(fields)
            }
        }
    }

    @Nested
    @DisplayName("should play")
    inner class ShouldPlayTest {
        @Test
        fun `one field`() {
            val fields = listOf(playedField)
            every { fieldsParser.parse(aField + termination) } returns listOf(field)
            every { fieldsResolver.resolve(listOf(field)) } returns fields
            every { fieldsFormatter.format(fields) } returns formattedPlayedField

            val gameResult = Minesweeper(
                    fieldsParser,
                    fieldsResolver,
                    fieldsFormatter,
                    aField + termination
            ).play()

            assertEquals(gameResult, formattedPlayedField)
            verifySequence {
                fieldsParser.parse(aField + termination)
                fieldsResolver.resolve(listOf(field))
                fieldsFormatter.format(fields)
            }
        }

        @Test
        fun `more than one field`() {
            val fields = listOf(playedField, anotherPlayedField)
            val fieldsParser = mock(FieldsParser::class.java)
            val fieldsResolver = mock(FieldsResolver::class.java)
            val fieldsFormatter = mock(FieldsFormatter::class.java)
            `when`(fieldsParser.parse(aField + yetAnotherField + termination)).thenReturn(listOf(field, anotherField))
            `when`(fieldsResolver.resolve(listOf(field, anotherField))).thenReturn(fields)
            `when`(fieldsFormatter.format(fields))
                    .thenReturn(formattedPlayedField + System.getProperty("line.separator") + anotherFormattedPlayedField)
            val minesweeper = Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, aField + yetAnotherField + termination)
            val gameResult = minesweeper.play()
            assertEquals(
                    gameResult,
                    formattedPlayedField + System.getProperty("line.separator") + anotherFormattedPlayedField
            )
        }
    }

    companion object {
        private const val termination = "0 0"
        private val aField = "3 5" + System.getProperty("line.separator") +
                "* * . . ." + System.getProperty("line.separator") +
                ". . . . ." + System.getProperty("line.separator") +
                ". * . . ." + System.getProperty("line.separator")
        private val yetAnotherField = "4 4" + System.getProperty("line.separator") +
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