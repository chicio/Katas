package it.chicio.minesweeper.field.resolver;

import it.chicio.minesweeper.field.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldsResolverByIteratingThroughThem implements FieldsResolver {
    private FieldResolver fieldResolver;

    public FieldsResolverByIteratingThroughThem(FieldResolver fieldResolver) {
        this.fieldResolver = fieldResolver;
    }

    public List<Field> resolve(List<Field> fields) {
        ArrayList<Field> resolvedFields = new ArrayList<Field>();
        for (Field field : fields) {
            resolvedFields.add(fieldResolver.resolve(field));
        }
        return resolvedFields;
    }
}
