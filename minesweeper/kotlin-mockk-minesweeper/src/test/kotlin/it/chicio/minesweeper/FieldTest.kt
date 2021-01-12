package it.chicio.minesweeper

import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import java.util.*


class FieldTest {
    @Test
    fun neighboursFor1x1Field() {
        val field = FieldFactory().make(arrayOf(arrayOf("*")))
        Assert.assertThat(field.neighboursOf(0, 0), CoreMatchers.`is`(CoreMatchers.equalTo(ArrayList())))
    }

    @Test
    fun neighboursFor1x2FieldOnLeft() {
        val field = FieldFactory().make(arrayOf(arrayOf("*", ".")))
        Assert.assertThat(field.neighboursOf(0, 1), CoreMatchers.`is`(CoreMatchers.equalTo(listOf("*"))))
    }

    @Test
    fun neighboursFor2x1FieldOnRight() {
        val field = FieldFactory().make(arrayOf(arrayOf(".", "*")))
        Assert.assertThat(field.neighboursOf(0, 0), CoreMatchers.`is`(CoreMatchers.equalTo(listOf("*"))))
    }

    @Test
    fun neighboursFor2x2FieldOnRight() {
        val field = FieldFactory().make(arrayOf(arrayOf(".", "*"), arrayOf("*", "*")))
        Assert.assertThat(field.neighboursOf(0, 0), CoreMatchers.`is`(CoreMatchers.equalTo(Arrays.asList("*", "*", "*"))))
    }

    @Test
    fun neighboursFor2x3FieldOnRightAndLeft() {
        val field = FieldFactory().make(arrayOf(arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))
        Assert.assertThat(field.neighboursOf(0, 1), CoreMatchers.`is`(CoreMatchers.equalTo(Arrays.asList("*", "*", "*", "*", "*"))))
    }

    @Test
    fun resolve3x3FieldWith3Bomb() {
        val field = FieldFactory().make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", ".", "*"), arrayOf("*", "*", "*")))
        Assert.assertThat(field.neighboursOf(1, 1), CoreMatchers.`is`(CoreMatchers.equalTo(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*"))))
    }
}