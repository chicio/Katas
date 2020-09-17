package it.chicio.minesweeper;

import it.chicio.minesweeper.field.formatter.FieldsFormatter;
import it.chicio.minesweeper.field.parser.FieldsParser;
import it.chicio.minesweeper.field.resolver.FieldsResolver;

class Minesweeper {
    private FieldsParser fieldsParser;
    private FieldsResolver fieldsResolver;
    private FieldsFormatter fieldsFormatter;
    private String fields;

    Minesweeper(FieldsParser fieldsParser,
                FieldsResolver fieldsResolver,
                FieldsFormatter fieldsFormatter,
                String fields) {
        this.fieldsParser = fieldsParser;
        this.fieldsResolver = fieldsResolver;
        this.fieldsFormatter = fieldsFormatter;
        this.fields = fields;
    }

    public String play() {
        return fieldsFormatter.format(fieldsResolver.resolve(fieldsParser.parse(this.fields)));
    }
}