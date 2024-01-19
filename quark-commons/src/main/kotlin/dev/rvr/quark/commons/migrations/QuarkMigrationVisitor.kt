package dev.rvr.quark.commons.migrations

interface QuarkMigrationVisitor<T> {
    fun visitMigration(migration: QuarkMigration): T {
        return migration.steps.map { it.accept(this) }.last()
    }
    fun visitCreateModelStep(step: CreateModelStep): T
    fun visitDropModelStep(step: DropModelStep): T
    fun visitAlterModelStep(step: AlterModelStep): T
    fun visitRenameModelStep(step: RenameModelStep): T
    fun visitCreateFieldStep(step: CreateFieldStep): T
    fun visitDropFieldStep(step: DropFieldStep): T
    fun visitModifyFieldStep(step: ModifyFieldStep): T
    fun visitRenameFieldStep(step: RenameFieldStep): T
}