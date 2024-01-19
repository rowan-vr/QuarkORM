package dev.rvr.quark.commons.migrations

import dev.rvr.quark.commons.models.field
import dev.rvr.quark.commons.models.model
import dev.rvr.quark.commons.models.schema
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuarkMigrationTest {

    @Test
    fun `should create a migration with a single step`() {
        val old = schema(emptyList());
        val model = model("User", listOf(field("name", "string"), field("age", "int")));
        val new = schema(listOf(model));

        val migration = QuarkMigration.Builder().step(CreateModelStep(model)).build()

        assertEquals(1, migration.steps.size)
        assertEquals(CreateModelStep(model), migration.steps[0])
        assertEquals(migration.accept(QuarkMigrationObjectVisitor(old)).toImmutable(),new)
    }

    @Test
    fun `should create a migration with multiple steps`() {
        val old = schema(listOf(model("User", emptyList())));
        val model = model("User", listOf(field("name", "string"), field("age", "int")));
        val model1 = model("Comments", listOf(field("content", "string"), field("deleted", "boolean")));
        val new = schema(listOf(model,model1));

        val migration = StandardQuarkMigrationFactory().createMigration(old, new)

        assertEquals(4, migration.steps.size)
        assertEquals(migration.accept(QuarkMigrationObjectVisitor(old)).toImmutable(),new)
    }

    //TODO: Tests for each step
}