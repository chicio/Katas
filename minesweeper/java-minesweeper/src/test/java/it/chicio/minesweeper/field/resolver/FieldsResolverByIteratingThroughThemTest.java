package it.chicio.minesweeper.field.resolver;

import it.chicio.minesweeper.field.Field;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldsResolverByIteratingThroughThemTest {
    private final Field field = new Field(new String[][]{
            {"*", "*", ".", ".", "."},
            {".", ".", ".", ".", "."},
            {".", "*", ".", ".", "."}
    });
    private Field resolvedField = new Field(new String[][]{
            {"*", "*", "1", "0", "0"},
            {"3", "3", "2", "0", "0"},
            {"1", "*", "1", "0", "0"}
    });
    private final Field anotherField = new Field(new String[][]{
            {"*", ".", ".", "."},
            {".", ".", ".", "."},
            {".", "*", ".", "."},
            {".", ".", ".", "."}
    });
    private Field anotherResolvedField = new Field(new String[][]{
            {"*", "*", "1", "0", "0"},
            {"3", "3", "2", "0", "0"},
            {"1", "*", "1", "0", "0"}
    });

    @Test
    public void resolveSingleFieldList() {
        FieldResolver fieldResolver = mock(FieldResolver.class);
        when(fieldResolver.resolve(field)).thenReturn(resolvedField);
        FieldsResolverByIteratingThroughThem fieldsResolverByIteratingThroughThem =
                new FieldsResolverByIteratingThroughThem(fieldResolver);

        List<Field> fields = fieldsResolverByIteratingThroughThem.resolve(singletonList(field));

        assertThat(fields, is(singletonList(resolvedField)));
    }

    @Test
    public void resolveFieldsList() {
        FieldResolver fieldResolver = mock(FieldResolver.class);
        when(fieldResolver.resolve(field)).thenReturn(resolvedField);
        when(fieldResolver.resolve(anotherField)).thenReturn(anotherResolvedField);
        FieldsResolverByIteratingThroughThem fieldsResolverByIteratingThroughThem =
                new FieldsResolverByIteratingThroughThem(fieldResolver);

        List<Field> fields = fieldsResolverByIteratingThroughThem.resolve(asList(field, anotherField));

        assertThat(fields, is(asList(resolvedField, anotherResolvedField)));
    }
}
