package it.chicio.minesweeper.field.parser;

public class FieldRowContentValuesChainParser extends FieldRowContentChainParser {
    boolean canParse(String row) {
        return isNotHeader(row) && isNotTermination(row);
    }

    public FieldsParsingStatus parse(FieldsParsingStatus fieldsParsingStatus) throws RuntimeException {
        FieldsParsingStatus newFieldsParsingStatus = new FieldsParsingStatus(fieldsParsingStatus);
        String[] columnsValues = newFieldsParsingStatus.currentRowContent.split(" ");
        checkIfNumberOfValuesAreCorrect(newFieldsParsingStatus, columnsValues);
        setNewFieldRow(newFieldsParsingStatus, columnsValues);
        return newFieldsParsingStatus;
    }

    private void setNewFieldRow(FieldsParsingStatus newFieldsParsingStatus, String[] columnsValues) {
        for (int column = 0; column < newFieldsParsingStatus.currentField.numberOfColumn(); column++) {
            newFieldsParsingStatus.currentField.set(
                    newFieldsParsingStatus.currentRow,
                    column,
                    columnsValues[column]
            );
        }
        newFieldsParsingStatus.currentRow++;
    }

    private void checkIfNumberOfValuesAreCorrect(FieldsParsingStatus newFieldsParsingStatus, String[] columnsValues) {
        if (columnsValues.length != newFieldsParsingStatus.currentField.numberOfColumn()) {
            throw new RuntimeException();
        }
    }

    private boolean isNotHeader(String row) {
        return !row.matches("(\\d \\d)");
    }

    private boolean isNotTermination(String row) {
        return !row.equals("0 0");
    }
}
