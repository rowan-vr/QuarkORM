package dev.rvr.quark.language.factory

import dev.rvr.quark.language.QuarkLexer
import dev.rvr.quark.language.QuarkParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.io.InputStream

interface QuarkParserFactory {
    fun createFromFile(file: File): QuarkParser
    fun createFromInputStream(inputStream: InputStream): QuarkParser
}

class StandardQuarkParserFactory : QuarkParserFactory {
    override fun createFromFile(file: File): QuarkParser {
        if (!file.exists()) throw IllegalArgumentException("File ${file.absolutePath} does not exist")
        return fromCharStream(CharStreams.fromPath(file.toPath()))
    }

    override fun createFromInputStream(inputStream: InputStream): QuarkParser {
        return fromCharStream(CharStreams.fromStream(inputStream))
    }

    private fun fromCharStream(stream: CharStream): QuarkParser {
        val lexer = QuarkLexer(stream)
        return QuarkParser(CommonTokenStream(lexer))
    }
}