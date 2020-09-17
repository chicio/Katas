package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldValidRowParserTest {
    private FieldRowContentParser fieldRowContentParser;
    private FieldValidRowParser fieldValidRowParser;

    @Before
    public void setUp() {
        fieldRowContentParser = mock(FieldRowContentParser.class);
        fieldValidRowParser = new FieldValidRowParser(fieldRowContentParser);
    }

    @Test
    public void parseAValidRow() {
        String newRow = ". *";
        when(fieldRowContentParser.tryToParseRowAndUpdate(ArgumentMatchers.any(FieldsParsingStatus.class)))
                .thenReturn(new FieldsParsingStatusBuilder()
                        .withCurrentRowContent(newRow)
                        .withCurrentField(new Field(new String[][]{{"*", "."}, {".", "*"}}))
                        .build()
                );

        FieldsParsingStatus newFieldsParsingStatus = fieldValidRowParser.parse(
            newRow,
            new FieldsParsingStatusBuilder().build()
        );

        assertThat(newFieldsParsingStatus.currentRowContent, is(equalTo(newRow)));
        assertThat(newFieldsParsingStatus.currentField, is(equalTo(new Field(new String[][]{{"*", "."}, {".", "*"}}))));
    }

    @Test
    public void parseInvalidRow() {
        String currentRowContent = "* *";
        FieldsParsingStatus newFieldsParsingStatus = fieldValidRowParser.parse(
            "",
            new FieldsParsingStatusBuilder().withCurrentRowContent(currentRowContent).build()
        );

        assertThat(newFieldsParsingStatus.currentRowContent, is(equalTo(currentRowContent)));
    }
}
