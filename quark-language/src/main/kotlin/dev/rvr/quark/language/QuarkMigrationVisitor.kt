package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.AlterModelStep
import dev.rvr.quark.commons.migrations.ModelMigrationStep
import dev.rvr.quark.commons.migrations.QuarkMigration

class QuarkMigrationVisitor() : MigrationParserBaseVisitor<QuarkMigration>() {


    override fun visitMigration(ctx: MigrationParser.MigrationContext?): QuarkMigration {
        val builder = QuarkMigration.Builder()
        var model: String? = null
        var modelSteps: List<ModelMigrationStep> = emptyList()


        ctx!!.element().forEach {
            when {
                it.step() != null -> {
                    if (model == null) {
                        val step = it.step().accept(QuarkSchemaStepVisitor())
                        builder.step(step)
                    } else {
                        val step = it.step().accept(QuarkModelStepVisitor(model!!))
                        modelSteps = modelSteps + step
                    }
                }

                it.block().blockDefinition().modelBlock() != null -> {
                    model = it.block().blockDefinition().modelBlock().ID().text
                }

                it.block().blockDefinition().blockEnd() != null -> {
                    val step = AlterModelStep(model!!, modelSteps)
                    model = null
                    builder.step(step)
                }
            }
        }

        return builder.build()
    }


}