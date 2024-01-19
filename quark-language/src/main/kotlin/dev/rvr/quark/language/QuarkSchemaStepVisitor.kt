package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.CreateModelStep
import dev.rvr.quark.commons.migrations.DropModelStep
import dev.rvr.quark.commons.migrations.SchemaMigrationStep
import dev.rvr.quark.commons.models.QuarkModel

class QuarkSchemaStepVisitor : MigrationParserBaseVisitor<SchemaMigrationStep>() {
    override fun visitStep(ctx: MigrationParser.StepContext?): SchemaMigrationStep {
        val type = ctx!!.stepType().ID().text;
        val arguments = ctx.stepArguments().stepArgument().map {
            QuarkMigrationParser.parseArgument(it)
        }

        return when (type) {
            "CREATE_MODEL" -> CreateModelStep(arguments[0] as QuarkModel)
            "DROP_MODEL" -> DropModelStep(arguments[0] as QuarkModel)
            "RENAME_MODEL" -> throw UnsupportedOperationException("Not implemented yet")
            else -> throw IllegalArgumentException("Step type $type is not valid. This could be because it does not exist or requires to be in a model block")
        }
    }
}