package dev.rvr.quark.commons.models

import com.google.common.base.Objects

interface MutableQuarkObject<T> {
    fun toImmutable(): T
}

class MutableQuarkSchema: MutableQuarkObject<QuarkSchema> {
    val models: MutableSet<MutableQuarkModel> = mutableSetOf()

    companion object {
        fun fromImmutable(schema: QuarkSchema): MutableQuarkSchema {
            return MutableQuarkSchema().apply {
                schema.models.forEach { models.add(MutableQuarkModel.fromImmutable(it)) }
            }
        }
    }

    override fun toImmutable(): QuarkSchema {
        val builder = QuarkSchema.Builder()
        models.forEach { builder.model(it.toImmutable()) }
        return builder.build()
    }
}

class MutableQuarkModel: MutableQuarkObject<QuarkModel> {
    var name: String = ""
    val fields: MutableSet<MutableQuarkModelField> = mutableSetOf()

    companion object {
        fun fromImmutable(model: QuarkModel): MutableQuarkModel {
            return MutableQuarkModel().apply {
                name = model.name
                model.fields.forEach { fields.add(MutableQuarkModelField.fromImmutable(it)) }
            }
        }
    }

    override fun toImmutable(): QuarkModel {
        val builder = QuarkModel.Builder()
        builder.name(name)
        fields.forEach { builder.field(it.toImmutable()) }
        return builder.build()
    }
}

class MutableQuarkModelField: MutableQuarkObject<QuarkModelField> {
    var name: String = ""
    var type: String = ""



    companion object {
        fun fromImmutable(field: QuarkModelField): MutableQuarkModelField {
            return MutableQuarkModelField().apply {
                name = field.name
                type = field.type
            }
        }
    }

    override fun toImmutable(): QuarkModelField {
        return QuarkModelField.Builder()
            .name(name)
            .type(type)
            .build()
    }
}

