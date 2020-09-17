package it.chicio.minesweeper.field.formatter;

import it.chicio.minesweeper.field.Field;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldsFormatterWithHeaderTest {
    private Field field = new Field(new String[][]{
            {"*", "1", "0", "0"},
            {"2", "2", "1", "0"},
            {"1", "*", "1", "0"},
            {"1", "1", "1", "0"}
    });
    private final String FORMATTED_FIELD =
            "* 1 0 0" + System.getProperty("line.separator") +
            "2 2 1 0" + System.getProperty("line.separator") +
            "1 * 1 0" + System.getProperty("line.separator") +
            "1 1 1 0" + System.getProperty("line.separator");
    private final String FORMATTED_FIELD_WITH_HEADER =
            "field #1:" + System.getProperty("line.separator") +
                    FORMATTED_FIELD;
    private Field anotherField = new Field(new String[][]{
            {"*", "*", "1", "0", "0"},
            {"3", "3", "2", "0", "0"},
            {"1", "*", "1", "0", "0"}
    });
    private final String ANOTHER_FORMATTED_FIELD =
            "* * 1 0 0" + System.getProperty("line.separator") +
            "3 3 2 0 0" + System.getProperty("line.separator") +
            "1 * 1 0 0" + System.getProperty("line.separator");
    private final String ANOTHER_FORMATTED_FIELD_WITH_HEADER =
            "field #2:" + System.getProperty("line.separator") +
            ANOTHER_FORMATTED_FIELD;

    @Test
    public void formatField() {
        FieldFormatter fieldFormatter = mock(FieldFormatter.class);
        when(fieldFormatter.format(field)).thenReturn(FORMATTED_FIELD);
        FieldsFormatterWithHeader fieldsFormatterWithHeader = new FieldsFormatterWithHeader(fieldFormatter);

        String fieldsFormatted = fieldsFormatterWithHeader.format(Collections.singletonList(field));

        assertThat(fieldsFormatted, is(FORMATTED_FIELD_WITH_HEADER));
    }

    @Test
    public void formatMultipleFields() {
        FieldFormatter fieldFormatter = mock(FieldFormatter.class);
        when(fieldFormatter.format(field)).thenReturn(FORMATTED_FIELD);
        when(fieldFormatter.format(anotherField)).thenReturn(ANOTHER_FORMATTED_FIELD);
        FieldsFormatterWithHeader fieldsFormatterWithHeader = new FieldsFormatterWithHeader(fieldFormatter);

        String fieldsFormatted = fieldsFormatterWithHeader.format(asList(field, anotherField));

        assertThat(fieldsFormatted, is(FORMATTED_FIELD_WITH_HEADER + System.getProperty("line.separator") + ANOTHER_FORMATTED_FIELD_WITH_HEADER));
    }
}
