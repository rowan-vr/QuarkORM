package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.MutableMigrationVisitor
import dev.rvr.quark.commons.models.QuarkSchema
import dev.rvr.quark.language.factory.MigrationParserFactory
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QuarkMigrationParserTest {
//    private val test1 = schema(listOf(model("Test", listOf(field("field1", "String"),field("field2", "Integer"))), model("AnotherTest", listOf(field("coolField", "String"),field("coolerField", "Integer"),field("coolestField", "Boolean")))))

    @Test
    fun testParse() {
        val content = QuarkMigrationParserTest::class.java.getResource("/test1.migration")?.openStream()
        val parser = MigrationParserFactory().createFromInputStream(content!!)
        val quarkParser = QuarkMigrationParser(parser)
        val migration = quarkParser.parse();
        println(migration)

        val oldSchema = QuarkSchema.Builder()
            .model(model("test", listOf(field("name", "String"), field("old", "Integer"))))
            .model(model("bad", listOf(field("aaaa", "bbb"), field("ccc", "dddd"))))
            .build()
        val newSchema = QuarkSchema.Builder()
            .model(model("test", listOf(field("name", "String"), field("test", "Integer"))))
            .model(model("user", listOf(field("name", "String"), field("age", "Integer"))))
            .build()

        assertEquals(newSchema, migration.accept(MutableMigrationVisitor(oldSchema)).toImmutable())
    }
}