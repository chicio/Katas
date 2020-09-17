package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

public class FieldsParserByDivingInputIntoRowsTest {

    private FieldRowParser fieldRowParser;
    private FieldsParserByDivingInputIntoRows fieldsParserByDivingInputIntoRows;

    @Before
    public void setUp() {
        fieldRowParser = mock(FieldRowParser.class);
        fieldsParserByDivingInputIntoRows = new FieldsParserByDivingInputIntoRows(fieldRowParser);
        when(fieldRowParser.parse(argThat(not("0 0")), ArgumentMatchers.any(FieldsParsingStatus.class)))
                .thenReturn(new FieldsParsingStatusBuilder().build());
    }

    @Test
    public void parseTermination() {
        when(fieldRowParser.parse(argThat(is("0 0")), ArgumentMatchers.any(FieldsParsingStatus.class)))
                .thenReturn(new FieldsParsingStatusBuilder().withFieldsParsed(new ArrayList<Field>()).build());
        FieldsParserByDivingInputIntoRows fieldsParserByDivingInputIntoRows =
                new FieldsParserByDivingInputIntoRows(fieldRowParser);

        List<Field> fields = fieldsParserByDivingInputIntoRows.parse("0 0");

        assertThat(fields, is(equalTo(Collections.<Field>emptyList())));
    }

    @Test
    public void parseAValidField() {
        when(fieldRowParser.parse(argThat(is("0 0")), ArgumentMatchers.any(FieldsParsingStatus.class)))
                .thenReturn(new FieldsParsingStatusBuilder().withFieldsParsed(
                                new ArrayList<Field>(Collections.singletonList(new Field(new String[][]{{"*"}})))
                ).build()
        );

        List<Field> fields = fieldsParserByDivingInputIntoRows.parse(
                "1 1" + System.getProperty("line.separator") +
                "*" + System.getProperty("line.separator") +
                "0 0"
        );

        assertThat(fields, is(equalTo(asList(new Field(new String[][]{{"*"}})))));
    }

    @Test
    public void parseTwoValidFields() {
        when(fieldRowParser.parse(argThat(is("0 0")), ArgumentMatchers.any(FieldsParsingStatus.class)))
                .thenReturn(
                new FieldsParsingStatusBuilder().withFieldsParsed(
                        new ArrayList<Field>(asList(new Field(new String[][]{{"*"}}), new Field(new String[][]{{"*"}})))
                ).build()
        );

        List<Field> fields = fieldsParserByDivingInputIntoRows.parse(
                "1 1" + System.getProperty("line.separator") +
                "*" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "1 1" + System.getProperty("line.separator") +
                "*" + System.getProperty("line.separator") +
                "0 0"
        );

        assertThat(fields, is(equalTo(asList(
                new Field(new String[][]{{"*"}}),
                new Field(new String[][]{{"*"}})
        ))));
    }
}
