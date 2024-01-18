package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkSchema

class QuarkSchemaVisitor: QuarkParserBaseVisitor<QuarkSchema>() {
    private val schema = QuarkSchema.Builder()

    override fun visitFile(ctx: QuarkParser.FileContext?): QuarkSchema {
        ctx!!.block().forEach { it.accept(this) }

        return schema.build()
    }

    override fun visitBlock(ctx: QuarkParser.BlockContext?): QuarkSchema? {
        schema.model(ctx!!.model().accept(QuarkModelVisitor()))
        return null
    }
}