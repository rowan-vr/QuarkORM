package dev.rvr.quark.language.factory

import dev.rvr.quark.language.*
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.io.InputStream

class MigrationParserFactory : ParserFactory<MigrationParser> {
    override fun createFromFile(file: File): MigrationParser {
        if (!file.exists()) throw IllegalArgumentException("File ${file.absolutePath} does not exist")
        return fromCharStream(CharStreams.fromPath(file.toPath()))
    }

    override fun createFromInputStream(inputStream: InputStream): MigrationParser {
        return fromCharStream(CharStreams.fromStream(inputStream))
    }

    private fun fromCharStream(stream: CharStream): MigrationParser {
        val lexer = MigrationLexer(stream)
        return MigrationParser(CommonTokenStream(lexer))
    }
}