package dev.rvr.quark.language

import dev.rvr.quark.language.factory.StandardQuarkParserFactory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QuarkSchemaParserTest {
    private val test1 = schema(listOf(model("test", listOf(field("test", "test"))), model("test1", listOf(field("test1", "test1")))))

    @Test
    fun testParse() {
        val schemaContent = QuarkSchemaParserTest::class.java.getResource("/test1.quark")?.openStream()
        val parser = StandardQuarkParserFactory().createFromInputStream(schemaContent!!)
        val schema = QuarkSchemaParser(parser).parse()
        assertEquals(test1, schema)
    }
}