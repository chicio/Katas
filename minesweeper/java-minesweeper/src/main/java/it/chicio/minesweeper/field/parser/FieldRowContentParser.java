package it.chicio.minesweeper.field.parser;

public interface FieldRowContentParser {
    FieldsParsingStatus tryToParseRowAndUpdate(FieldsParsingStatus parsingStatus);
}
