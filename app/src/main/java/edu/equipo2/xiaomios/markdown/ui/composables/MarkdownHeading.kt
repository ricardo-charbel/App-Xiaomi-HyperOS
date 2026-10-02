package edu.equipo2.xiaomios.markdown.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.ui.BuildMarkdownString
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.ChapterHeaderSize
import edu.equipo2.xiaomios.ui.theme.SubtopicHeaderSize
import edu.equipo2.xiaomios.ui.theme.TopicHeaderSize

@Composable
fun MarkdownHeading(block: MarkdownBlock.Heading){
    val level = block.level
    val annotatedString = BuildMarkdownString(block.content)
    var defStyle = TextStyle(
        color = AccentColor,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )


    when(level){
        1 -> {
            defStyle = TextStyle(
                color = AccentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = ChapterHeaderSize
            )
        }

        2 -> {
            defStyle = TextStyle(
                color = AccentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = TopicHeaderSize
            )
        }

        3 -> {
            defStyle = TextStyle(
                color = AccentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = SubtopicHeaderSize
            )
        }


    }

    Text(
        text = annotatedString,
        style = defStyle,
        modifier = Modifier.fillMaxWidth()
    )
}