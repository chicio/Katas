package it.chicio.minesweeper;

import it.chicio.minesweeper.field.Field;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FieldTest {

    @Test
    public void neighboursFor1x1Field() {
        Field field = new Field(new String[][]{{"*"}});

        assertThat(field.neighboursOf(0, 0), is(equalTo(new ArrayList<String>())));
    }

    @Test
    public void neighboursFor1x2FieldOnLeft() {
        Field field = new Field(new String[][]{{"*", "."}});

        assertThat(field.neighboursOf(0, 1), is(equalTo(singletonList("*"))));
    }

    @Test
    public void neighboursFor2x1FieldOnRight() {
        Field field = new Field(new String[][]{{".", "*"}});

        assertThat(field.neighboursOf(0, 0), is(equalTo(singletonList("*"))));
    }

    @Test
    public void neighboursFor2x2FieldOnRight() {
        Field field = new Field(new String[][]{
                {".", "*"},
                {"*", "*"}
        });

        assertThat(field.neighboursOf(0, 0), is(equalTo(asList("*", "*", "*"))));
    }

    @Test
    public void neighboursFor2x3FieldOnRightAndLeft() {
        Field field = new Field(new String[][]{
                {"*", ".", "*"},
                {"*", "*", "*"}
        });

        assertThat(field.neighboursOf(0, 1), is(equalTo(asList("*", "*", "*", "*", "*"))));
    }

    @Test
    public void resolve3x3FieldWith3Bomb() {
        Field field = new Field(new String[][]{
                {"*", "*", "*"},
                {"*", ".", "*"},
                {"*", "*", "*"}
        });

        assertThat(field.neighboursOf(1, 1), is(equalTo(asList("*", "*", "*", "*", "*", "*", "*", "*"))));
    }
}
