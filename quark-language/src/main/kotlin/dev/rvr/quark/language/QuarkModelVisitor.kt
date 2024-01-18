package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkModel

class QuarkModelVisitor: QuarkParserBaseVisitor<QuarkModel>() {
    private val model = QuarkModel.Builder()
    override fun visitModel(ctx: QuarkParser.ModelContext?): QuarkModel {
        ctx!!.modelid().accept(this)
        ctx.modelbody().accept(this)
        return model.build()
    }

    override fun visitModelid(ctx: QuarkParser.ModelidContext?): QuarkModel? {
        model.name(ctx!!.ID().text)
        return null
    }

    override fun visitModelbody(ctx: QuarkParser.ModelbodyContext?): QuarkModel? {
        ctx!!.field().forEach {
            model.field(it.accept(QuarkModelFieldVisitor()))
        }
        return null
    }
}