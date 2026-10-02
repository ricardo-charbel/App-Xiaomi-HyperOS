package edu.equipo2.xiaomios.markdown.ui.composables
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.ui.BuildMarkdownString
import edu.equipo2.xiaomios.ui.theme.AccentColor


@Composable
fun MarkdownParagraph(block: MarkdownBlock.Paragraph){
    val annotatedString = BuildMarkdownString(block.content)

    Text(
        text = annotatedString,
        modifier = Modifier.padding(vertical = 6.dp),
        style = TextStyle(
            fontSize = 12.sp,
            textIndent = TextIndent(firstLine = 24.sp, restLine = 0.sp),
            color = AccentColor,
            textAlign = TextAlign.Justify
        )
    )
}