package it.chicio.minesweeper.field.formatter;

import it.chicio.minesweeper.field.Field;

import java.util.List;

public interface FieldsFormatter {
    String format(List<Field> fields);
}
