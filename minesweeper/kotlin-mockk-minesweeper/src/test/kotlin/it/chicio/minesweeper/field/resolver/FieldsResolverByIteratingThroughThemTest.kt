package it.chicio.minesweeper.field.resolver

import it.chicio.minesweeper.field.Field
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class FieldsResolverByIteratingThroughThemTest {
    private val field = Field(arrayOf(arrayOf("*", "*", ".", ".", "."), arrayOf(".", ".", ".", ".", "."), arrayOf(".", "*", ".", ".", ".")))
    private val resolvedField = Field(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0")))
    private val anotherField = Field(arrayOf(arrayOf("*", ".", ".", "."), arrayOf(".", ".", ".", "."), arrayOf(".", "*", ".", "."), arrayOf(".", ".", ".", ".")))
    private val anotherResolvedField = Field(arrayOf(arrayOf("*", "*", "1", "0", "0"), arrayOf("3", "3", "2", "0", "0"), arrayOf("1", "*", "1", "0", "0")))
    @Test
    fun resolveSingleFieldList() {
        val fieldResolver = Mockito.mock(FieldResolver::class.java)
        Mockito.`when`(fieldResolver.resolve(field)).thenReturn(resolvedField)
        val fieldsResolverByIteratingThroughThem = FieldsResolverByIteratingThroughThem(fieldResolver)
        val fields = fieldsResolverByIteratingThroughThem.resolve(listOf(field))
        Assert.assertThat(fields, CoreMatchers.`is`(listOf(resolvedField)))
    }

    @Test
    fun resolveFieldsList() {
        val fieldResolver = Mockito.mock(FieldResolver::class.java)
        Mockito.`when`(fieldResolver.resolve(field)).thenReturn(resolvedField)
        Mockito.`when`(fieldResolver.resolve(anotherField)).thenReturn(anotherResolvedField)
        val fieldsResolverByIteratingThroughThem = FieldsResolverByIteratingThroughThem(fieldResolver)
        val fields = fieldsResolverByIteratingThroughThem.resolve(Arrays.asList(field, anotherField))
        Assert.assertThat(fields, CoreMatchers.`is`(Arrays.asList(resolvedField, anotherResolvedField)))
    }
}