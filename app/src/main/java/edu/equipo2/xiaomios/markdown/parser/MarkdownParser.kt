package edu.equipo2.xiaomios.markdown.parser

import android.content.Context

private fun parseHeading(line: String): MarkdownBlock.Heading?{
    val match = Regex("^(#{1,6})\\s+(.*)$").find(line.trim()) ?: return null

    val level = match.groupValues[1].length
    val text = match.groupValues[2]

    return MarkdownBlock.Heading(
        level = level,
        content = parseInline(text)
    )
}

private fun parseImage(line: String): MarkdownBlock.Image?{
    val match = Regex("^!\\[(.*)]\\((.*)\\)$").find(line.trim()) ?: return null

    return MarkdownBlock.Image(
        alt = match.groupValues[1],
        path = match.groupValues[2]
    )
}

private fun parseInline(text: String): List<MarkdownInline>{
    val result = mutableListOf<MarkdownInline>()
    val regex = Regex("""(\*\*\*.*?\*\*\*|\*\*.*?\*\*|\*.*?\*|`.*?`|\[.*?]\(.*?\))""")
    var lastIndex = 0

    for (match in regex.findAll(text)){
        if (match.range.first > lastIndex){
            result.add(
                MarkdownInline.Text(
                    text.substring(lastIndex, match.range.first)
                )
            )
        }

        val token = match.value
        when {
            // Texto en Negritas e Inclinado
            token.startsWith("***") -> {
                result.add(MarkdownInline.BoldItalic(token.removePrefix("***").removeSuffix("***")))
            }

            // Texto en Negritas
            token.startsWith("**") -> {
                result.add(MarkdownInline.Bold(token.removePrefix("**").removeSuffix("**")))
            }

            // Texto Inclinado
            token.startsWith("*") -> {
                result.add(
                    MarkdownInline.Italic(
                        token.removePrefix("*")
                            .removeSuffix("*")
                    )
                )
            }

            // Código
            token.startsWith("`") -> {
                result.add(
                    MarkdownInline.Code(
                        token.removePrefix("`")
                            .removeSuffix("`")
                    )
                )
            }

            token.startsWith("[") ->{
                val link = Regex("""^\[(.*?)]\((.*?)\)$""").find(token)

                if (link != null){
                    result.add(
                        MarkdownInline.Link(
                            text = link.groupValues[1],
                            url = link.groupValues[2]
                        )
                    )
                }
            }
        }
        lastIndex = match.range.last + 1
    }

    if (lastIndex < text.length){
        result.add(
            MarkdownInline.Text(
                text.substring(lastIndex)
            )
        )
    }
    return result
}

private fun isBlockStart(line: String): Boolean{
    val trimmed = line.trim()

    return trimmed.startsWith("#") ||
            trimmed.startsWith(">") ||
            trimmed.startsWith("-") ||
            trimmed.matches(Regex("^\\d+\\.\\s+.*")) ||
            trimmed.matches(Regex("^!\\[.*]\\(.*\\)$")) ||
            trimmed == "---"
}

private fun isUnorderedList(line: String): Boolean{
    return line.trim().startsWith("- ")
}

private fun isOrderedList(line: String): Boolean{
    return line.trim().matches(
        Regex("^\\d+\\.\\s+.*")
    )
}

class MarkdownParser {
    fun readMarkdown(context: Context, path:String): String{
        return context.assets
            .open(path)
            .bufferedReader()
            .use{it.readText()}
    }

    fun parse(markdown:String): List<MarkdownBlock>{
        val lines = markdown
            .replace("\r\n", "\n")
            .split("\n")
        val blocks = mutableListOf<MarkdownBlock>()
        var index = 0

        while (index < lines.size){
            val line = lines[index]

            // Línea vacía
            if (line.isBlank()){
                index++
                continue
            }

            // Titulo
            parseHeading(line)?.let{
                blocks.add(it)
                index++
                continue
            }

            // Divisor
            if (line.trim() == "---"){
                blocks.add(MarkdownBlock.Divider)
                index++
                continue
            }

            // Imagen
            parseImage(line)?.let{
                blocks.add(it)
                index++
                continue
            }

            // Cita
            if (line.trim().startsWith(">")){
                val quoteLines = mutableListOf<String>()
                while(index < lines.size && lines[index].trim().startsWith(">")){
                    quoteLines.add(
                        lines[index]
                            .trim()
                            .removePrefix(">")
                            .trim()
                    )

                    index++
                }

                val text = quoteLines.joinToString(" ")
                blocks.add(
                    MarkdownBlock.Quote(
                        parseInline(text)
                    )
                )
            }

            // Lista Sin Orden
            if (isUnorderedList(line)){
                val items = mutableListOf<List<MarkdownInline>>()
                while(index < lines.size && isUnorderedList(lines[index])){
                    val item = lines[index]
                        .trim()
                        .removePrefix("-")
                        .trim()

                    items.add(parseInline(item))
                    index++
                }
                blocks.add(
                    MarkdownBlock.UnorderedList(items)
                )
                continue
            }

            // Lista Ordenada
            if (isOrderedList(line)){
                val items = mutableListOf<List<MarkdownInline>>()
                while(index < lines.size && isOrderedList(lines[index])){
                    val item  = lines[index]
                        .trim()
                        .replaceFirst(Regex("^\\d+\\.\\s*"), "")
                    items.add(parseInline(item))
                    index++
                }

                blocks.add(MarkdownBlock.OrderedList(items))
                continue
            }

            // Parrafo
            val paragraphLines = mutableListOf<String>()
            while(index < lines.size && lines[index].isNotBlank() && !isBlockStart(lines[index])){
                paragraphLines.add(lines[index])
                index++
            }

            val paragraph = paragraphLines.joinToString(" ")
            blocks.add(
                MarkdownBlock.Paragraph(parseInline(paragraph))
            )
        }

        return blocks
    }
}