package edu.equipo2.xiaomios.markdown.parser
// Si está en inglés no es por IA, mi manera de trabajar rodea mucho el inglés x)

interface MarkdownBlock {
    data class Heading(
        val level:Int,
        val content: List<MarkdownInline>
    ) : MarkdownBlock

    data class Paragraph(
        val content: List<MarkdownInline>
    ) : MarkdownBlock

    data class Quote(
        val content: List<MarkdownInline>
    ) : MarkdownBlock

    data class UnorderedList(
        val items: List<List<MarkdownInline>>
        ) : MarkdownBlock

    data class OrderedList(
        val items: List<List<MarkdownInline>>
    ) : MarkdownBlock

    data class Image(
        val alt: String,
        var path: String,
        val size: Int = 100
    ) : MarkdownBlock

    data class Table(
        val headings: List<String>,
        val rows: List<List<String>>
    ) : MarkdownBlock

    data object Divider : MarkdownBlock
}