package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.QuarkMigrationFactory
import dev.rvr.quark.commons.migrations.StandardQuarkMigrationFactory
import dev.rvr.quark.commons.models.QuarkSchema
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuarkMigrationWriterTest {
    @Test
    fun testMigrationWriter() {
        val oldSchema = QuarkSchema.Builder()
            .model(model("test", listOf(field("name", "String"), field("old", "Integer"))))
            .model(model("bad", listOf(field("aaaa", "bbb"), field("ccc", "dddd"))))
            .build()
        val newSchema = QuarkSchema.Builder()
            .model(model("test", listOf(field("name", "String"), field("test", "Integer"))))
            .model(model("user", listOf(field("name", "String"), field("age", "Integer"))))
            .build()
        val migration = StandardQuarkMigrationFactory().createMigration(oldSchema, newSchema)
        val content = migration.accept(QuarkMigrationWriter())
        println(migration)
        println(content)
        assertEquals(content, QuarkMigrationParserTest::class.java.getResource("/test1.migration")?.readText())
    }
}