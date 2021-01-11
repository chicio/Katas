package it.chicio.minesweeper.field.formatter;

import it.chicio.minesweeper.field.Field;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldFormatterWithNewlinesTest {
    @Test
    public void formatField() {
        FieldFormatterWithNewlines fieldFormatterWithNewlines = new FieldFormatterWithNewlines();

        String formattedField = fieldFormatterWithNewlines.format(new Field(new String[][]{
                {"*", "1", "0", "0"},
                {"2", "2", "1", "0"},
                {"1", "*", "1", "0"},
                {"1", "1", "1", "0"}
        }));

        assertThat(formattedField, is(
                "* 1 0 0" + System.getProperty("line.separator") +
                        "2 2 1 0" + System.getProperty("line.separator") +
                        "1 * 1 0" + System.getProperty("line.separator") +
                        "1 1 1 0" + System.getProperty("line.separator")
        ));
    }
}
