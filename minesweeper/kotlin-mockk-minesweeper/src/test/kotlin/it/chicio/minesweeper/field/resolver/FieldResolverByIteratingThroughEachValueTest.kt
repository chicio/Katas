package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.FieldFactory
import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FieldResolverByIteratingThroughEachValueTest {
    private lateinit var fieldResolverByIteratingThroughEachValue: FieldResolverByIteratingThroughEachValue
    @Before
    fun setUp() {
        fieldResolverByIteratingThroughEachValue = FieldResolverByIteratingThroughEachValue()
    }

    @Test
    fun resolve1x1FieldWithABomb() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*"))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*"))))))
    }

    @Test
    fun resolve1x2FieldWithABombOnTheLeft() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", "."))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*", "1"))))))
    }

    @Test
    fun resolve1x2FieldWithABombOnTheRight() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf(".", "*"))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("1", "*"))))))
    }

    @Test
    fun resolve2x2FieldWith3Bomb() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf(".", "*"), arrayOf("*", "*"))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("3", "*"), arrayOf("*", "*"))))))
    }

    @Test
    fun resolve2x3FieldWith3Bomb() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", ".", "*"), arrayOf("*", "*", "*"))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*", "5", "*"), arrayOf("*", "*", "*"))))))
    }

    @Test
    fun resolve3x3FieldWith3Bomb() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", ".", "*"), arrayOf("*", "*", "*"))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*", "*", "*"), arrayOf("*", "8", "*"), arrayOf("*", "*", "*"))))))
    }

    @Test
    fun resolveTestField1() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", "*", ".", ".", "."), arrayOf(".", ".", ".", ".", "."), arrayOf(".", "*", ".", ".", "."))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0"))))))
    }

    @Test
    fun resolveTestField2() {
        val field = fieldResolverByIteratingThroughEachValue.resolve(FieldFactory().make(arrayOf(arrayOf("*", ".", ".", "."), arrayOf(".", ".", ".", "."), arrayOf(".", "*", ".", "."), arrayOf(".", ".", ".", "."))))
        Assert.assertThat(field, CoreMatchers.`is`(CoreMatchers.equalTo(FieldFactory().make(arrayOf(arrayOf("*", "1", "0", "0"), arrayOf("2", "2", "1", "0"), arrayOf("1", "*", "1", "0"), arrayOf("1", "1", "1", "0"))))))
    }
}