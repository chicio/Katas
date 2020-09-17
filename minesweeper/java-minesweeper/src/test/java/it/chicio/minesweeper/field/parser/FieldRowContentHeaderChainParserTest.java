package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class FieldRowContentHeaderChainParserTest {
    private FieldRowContentHeaderChainParser fieldRowContentHeaderChainParser;

    @Before
    public void setUp() {
        fieldRowContentHeaderChainParser = new FieldRowContentHeaderChainParser();
    }

    @Test
    public void canParseValidHeaderRow() {
        assertThat(fieldRowContentHeaderChainParser.canParse("1 1"), is(true));
    }

    @Test
    public void canNotParseValidHeaderRow() {
        assertThat(fieldRowContentHeaderChainParser.canParse("1"), is(false));
        assertThat(fieldRowContentHeaderChainParser.canParse(""), is(false));
    }

    @Test
    public void parseHeader() {
        Field previousField = new Field(new String[][]{{"*"}});

        FieldsParsingStatus newFieldsParsingStatus = fieldRowContentHeaderChainParser.parse(
                new FieldsParsingStatusBuilder()
                        .withCurrentRowContent("2 2")
                        .withHeaderNumberOfRowsForCurrentField(1)
                        .withFieldsParsed(new ArrayList<Field>())
                        .withCurrentField(previousField)
                        .build()
        );

        assertThat(newFieldsParsingStatus.fieldsParsed.size(), is(1));
        assertThat(newFieldsParsingStatus.currentField.numberOfColumn(), is(2));
        assertThat(newFieldsParsingStatus.currentField.numberOfRows(), is(2));
        assertThat(newFieldsParsingStatus.currentField, is(not(previousField)));
    }

    @Test(expected = RuntimeException.class)
    public void failParseForInvalidField() {
        Field previousField = new Field(new String[][]{});

        fieldRowContentHeaderChainParser.parse(
                new FieldsParsingStatusBuilder()
                        .withCurrentRowContent("2 2")
                        .withCurrentField(new Field(new String[][]{{"*"}}))
                        .withHeaderNumberOfRowsForCurrentField(1)
                        .withFieldsParsed(new ArrayList<Field>())
                        .withCurrentField(previousField)
                        .build()
        );
    }
}
