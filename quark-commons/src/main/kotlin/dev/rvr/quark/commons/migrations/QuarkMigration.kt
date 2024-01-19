package dev.rvr.quark.commons.migrations

class QuarkMigration private constructor(builder: Builder){
    val steps = builder.steps

    fun <T> accept(visitor:QuarkMigrationVisitor<T>): T {
        return visitor.visitMigration(this)
    }



    override fun equals(other: Any?): Boolean {
        return other is QuarkMigration && other.steps == steps
    }

    override fun hashCode(): Int {
        return steps.hashCode()
    }

    override fun toString(): String {
        return "QuarkMigration(steps=$steps)"
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