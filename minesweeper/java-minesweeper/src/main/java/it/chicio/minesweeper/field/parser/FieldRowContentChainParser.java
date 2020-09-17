package it.chicio.minesweeper.field.parser;

public abstract class FieldRowContentChainParser implements FieldRowContentParser {
    private FieldRowContentChainParser nextFieldRowContentChainParser;

    public FieldsParsingStatus tryToParseRowAndUpdate(FieldsParsingStatus parsingStatus) {
        if (canParse(parsingStatus.currentRowContent)) {
            return parse(parsingStatus);
        } else if (hasAValidNextFieldRowParser()) {
            return nextFieldRowContentChainParser.tryToParseRowAndUpdate(parsingStatus);
        }
        return parsingStatus;
    }

    private boolean hasAValidNextFieldRowParser() {
        return nextFieldRowContentChainParser != null;
    }

    public void setNextFieldRowContentChainParser(FieldRowContentChainParser fieldRowContentChainParser) {
        this.nextFieldRowContentChainParser = fieldRowContentChainParser;
    }

    abstract boolean canParse(String row);

    abstract FieldsParsingStatus parse(FieldsParsingStatus fieldsParsingStatus);
}
