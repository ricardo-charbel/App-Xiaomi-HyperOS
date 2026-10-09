package edu.equipo2.xiaomios.markdown.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.parser.MarkdownDocument
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownHeading
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownImage
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownOrderedList
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownParagraph
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownQuote
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownTable
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownUnorderedList

@Composable
fun MarkdownRenderer(document: MarkdownDocument){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ){
        val blocks = document.blocks
      blocks.forEach { block ->
          when (block){
              is MarkdownBlock.Heading -> MarkdownHeading(block)
              is MarkdownBlock.Paragraph -> MarkdownParagraph(block)
              is MarkdownBlock.OrderedList -> MarkdownOrderedList(block)
              is MarkdownBlock.UnorderedList -> MarkdownUnorderedList(block)
              is MarkdownBlock.Image -> {
                  val basePath = document.basePath.trim('/')
                  val imagePath = block.path.trimStart('/')

                  val resolvedPath = when {
                      basePath.isBlank() -> imagePath
                      imagePath == basePath -> imagePath
                      imagePath.startsWith("$basePath/") -> imagePath
                      else -> "$basePath/$imagePath"
                  }

                  MarkdownImage(
                      block.copy(path = resolvedPath)
                  )
              }
              is MarkdownBlock.Quote -> MarkdownQuote(block)
              is MarkdownBlock.Table -> MarkdownTable(block)

              MarkdownBlock.Divider -> HorizontalDivider()
          }
      }
    }
}