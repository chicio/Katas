package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

import java.util.ArrayList;

class FieldsParsingStatus {
    public Field currentField;
    public String currentRowContent;
    public int currentRow;
    public int headerNumberOfRowsForCurrentField;
    public ArrayList<Field> fieldsParsed;

    FieldsParsingStatus(Field currentField, String currentRowContent, int currentRow, int headerNumberOfRowsForCurrentField, ArrayList<Field> fieldsParsed) {
        this.currentField = currentField;
        this.currentRowContent = currentRowContent;
        this.currentRow = currentRow;
        this.headerNumberOfRowsForCurrentField = headerNumberOfRowsForCurrentField;
        this.fieldsParsed = fieldsParsed;
    }

    FieldsParsingStatus(FieldsParsingStatus anotherFieldParsingStatus) {
        this.currentField = anotherFieldParsingStatus.currentField;
        this.currentRowContent = anotherFieldParsingStatus.currentRowContent;
        this.currentRow = anotherFieldParsingStatus.currentRow;
        this.headerNumberOfRowsForCurrentField = anotherFieldParsingStatus.headerNumberOfRowsForCurrentField;
        this.fieldsParsed = anotherFieldParsingStatus.fieldsParsed;
    }
}