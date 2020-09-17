package it.chicio.minesweeper.field.formatter;

import it.chicio.minesweeper.field.Field;

public class FieldFormatterWithNewlines implements FieldFormatter {
    public String format(Field field) {
        StringBuilder fieldAsString = new StringBuilder();
        for (int row = 0; row < field.numberOfRows(); row++) {
            for (int column = 0; column < field.numberOfColumn(); column++) {
                fieldAsString.append(field.get(row, column));
                appendSpaceIfIsNotLastColumn(field, fieldAsString, column);
            }
            fieldAsString.append(System.getProperty("line.separator"));
        }
        return fieldAsString.toString();
    }

    private void appendSpaceIfIsNotLastColumn(Field field, StringBuilder fieldAsString, int column) {
        if (isNotLastColumn(field, column)) {
            fieldAsString.append(" ");
        }
    }

    private boolean isNotLastColumn(Field field, int column) {
        return column != field.numberOfColumn() - 1;
    }
}
