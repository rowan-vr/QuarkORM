package dev.rvr.quark.commons.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuarkModelTest {
    @Test
    fun testEquals() {
        assertEquals(model("model", listOf()),model("model", listOf()))
        assertNotEquals(model("model1", listOf()),model("model", listOf()))
        assertNotEquals(model("model", listOf(field("field","type"))),model("model", listOf()))
        assertNotEquals(model("model", listOf()),model("model", listOf(field("field","type"))))
        assertNotEquals(model("model", listOf(field("field","type"))),model("model", listOf(field("field","type2"))))
        assertNotEquals(model("model", listOf(field("field","type"))),model("model", listOf(field("field2","type"))))
        assertEquals(model("model", listOf(field("field","type"))),model("model", listOf(field("field","type"))))
        assertNotEquals(model("model1", listOf(field("field","type"))),model("model", listOf(field("field","type"))))
    }
}