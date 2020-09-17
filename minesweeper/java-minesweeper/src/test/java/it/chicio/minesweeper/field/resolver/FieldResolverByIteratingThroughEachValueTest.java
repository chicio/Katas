package it.chicio.minesweeper.field.resolver;

import it.chicio.minesweeper.field.Field;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldResolverByIteratingThroughEachValueTest {
    private FieldResolverByIteratingThroughEachValue fieldResolverByIteratingThroughEachValue;

    @Before
    public void setUp() {
        fieldResolverByIteratingThroughEachValue = new FieldResolverByIteratingThroughEachValue();
    }

    @Test
    public void resolve1x1FieldWithABomb() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{{"*"}}));

        assertThat(field, is(equalTo(new Field(new String[][]{{"*"}}))));
    }

    @Test
    public void resolve1x2FieldWithABombOnTheLeft() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{{"*", "."}}));

        assertThat(field, is(equalTo(new Field(new String[][]{{"*", "1"}}))));
    }

    @Test
    public void resolve1x2FieldWithABombOnTheRight() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{{".", "*"}}));

        assertThat(field, is(equalTo(new Field(new String[][]{{"1", "*"}}))));
    }

    @Test
    public void resolve2x2FieldWith3Bomb() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{
                {".", "*"},
                {"*", "*"}
        }));

        assertThat(field, is(equalTo(new Field(new String[][]{
                {"3", "*"},
                {"*", "*"},
        }))));
    }

    @Test
    public void resolve2x3FieldWith3Bomb() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{
                {"*", ".", "*"},
                {"*", "*", "*"}
        }));

        assertThat(field, is(equalTo(new Field(new String[][]{
                {"*", "5", "*"},
                {"*", "*", "*"},
        }))));
    }

    @Test
    public void resolve3x3FieldWith3Bomb() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{
                {"*", "*", "*"},
                {"*", ".", "*"},
                {"*", "*", "*"}
        }));

        assertThat(field, is(equalTo(new Field(new String[][]{
                {"*", "*", "*"},
                {"*", "8", "*"},
                {"*", "*", "*"},
        }))));
    }

    @Test
    public void resolveTestField1() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{
                {"*", "*", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", "*", ".", ".", "."}
        }));

        assertThat(field, is(equalTo(new Field(new String[][]{
                {"*", "*", "1", "0", "0"},
                {"3", "3", "2", "0", "0"},
                {"1", "*", "1", "0", "0"}
        }))));
    }

    @Test
    public void resolveTestField2() {
        Field field = fieldResolverByIteratingThroughEachValue.resolve(new Field(new String[][]{
                {"*", ".", ".", "."},
                {".", ".", ".", "."},
                {".", "*", ".", "."},
                {".", ".", ".", "."}
        }));

        assertThat(field, is(equalTo(new Field(new String[][]{
                {"*", "1", "0", "0"},
                {"2", "2", "1", "0"},
                {"1", "*", "1", "0"},
                {"1", "1", "1", "0"}
        }))));
    }
}
