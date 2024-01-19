package dev.rvr.quark.language

import dev.rvr.quark.commons.migrations.QuarkMigration
import dev.rvr.quark.commons.models.QuarkModel
import dev.rvr.quark.commons.models.QuarkModelField
import dev.rvr.quark.commons.models.QuarkSchema
import dev.rvr.quark.language.QuarkSchemaVisitor

class QuarkMigrationParser constructor(private val quarkParser: MigrationParser) {
    fun parse(): QuarkMigration {
        return quarkParser.migration().accept(QuarkMigrationVisitor())
    }

    internal companion object {
        internal fun parseArgument(ctx: MigrationParser.StepArgumentContext?): Any {
            val id = ctx!!.ARGUMENT_ID().text
            val arguments = ctx.subArguments().subArgument().map {
                parseSubArgument(it)
            }

            return when (id) {
                "model" -> QuarkModel.Builder().name(arguments[0] as String).apply {
                    (arguments[1] as List<*>).forEach { field(it as QuarkModelField) }
                }.build()
                "field" -> QuarkModelField.Builder().name(arguments[0] as String).type(arguments[1] as String).build()
                else -> throw IllegalArgumentException("Unknown argument $id")
            }
        }

        private fun parseSubArgument(ctx: MigrationParser.SubArgumentContext?): Any {
            return when {
                ctx!!.stepArgument() != null -> parseArgument(ctx.stepArgument())
                ctx.listArgument() != null -> parseList(ctx.listArgument())
                ctx.stringArgument() != null -> ctx.stringArgument().STRING_CONTENT().text
                else -> throw IllegalArgumentException("Unknown argument")
            }
        }

        private fun parseList(ctx: MigrationParser.ListArgumentContext?): List<Any> {
            return ctx!!.list().subArgument().map {
                parseSubArgument(it)
            }
        }
    }
}