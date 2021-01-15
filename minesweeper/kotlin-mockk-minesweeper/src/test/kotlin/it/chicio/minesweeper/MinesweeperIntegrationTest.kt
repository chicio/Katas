package it.chicio.minesweeper

import it.chicio.minesweeper.field.formatter.FieldFormatterWithNewlines
import it.chicio.minesweeper.field.formatter.FieldsFormatterWithHeader
import it.chicio.minesweeper.field.parser.*
import it.chicio.minesweeper.field.resolver.FieldResolverByIteratingThroughEachValue
import it.chicio.minesweeper.field.resolver.FieldsResolverByIteratingThroughThem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MinesweeperIntegrationTest {
    private val FORMATTED_PLAYED_FIELD = "field #1:" + System.getProperty("line.separator") +
            "* * 1 0 0" + System.getProperty("line.separator") +
            "3 3 2 0 0" + System.getProperty("line.separator") +
            "1 * 1 0 0" + System.getProperty("line.separator")
    private val ANOTHER_FORMATTED_PLAYED_FIELD = "field #2:" + System.getProperty("line.separator") +
            "* 1 0 0" + System.getProperty("line.separator") +
            "2 2 1 0" + System.getProperty("line.separator") +
            "1 * 1 0" + System.getProperty("line.separator") +
            "1 1 1 0" + System.getProperty("line.separator")
    private val playedGamed = FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD
    @Test
    fun resolveFields() {
        val fieldTerminationParser = FieldRowContentTerminationChainParser()
        val fieldHeaderParser = FieldRowContentHeaderChainParser()
        val fieldRowValuesParser = FieldRowContentValuesChainParser()
        fieldTerminationParser.setNextFieldRowContentChainParser(fieldHeaderParser)
        fieldHeaderParser.setNextFieldRowContentChainParser(fieldRowValuesParser)
        val minesweeper = Minesweeper(
                FieldsParserByDivingInputIntoRows(FieldValidRowParser(fieldTerminationParser)),
                FieldsResolverByIteratingThroughThem(FieldResolverByIteratingThroughEachValue()),
                FieldsFormatterWithHeader(FieldFormatterWithNewlines()),
                FIELD + System.getProperty("line.separator") + ANOTHER_FIELD + "0 0"
        )
        val gameResult = minesweeper.play()
        assertEquals(gameResult, playedGamed)
    }

    companion object {
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