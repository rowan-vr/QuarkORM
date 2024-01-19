package dev.rvr.quark.language

import dev.rvr.quark.language.factory.SchemaParserFactory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QuarkSchemaParserTest {
    private val test1 = schema(listOf(model("Test", listOf(field("field1", "String"),field("field2", "Integer"))), model("AnotherTest", listOf(field("coolField", "String"),field("coolerField", "Integer"),field("coolestField", "Boolean")))))

    @Test
    fun testParse() {
        val schemaContent = QuarkSchemaParserTest::class.java.getResource("/test1.quark")?.openStream()
        val parser = SchemaParserFactory().createFromInputStream(schemaContent!!)
        val schema = QuarkSchemaParser(parser).parse()
        assertEquals(test1, schema)
    }
}