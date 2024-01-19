package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkSchema

class QuarkSchemaVisitor: SchemaParserBaseVisitor<QuarkSchema>() {
    private val schema = QuarkSchema.Builder()

    override fun visitFile(ctx: SchemaParser.FileContext?): QuarkSchema {
        ctx!!.block().forEach { it.accept(this) }

        return schema.build()
    }

    override fun visitBlock(ctx: SchemaParser.BlockContext?): QuarkSchema? {
        schema.model(ctx!!.model().accept(QuarkModelVisitor()))
        return null
    }
}