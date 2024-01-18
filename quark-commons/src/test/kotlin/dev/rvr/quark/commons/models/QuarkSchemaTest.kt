package dev.rvr.quark.commons.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QuarkSchemaTest {
    @Test
    fun testEquals() {
        assertEquals(schema(listOf()),schema(listOf()))
        assertEquals(schema(listOf(model("model", listOf()))),schema(listOf(model("model", listOf()))))
        assertEquals(schema(listOf(model("model", listOf(field("field","type"))))),schema(listOf(model("model", listOf(field("field","type"))))))
        assertEquals(schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type2"))))),schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type2"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))))),schema(listOf(model("model", listOf(field("field","type2"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))))),schema(listOf(model("model", listOf(field("field2","type"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))))),schema(listOf(model("model2", listOf(field("field","type"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type2"))))),schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type3"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type2"))))),schema(listOf(model("model", listOf(field("field","type"))),model("model3", listOf(field("field2","type2"))))))
        assertNotEquals(schema(listOf(model("model", listOf(field("field","type"))),model("model2", listOf(field("field2","type2"))))),schema(listOf(model("model", listOf(field("field","type"))),model("model3", listOf(field("field3","type2"))))))
        assertNotEquals(schema(listOf()),schema(listOf(model("model", listOf()))))
    }
}