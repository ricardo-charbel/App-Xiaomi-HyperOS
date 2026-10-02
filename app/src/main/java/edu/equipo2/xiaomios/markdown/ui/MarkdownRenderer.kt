package edu.equipo2.xiaomios.markdown.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownHeading
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownImage
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownOrderedList
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownParagraph
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownQuote
import edu.equipo2.xiaomios.markdown.ui.composables.MarkdownUnorderedList

@Composable
fun MarkdownRenderer(blocks: List<MarkdownBlock>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
      blocks.forEach { block ->
          when (block){
              is MarkdownBlock.Heading -> MarkdownHeading(block)
              is MarkdownBlock.Paragraph -> MarkdownParagraph(block)
              is MarkdownBlock.OrderedList -> MarkdownOrderedList(block)
              is MarkdownBlock.UnorderedList -> MarkdownUnorderedList(block)
              is MarkdownBlock.Image -> MarkdownImage(block)
              is MarkdownBlock.Quote -> MarkdownQuote(block)

              MarkdownBlock.Divider -> HorizontalDivider()
          }
      }
    }
}