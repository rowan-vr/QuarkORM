package dev.rvr.quark.language

import dev.rvr.quark.commons.models.QuarkSchema

class QuarkSchemaParser constructor(private val quarkParser: SchemaParser) {
    fun parse(): QuarkSchema {
        return quarkParser.file().accept(QuarkSchemaVisitor())
    }
}