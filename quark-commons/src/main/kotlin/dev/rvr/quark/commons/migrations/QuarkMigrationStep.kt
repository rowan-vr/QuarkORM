package dev.rvr.quark.commons.migrations

import dev.rvr.quark.commons.models.QuarkModel
import dev.rvr.quark.commons.models.QuarkModelField

interface MigrationStep {
    fun <T> accept(visitor: QuarkMigrationVisitor<T>): T
}

interface SchemaMigrationStep: MigrationStep {
}

interface ModelMigrationStep: MigrationStep {
}

data class CreateModelStep(val model: QuarkModel) : SchemaMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitCreateModelStep(this)
    }
}

data class DropModelStep(val model: QuarkModel) : SchemaMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitDropModelStep(this)
    }
}

data class AlterModelStep(val model: String, val steps: List<ModelMigrationStep>) : SchemaMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitAlterModelStep(this)
    }
}

data class RenameModelStep(val model: QuarkModel) : ModelMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        TODO("Not yet implemented")
    }
}

data class CreateFieldStep(val model: String, val field: QuarkModelField) : ModelMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitCreateFieldStep(this)
    }
}

data class DropFieldStep(val model: String, val field: QuarkModelField) : ModelMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitDropFieldStep(this)
    }
}

data class ModifyFieldStep(val model: String, val field: QuarkModelField) : ModelMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        return visitor.visitModifyFieldStep(this)
    }
}

data class RenameFieldStep(val model: String, val field: QuarkModelField) : ModelMigrationStep {
    override fun <T> accept(visitor: QuarkMigrationVisitor<T>): T {
        TODO("Not yet implemented")
    }
}

