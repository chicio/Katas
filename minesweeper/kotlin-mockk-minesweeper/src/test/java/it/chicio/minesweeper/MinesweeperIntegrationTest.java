package it.chicio.minesweeper;

import it.chicio.minesweeper.field.formatter.FieldFormatterWithNewlines;
import it.chicio.minesweeper.field.formatter.FieldsFormatterWithHeader;
import it.chicio.minesweeper.field.parser.*;
import it.chicio.minesweeper.field.resolver.FieldResolverByIteratingThroughEachValue;
import it.chicio.minesweeper.field.resolver.FieldsResolverByIteratingThroughThem;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MinesweeperIntegrationTest {
    private static final String FIELD =
            "3 5" + System.getProperty("line.separator") +
            "* * . . ." + System.getProperty("line.separator") +
            ". . . . ." + System.getProperty("line.separator") +
            ". * . . ." + System.getProperty("line.separator");
    private static final String ANOTHER_FIELD =
            "4 4" + System.getProperty("line.separator") +
            "* . . ." + System.getProperty("line.separator") +
            ". . . ." + System.getProperty("line.separator") +
            ". * . ." + System.getProperty("line.separator") +
            ". . . ." + System.getProperty("line.separator");
    private final String FORMATTED_PLAYED_FIELD =
            "field #1:" + System.getProperty("line.separator") +
            "* * 1 0 0" + System.getProperty("line.separator") +
            "3 3 2 0 0" + System.getProperty("line.separator") +
            "1 * 1 0 0" + System.getProperty("line.separator");
    private final String ANOTHER_FORMATTED_PLAYED_FIELD =
            "field #2:" + System.getProperty("line.separator") +
            "* 1 0 0" + System.getProperty("line.separator") +
            "2 2 1 0" + System.getProperty("line.separator") +
            "1 * 1 0" + System.getProperty("line.separator") +
            "1 1 1 0" + System.getProperty("line.separator");
    private final String playedGamed =
            FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD;

    @Test
    public void resolveFields() {
        FieldRowContentTerminationChainParser fieldTerminationParser = new FieldRowContentTerminationChainParser();
        FieldRowContentHeaderChainParser fieldHeaderParser = new FieldRowContentHeaderChainParser();
        FieldRowContentValuesChainParser fieldRowValuesParser = new FieldRowContentValuesChainParser();
        fieldTerminationParser.setNextFieldRowContentChainParser(fieldHeaderParser);
        fieldHeaderParser.setNextFieldRowContentChainParser(fieldRowValuesParser);
        Minesweeper minesweeper = new Minesweeper(
                new FieldsParserByDivingInputIntoRows(new FieldValidRowParser(fieldTerminationParser)),
                new FieldsResolverByIteratingThroughThem(new FieldResolverByIteratingThroughEachValue()),
                new FieldsFormatterWithHeader(new FieldFormatterWithNewlines()),
                FIELD + System.getProperty("line.separator") + ANOTHER_FIELD + "0 0"
        );

        String gameResult = minesweeper.play();

        assertThat(gameResult, is(equalTo(playedGamed)));
    }
}
