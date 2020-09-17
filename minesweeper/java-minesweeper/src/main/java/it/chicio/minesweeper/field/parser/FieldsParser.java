package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

import java.util.List;

public interface FieldsParser {
    List<Field> parse(String fields) throws RuntimeException;
}
