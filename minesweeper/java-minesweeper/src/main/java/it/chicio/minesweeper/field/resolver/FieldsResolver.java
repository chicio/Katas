package it.chicio.minesweeper.field.resolver;

import it.chicio.minesweeper.field.Field;

import java.util.List;

public interface FieldsResolver {
    List<Field> resolve(List<Field> fields);
}
