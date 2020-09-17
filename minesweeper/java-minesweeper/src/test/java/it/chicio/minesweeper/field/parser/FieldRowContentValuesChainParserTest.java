package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldRowContentValuesChainParserTest {
    private FieldRowContentValuesChainParser fieldRowContentValuesChainParser;

    @Before
    public void setUp() {
        fieldRowContentValuesChainParser = new FieldRowContentValuesChainParser();
    }

    @Test
    public void canParseValidRow() {
        assertThat(fieldRowContentValuesChainParser.canParse("* . . *"), is(true));
    }

    @Test
    public void canNotParseOtherRows() {
        assertThat(fieldRowContentValuesChainParser.canParse("1 1"), is(false));
        assertThat(fieldRowContentValuesChainParser.canParse("0 0"), is(false));
    }

    @Test
    public void parseRow() {
        FieldsParsingStatus fieldsParsingStatus = fieldRowContentValuesChainParser.parse(new FieldsParsingStatusBuilder()
                .withCurrentField(new Field(new String[][]{{"*", "."}, {"", ""}}))
                .withCurrentRow(1)
                .withCurrentRowContent("* *")
                .build()
        );

        assertThat(fieldsParsingStatus.currentField.get(1, 0), is(equalTo("*")));
        assertThat(fieldsParsingStatus.currentField.get(1, 1), is(equalTo("*")));
    }

    @Test(expected = RuntimeException.class)
    public void failParseRow() {
        fieldRowContentValuesChainParser.parse(new FieldsParsingStatusBuilder()
                .withCurrentField(new Field(new String[][]{{"*", "."}, {"", ""}}))
                .withCurrentRow(1)
                .withCurrentRowContent("* * *")
                .build()
        );
    }
}
