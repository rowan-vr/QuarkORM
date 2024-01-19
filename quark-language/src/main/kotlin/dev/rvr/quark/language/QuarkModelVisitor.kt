package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkModel

class QuarkModelVisitor: SchemaParserBaseVisitor<QuarkModel>() {
    private val model = QuarkModel.Builder()
    override fun visitModel(ctx: SchemaParser.ModelContext?): QuarkModel {
        ctx!!.modelid().accept(this)
        ctx.modelbody().accept(this)
        return model.build()
    }

    override fun visitModelid(ctx: SchemaParser.ModelidContext?): QuarkModel? {
        model.name(ctx!!.ID().text)
        return null
    }

    override fun visitModelbody(ctx: SchemaParser.ModelbodyContext?): QuarkModel? {
        ctx!!.field().forEach {
            model.field(it.accept(QuarkModelFieldVisitor()))
        }
        return null
    }
}