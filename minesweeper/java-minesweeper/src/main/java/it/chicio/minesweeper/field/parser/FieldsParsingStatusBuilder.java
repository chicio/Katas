package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

import java.util.ArrayList;

public class FieldsParsingStatusBuilder {
    private Field currentField;
    private String currentRowContent;
    private int currentRow;
    private int headerNumberOfRowsForCurrentField;
    private ArrayList<Field> fieldsParsed;

    FieldsParsingStatusBuilder withCurrentField(Field currentField) {
        this.currentField = currentField;
        return this;
    }

    FieldsParsingStatusBuilder withCurrentRowContent(String currentRowContent) {
        this.currentRowContent = currentRowContent;
        return this;
    }

    FieldsParsingStatusBuilder withCurrentRow(int currentRow) {
        this.currentRow = currentRow;
        return this;
    }

    FieldsParsingStatusBuilder withHeaderNumberOfRowsForCurrentField(int headerNumberOfRowsForCurrentField) {
        this.headerNumberOfRowsForCurrentField = headerNumberOfRowsForCurrentField;
        return this;
    }

    FieldsParsingStatusBuilder withFieldsParsed(ArrayList<Field> fieldsParsed) {
        this.fieldsParsed = fieldsParsed;
        return this;
    }

    FieldsParsingStatus build() {
        return new FieldsParsingStatus(
                currentField,
                currentRowContent,
                currentRow,
                headerNumberOfRowsForCurrentField,
                fieldsParsed
        );
    }
}
