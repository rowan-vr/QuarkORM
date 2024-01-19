package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.*
import dev.rvr.quark.commons.migrations.QuarkMigrationVisitor
import dev.rvr.quark.commons.models.QuarkModel
import dev.rvr.quark.commons.models.QuarkModelField

class QuarkMigrationWriter : QuarkMigrationVisitor<String> {
    val builder = StringBuilder()

    private fun serializeField(field: QuarkModelField): String {
        return "field(\"${field.name}\",\"${field.type}\")"
    }

    private fun serializeModel(model: QuarkModel): String {
        return "model(\"${model.name}\",[${model.fields.joinToString(";") { serializeField(it) }}])"
    }

    override fun visitCreateModelStep(step: CreateModelStep): String {
        return builder.apply {
            append("[CREATE_MODEL]:${serializeModel(step.model)}\n")
        }.toString()
    }

    override fun visitDropModelStep(step: DropModelStep): String {
        return builder.apply {
            append("[DROP_MODEL]:${serializeModel(step.model)}\n")
        }.toString()
    }

    override fun visitAlterModelStep(step: AlterModelStep): String {
        return builder.apply {
            append("--- MODEL ${step.model} ---\n")
            step.steps.forEach {
                it.accept(this@QuarkMigrationWriter)
            }
            append("--- END ---\n")
        }.toString()
    }

    override fun visitRenameModelStep(step: RenameModelStep): String {
        TODO("Not yet implemented")
    }

    override fun visitCreateFieldStep(step: CreateFieldStep): String {
        return builder.apply {
            append("[CREATE_FIELD]:${serializeField(step.field)}\n")
        }.toString()
    }

    override fun visitDropFieldStep(step: DropFieldStep): String {
        return builder.apply {
            append("[DROP_FIELD]:${serializeField(step.field)}\n")
        }.toString()
    }

    override fun visitModifyFieldStep(step: ModifyFieldStep): String {
        return builder.apply {
            append("[MODIFY_FIELD]:${serializeField(step.field)}\n")
        }.toString()
    }

    override fun visitRenameFieldStep(step: RenameFieldStep): String {
        TODO("Not yet implemented")
    }
}