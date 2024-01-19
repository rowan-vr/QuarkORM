package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.*
import dev.rvr.quark.commons.models.QuarkModel
import dev.rvr.quark.commons.models.QuarkModelField

class QuarkModelStepVisitor(val model: String) : MigrationParserBaseVisitor<ModelMigrationStep>() {
    override fun visitStep(ctx: MigrationParser.StepContext?): ModelMigrationStep {
        val type = ctx!!.stepType().ID().text;
        val arguments = ctx.stepArguments().stepArgument().map {
            QuarkMigrationParser.parseArgument(it)
        }

        return when (type) {
            "CREATE_FIELD" -> CreateFieldStep(model, arguments[0] as QuarkModelField)
            "DROP_FIELD" -> DropFieldStep(model, arguments[0] as QuarkModelField)
            "MODIFY_FIELD" -> ModifyFieldStep(model, arguments[0] as QuarkModelField)
            "RENAME_FIELD" -> throw UnsupportedOperationException("Not implemented yet")
            else -> throw IllegalArgumentException("Step type $type is not valid. This could be because it does not exist or cannot be in a model block")
        }
    }
}