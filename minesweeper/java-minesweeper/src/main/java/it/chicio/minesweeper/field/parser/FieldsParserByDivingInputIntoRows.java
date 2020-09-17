package it.chicio.minesweeper.field.parser;

import it.chicio.minesweeper.field.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldsParserByDivingInputIntoRows implements FieldsParser {
    private FieldRowParser fieldRowParser;

    public FieldsParserByDivingInputIntoRows(FieldRowParser fieldRowParser) {
        this.fieldRowParser = fieldRowParser;
    }

    public List<Field> parse(String fieldsAsString) throws RuntimeException {
        FieldsParsingStatus fieldsParsingStatus = new FieldsParsingStatus(null, null, 0, 0, new ArrayList<Field>());
        for (String row : fieldsAsString.split(System.getProperty("line.separator"))) {
            fieldsParsingStatus = fieldRowParser.parse(row, fieldsParsingStatus);
        }
        return fieldsParsingStatus.fieldsParsed;
    }
}