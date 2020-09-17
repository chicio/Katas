package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

public class FieldRowContentTerminationChainParser extends FieldRowContentChainParser {
    boolean canParse(String row) {
        return isTermination(row);
    }

    private boolean isTermination(String row) {
        return row.equals("0 0");
    }

    public FieldsParsingStatus parse(FieldsParsingStatus fieldsParsingStatus) throws RuntimeException {
        FieldsParsingStatus newFieldsParsingStatus = new FieldsParsingStatus(fieldsParsingStatus);
        if (isValid(newFieldsParsingStatus.currentField)) {
            newFieldsParsingStatus.fieldsParsed.add(newFieldsParsingStatus.currentField);
        }
        return newFieldsParsingStatus;
    }

    private boolean isValid(Field field) {
        return field != null;
    }
}
