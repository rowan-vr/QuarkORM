package dev.rvr.quark.commons.models

interface QuarkVisitor {
    fun visitModel(model: QuarkModel): QuarkModel?
    fun visitField(field: QuarkModelField): QuarkModelField?
    fun visitSchema(schema: QuarkSchema): QuarkSchema?
}