package it.chicio.minesweeper;

import it.chicio.minesweeper.field.Field;
import it.chicio.minesweeper.field.formatter.FieldsFormatter;
import it.chicio.minesweeper.field.resolver.FieldsResolver;
import it.chicio.minesweeper.field.parser.FieldsParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MinesweeperTest {
    private static final String TERMINATION = "0 0";
    private static final String FIELD =
            "3 5" + System.getProperty("line.separator") +
                    "* * . . ." + System.getProperty("line.separator") +
                    ". . . . ." + System.getProperty("line.separator") +
                    ". * . . ." + System.getProperty("line.separator");
    private final Field field = new Field(new String[][]{
            {"*", "*", ".", ".", "."},
            {".", ".", ".", ".", "."},
            {".", "*", ".", ".", "."}
    });
    private Field playedField = new Field(new String[][]{
            {"*", "*", "1", "0", "0"},
            {"3", "3", "2", "0", "0"},
            {"1", "*", "1", "0", "0"}
    });
    private final String FORMATTED_PLAYED_FIELD =
            "field #2:" + System.getProperty("line.separator") +
                    "* * 1 0 0" + System.getProperty("line.separator") +
                    "3 3 2 0 0" + System.getProperty("line.separator") +
                    "1 * 1 0 0" + System.getProperty("line.separator");
    private static final String ANOTHER_FIELD =
            "4 4" + System.getProperty("line.separator") +
                    "* . . ." + System.getProperty("line.separator") +
                    ". . . ." + System.getProperty("line.separator") +
                    ". * . ." + System.getProperty("line.separator") +
                    ". . . ." + System.getProperty("line.separator");
    private final Field anotherField = new Field(new String[][]{
            {"*", ".", ".", "."},
            {".", ".", ".", "."},
            {".", "*", ".", "."},
            {".", ".", ".", "."}
    });
    private Field anotherPlayedField = new Field(new String[][]{
            {"*", "1", "0", "0"},
            {"2", "2", "1", "0"},
            {"1", "*", "1", "0"},
            {"1", "1", "1", "0"}
    });
    private final String ANOTHER_FORMATTED_PLAYED_FIELD =
            "field #1:" + System.getProperty("line.separator") +
                    "* 1 0 0" + System.getProperty("line.separator") +
                    "2 2 1 0" + System.getProperty("line.separator") +
                    "1 * 1 0" + System.getProperty("line.separator") +
                    "1 1 1 0" + System.getProperty("line.separator");

    @Test
    public void shouldTerminateWhenReceive0and0Input() {
        FieldsParser fieldsParser = mock(FieldsParser.class);
        ArrayList<Field> fields = new ArrayList<Field>();
        when(fieldsParser.parse(FIELD + TERMINATION)).thenReturn(fields);
        FieldsResolver fieldsResolver = mock(FieldsResolver.class);
        when(fieldsResolver.resolve(fields)).thenReturn(fields);
        FieldsFormatter fieldsFormatter = mock(FieldsFormatter.class);
        when(fieldsFormatter.format(fields)).thenReturn("");
        Minesweeper minesweeper = new Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, "0 0");

        String gameResult = minesweeper.play();

        assertThat(gameResult, is(""));
    }

    @Test
    public void shouldPlayOneField() {
        List<Field> fields = singletonList(playedField);
        FieldsParser fieldsParser = mock(FieldsParser.class);
        when(fieldsParser.parse(FIELD + TERMINATION)).thenReturn(singletonList(field));
        FieldsResolver fieldsResolver = mock(FieldsResolver.class);
        when(fieldsResolver.resolve(singletonList(field))).thenReturn(fields);
        FieldsFormatter fieldsFormatter = mock(FieldsFormatter.class);
        when(fieldsFormatter.format(fields)).thenReturn(FORMATTED_PLAYED_FIELD);
        Minesweeper minesweeper = new Minesweeper(
                fieldsParser,
                fieldsResolver,
                fieldsFormatter,
                FIELD + TERMINATION
        );

        String gameResult = minesweeper.play();

        assertThat(gameResult, is(FORMATTED_PLAYED_FIELD));
    }

    @Test
    public void shouldPlayMoreThanOneField() {
        List<Field> fields = asList(playedField, anotherPlayedField);
        FieldsParser fieldsParser = mock(FieldsParser.class);
        when(fieldsParser.parse(FIELD + ANOTHER_FIELD + TERMINATION)).thenReturn(asList(field, anotherField));
        FieldsResolver fieldsResolver = mock(FieldsResolver.class);
        when(fieldsResolver.resolve(asList(field, anotherField))).thenReturn(fields);
        FieldsFormatter fieldsFormatter = mock(FieldsFormatter.class);
        when(fieldsFormatter.format(fields))
                .thenReturn(FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD);
        Minesweeper minesweeper =
                new Minesweeper(fieldsParser, fieldsResolver, fieldsFormatter, FIELD + ANOTHER_FIELD + TERMINATION);

        String gameResult = minesweeper.play();

        assertThat(
            gameResult,
            is(FORMATTED_PLAYED_FIELD + System.getProperty("line.separator") + ANOTHER_FORMATTED_PLAYED_FIELD)
        );
    }
}
