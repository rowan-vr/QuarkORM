package dev.rvr.quark.commons.migrations

import dev.rvr.quark.commons.models.QuarkSchema

class QuarkMigration private constructor(builder: Builder){
    val steps = builder.steps

    fun <T> accept(visitor:QuarkMigrationVisitor<T>): T {
        return visitor.visitMigration(this)
    }

    class Builder {
        internal var steps: List<SchemaMigrationStep> = emptyList()

        fun step(step: SchemaMigrationStep) = apply {
            steps = steps + step
        }

        fun build(): QuarkMigration {
            return QuarkMigration(this)
        }
    }
}