package dev.rvr.quark.language.factory

import java.io.File
import java.io.InputStream

interface ParserFactory<out T> {
    fun createFromFile(file: File): T
    fun createFromInputStream(inputStream: InputStream): T
}