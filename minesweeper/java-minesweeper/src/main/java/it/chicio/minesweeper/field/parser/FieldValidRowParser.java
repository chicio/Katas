package it.chicio.minesweeper.field.parser;

public class FieldValidRowParser implements FieldRowParser {
    private FieldRowContentParser fieldRowContentParser;

    public FieldValidRowParser(FieldRowContentParser fieldRowContentParser) {
        this.fieldRowContentParser = fieldRowContentParser;
    }

    public FieldsParsingStatus parse(String row, FieldsParsingStatus fieldsParsingStatus) throws RuntimeException {
        FieldsParsingStatus newFieldsParsingStatus = new FieldsParsingStatus(fieldsParsingStatus);
        if (isAValid(row)) {
            newFieldsParsingStatus.currentRowContent = row;
            return fieldRowContentParser.tryToParseRowAndUpdate(newFieldsParsingStatus);
        } else {
            return newFieldsParsingStatus;
        }
    }

    private boolean isAValid(String row) {
        return !row.equals("");
    }
}