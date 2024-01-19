package dev.rvr.quark.language.factory

import dev.rvr.quark.language.*
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.io.InputStream

interface SchemaParserFactory {
    fun createFromFile(file: File): SchemaParser
    fun createFromInputStream(inputStream: InputStream): SchemaParser
}

class StandardQuarkParserFactory : SchemaParserFactory {
    override fun createFromFile(file: File): SchemaParser {
        if (!file.exists()) throw IllegalArgumentException("File ${file.absolutePath} does not exist")
        return fromCharStream(CharStreams.fromPath(file.toPath()))
    }

    override fun createFromInputStream(inputStream: InputStream): SchemaParser {
        return fromCharStream(CharStreams.fromStream(inputStream))
    }

    private fun fromCharStream(stream: CharStream): SchemaParser {
        val lexer = SchemaLexer(stream)
        return SchemaParser(CommonTokenStream(lexer))
    }
}