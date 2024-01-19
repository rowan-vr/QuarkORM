package dev.rvr.quark.commons.migrations

import dev.rvr.quark.commons.models.QuarkSchema

interface QuarkMigrationFactory {
    fun createMigration(old: QuarkSchema, new: QuarkSchema): QuarkMigration
}

class StandardQuarkMigrationFactory : QuarkMigrationFactory {
    override fun createMigration(old: QuarkSchema, new: QuarkSchema): QuarkMigration {
        if (old == new) return QuarkMigration.Builder().build();

        val builder = QuarkMigration.Builder()
        val oldModels = old.models
        val newModels = new.models

        for (model in oldModels) {
            if (!newModels.contains(model)) {
                builder.step(DropModelStep(model))
            }
        }

        for (model in newModels) {
            if (!oldModels.contains(model)) {
                builder.step(CreateModelStep(model))
            }
        }

        for (newModel in newModels) {
            val oldModel = oldModels.find { it.name == newModel.name }
            if (oldModel != null && oldModel != newModel) {
                val steps = mutableListOf<ModelMigrationStep>()

                for (field in oldModel.fields) {
                    if (!newModel.fields.contains(field)) {
                        steps.add(DropFieldStep(oldModel.name, field))
                    }
                }

                for (field in newModel.fields) {
                    if (!oldModel.fields.contains(field)) {
                        steps.add(CreateFieldStep(newModel.name, field))
                    }
                }

                for (field in newModel.fields) {
                    val oldField = oldModel.fields.find { it.name == field.name }
                    if (oldField != null && oldField != field) {
                        steps.add(ModifyFieldStep(newModel.name, field))
                    }
                }

                builder.step(AlterModelStep(oldModel.name, steps))
            }
        }

        return builder.build()
    }

}