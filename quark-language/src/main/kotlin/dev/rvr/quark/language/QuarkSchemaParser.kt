package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkSchema
import dev.rvr.quark.language.QuarkParser
import dev.rvr.quark.language.QuarkSchemaVisitor

class QuarkSchemaParser constructor(private val quarkParser: QuarkParser) {
    fun parse(): QuarkSchema {
        return quarkParser.file().accept(QuarkSchemaVisitor())
    }
}