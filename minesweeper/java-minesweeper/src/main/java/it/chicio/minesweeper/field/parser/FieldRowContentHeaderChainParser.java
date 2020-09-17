package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

import java.util.ArrayList;

public class FieldRowContentHeaderChainParser extends FieldRowContentChainParser {
    boolean canParse(String row) {
        return isHeader(row);
    }

    private boolean isHeader(String row) {
        return row.matches("(\\d \\d)");
    }

    public FieldsParsingStatus parse(FieldsParsingStatus fieldsParsingStatus) throws RuntimeException {
        FieldsParsingStatus newFieldsParsingStatus = new FieldsParsingStatus(fieldsParsingStatus);
        if (isValid(fieldsParsingStatus.currentField)) {
            addFieldIfItHasAValidNumberOfRows(
                    newFieldsParsingStatus.fieldsParsed,
                    newFieldsParsingStatus.currentField,
                    newFieldsParsingStatus.headerNumberOfRowsForCurrentField
            );
        }
        parseHeaderAndUpdateNewParsingStatus(newFieldsParsingStatus);
        return newFieldsParsingStatus;
    }

    private void parseHeaderAndUpdateNewParsingStatus(FieldsParsingStatus newFieldsParsingStatus) {
        String[] fieldDimensions = newFieldsParsingStatus.currentRowContent.split(" ");
        newFieldsParsingStatus.headerNumberOfRowsForCurrentField = Integer.valueOf(fieldDimensions[0]);
        newFieldsParsingStatus.currentRow = 0;
        newFieldsParsingStatus.currentField = new Field(
                newFieldsParsingStatus.headerNumberOfRowsForCurrentField,
                Integer.valueOf(fieldDimensions[1])
        );
    }

    private void addFieldIfItHasAValidNumberOfRows(ArrayList<Field> fieldsParsed,
                                                   Field field,
                                                   int currentNumberOfRows) throws RuntimeException {
        if (field.numberOfRows() < currentNumberOfRows) {
            throw new RuntimeException("Invalid number of row");
        }
        fieldsParsed.add(field);
    }

    private boolean isValid(Field field) {
        return field != null;
    }
}
