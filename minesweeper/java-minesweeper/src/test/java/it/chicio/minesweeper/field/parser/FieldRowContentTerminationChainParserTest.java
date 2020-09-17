package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldRowContentTerminationChainParserTest {
    private FieldRowContentTerminationChainParser fieldRowContentTerminationChainParser;

    @Before
    public void setUp() {
        fieldRowContentTerminationChainParser = new FieldRowContentTerminationChainParser();
    }

    @Test
    public void canParseTerminationRow() {
        assertThat(fieldRowContentTerminationChainParser.canParse("0 0"), is(true));
    }

    @Test
    public void canNotParseTerminationRow() {
        assertThat(fieldRowContentTerminationChainParser.canParse("0"), is(false));
        assertThat(fieldRowContentTerminationChainParser.canParse("* *"), is(false));
    }

    @Test
    public void parseValidTerminationRow() {
        Field currentField = new Field(new String[][]{{"*"}});
        FieldsParsingStatus fieldsParsingStatus = fieldRowContentTerminationChainParser.parse(new FieldsParsingStatusBuilder()
                .withCurrentField(currentField)
                .withFieldsParsed(new ArrayList<Field>())
                .withCurrentRow(1)
                .build()
        );

        assertThat(fieldsParsingStatus.fieldsParsed.size(), is(1));
        assertThat(fieldsParsingStatus.fieldsParsed.get(0), is(equalTo(currentField)));
    }
}
