package dev.rvr.quark.commons.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QuarkModelFieldTest {
    @Test
    fun testEquals() {
        assertEquals(field("field","type"),field("field","type"))
        assertEquals(field("field".repeat(20),"type".repeat(20)),field("field".repeat(20),"type".repeat(20)))
        assertEquals(field("","aaa"),field("","aaa"))
        assertEquals(field("aaa",""),field("aaa",""))
        assertEquals(field("",""),field("",""))
        assertNotEquals(field("field","type"),field("field","type2"))
        assertNotEquals(field("field","type"),field("field2","type"))
        assertNotEquals(field("field","type"),field("field2","type2"))
    }
}