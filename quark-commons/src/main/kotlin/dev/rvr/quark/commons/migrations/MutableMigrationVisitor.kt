package dev.rvr.quark.commons.migrations

import dev.rvr.quark.commons.models.*

class MutableMigrationVisitor(current: QuarkSchema): QuarkMigrationVisitor<MutableQuarkSchema> {
    private val mutable = MutableQuarkSchema.fromImmutable(current);
    private var currentModel: MutableQuarkModel? = null

    override fun visitCreateModelStep(step: CreateModelStep): MutableQuarkSchema {
        return mutable.apply {
            models.add(MutableQuarkModel.fromImmutable(step.model))
        }
    }

    override fun visitDropModelStep(step: DropModelStep): MutableQuarkSchema {
        return mutable.apply {
            models.removeIf { it.name == step.model.name }
        }
    }

    override fun visitAlterModelStep(step: AlterModelStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel = models.find { it.name == step.model.name }!!
            step.steps.forEach {
                it.accept(this@MutableMigrationVisitor)
            }
            currentModel = null
        }
    }

    override fun visitRenameModelStep(step: RenameModelStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel!!.name = step.model.name
        }
    }

    override fun visitCreateFieldStep(step: CreateFieldStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel!!.fields.add(MutableQuarkModelField.fromImmutable(step.field))
        }
    }

    override fun visitDropFieldStep(step: DropFieldStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel!!.fields.removeIf { it.name == step.field.name }
        }
    }

    override fun visitModifyFieldStep(step: ModifyFieldStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel!!.fields.find { it.name == step.field.name }.apply {
                this!!.type = step.field.type
            }
        }
    }

    override fun visitRenameFieldStep(step: RenameFieldStep): MutableQuarkSchema {
        return mutable.apply {
            currentModel!!.fields.find { it.name == step.field.name }.apply {
                this!!.name = step.field.name
            }
        }
    }
}