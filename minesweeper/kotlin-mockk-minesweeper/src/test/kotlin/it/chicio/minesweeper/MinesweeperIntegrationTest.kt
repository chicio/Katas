package it.chicio.minesweeper

import it.chicio.minesweeper.field.formatter.FieldFormatterWithNewlines
import it.chicio.minesweeper.field.formatter.FieldsFormatterWithHeader
import it.chicio.minesweeper.field.parser.*
import it.chicio.minesweeper.field.resolver.FieldResolverByIteratingThroughEachValue
import it.chicio.minesweeper.field.resolver.FieldsResolverByIteratingThroughThem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Minesweeper Integration")
class MinesweeperIntegrationTest {
    @Test
    fun `resolve fields`() {
        val fieldTerminationParser = FieldRowContentTerminationChainParser()
        val fieldHeaderParser = FieldRowContentHeaderChainParser()
        val fieldRowValuesParser = FieldRowContentValuesChainParser()
        fieldTerminationParser.setNextFieldRowContentChainParser(fieldHeaderParser)
        fieldHeaderParser.setNextFieldRowContentChainParser(fieldRowValuesParser)
        val minesweeper = Minesweeper(
                FieldsParserByDivingInputIntoRows(FieldValidRowParser(fieldTerminationParser)),
                FieldsResolverByIteratingThroughThem(FieldResolverByIteratingThroughEachValue()),
                FieldsFormatterWithHeader(FieldFormatterWithNewlines()),
                fields
        )
        val gameResult = minesweeper.play()
        assertEquals(gameResult, playedGamed)
    }

    companion object {
        private val fields =
                """
                3 5
                * * . . .
                . . . . .
                . * . . .
                
                4 4
                * . . .
                . . . .
                . * . .
                . . . .
                0 0
                """.trimIndent()
        private val playedGamed =
                """
                field #1:
                * * 1 0 0
                3 3 2 0 0
                1 * 1 0 0
                
                field #2:
                * 1 0 0
                2 2 1 0
                1 * 1 0
                1 1 1 0
                
                """.trimIndent()
    }
}