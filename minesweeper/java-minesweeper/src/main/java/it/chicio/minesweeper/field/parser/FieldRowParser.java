package it.chicio.minesweeper.field.parser;

public interface FieldRowParser {
    FieldsParsingStatus parse(String row, FieldsParsingStatus fieldsParsingStatus) throws RuntimeException;
}
