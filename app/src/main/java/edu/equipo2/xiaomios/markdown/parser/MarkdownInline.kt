package edu.equipo2.xiaomios.markdown.parser

interface MarkdownInline {
    data class Text(
        val value: String
    ) : MarkdownInline

    data class Bold(
        val value: String
    ) : MarkdownInline

    data class Italic(
        val value: String
    ) : MarkdownInline

    data class BoldItalic(
        val value: String
    ) : MarkdownInline

    data class Code(
        val value: String
    ) : MarkdownInline

    data class Link(
        val text: String,
        val url: String
    ) : MarkdownInline
}