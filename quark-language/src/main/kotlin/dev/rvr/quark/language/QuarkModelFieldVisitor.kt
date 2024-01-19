package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkModelField

class QuarkModelFieldVisitor : SchemaParserBaseVisitor<QuarkModelField>() {
    private val builder = QuarkModelField.Builder();

    override fun visitField(ctx: SchemaParser.FieldContext?): QuarkModelField {
        ctx!!.fieldname().accept(this)
        ctx.fieldtype().accept(this)
        return builder.build()
    }

    override fun visitFieldname(ctx: SchemaParser.FieldnameContext?): QuarkModelField? {
        builder.name(ctx!!.ID().text)
        return null;
    }

    override fun visitFieldtype(ctx: SchemaParser.FieldtypeContext?): QuarkModelField? {
        builder.type(ctx!!.ID().text)
        return null;
    }
}