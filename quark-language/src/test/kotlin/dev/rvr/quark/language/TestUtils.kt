package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkModel
import dev.rvr.quark.commons.models.QuarkModelField
import dev.rvr.quark.commons.models.QuarkSchema

fun model(name: String, field: List<QuarkModelField>): QuarkModel {
    val builder = QuarkModel.Builder().name(name)
    field.forEach { builder.field(it) }
    return builder.build()
}

fun field(name: String, type: String): QuarkModelField {
    return QuarkModelField.Builder().name(name).type(type).build()
}

fun schema(model: List<QuarkModel>): QuarkSchema {
    val builder = QuarkSchema.Builder()
    model.forEach { builder.model(it) }
    return builder.build()
}