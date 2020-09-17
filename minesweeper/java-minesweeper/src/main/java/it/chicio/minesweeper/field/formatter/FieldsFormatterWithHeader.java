package it.chicio.minesweeper.field.formatter;

import it.chicio.minesweeper.field.Field;

import java.util.List;

public class FieldsFormatterWithHeader implements FieldsFormatter {
    private FieldFormatter fieldFormatter;

    public FieldsFormatterWithHeader(FieldFormatter fieldFormatter) {
        this.fieldFormatter = fieldFormatter;
    }

    public String format(List<Field> fields) {
        StringBuilder formattedField = new StringBuilder();
        int currentFieldPosition = 1;
        for (Field field : fields) {
            formattedField.append(createHeaderFor(currentFieldPosition)).append(fieldFormatter.format(field));
            appendNewlineIfNeeded(fields, formattedField, currentFieldPosition);
            currentFieldPosition++;
        }
        return formattedField.toString();
    }

    private void appendNewlineIfNeeded(List<Field> fields, StringBuilder formattedField, int currentFieldPosition) {
        if (currentFieldPosition != fields.size()) {
            formattedField.append(System.getProperty("line.separator"));
        }
    }

    private String createHeaderFor(int currentField) {
        return "field #" + currentField + ":" + System.getProperty("line.separator");
    }
}
