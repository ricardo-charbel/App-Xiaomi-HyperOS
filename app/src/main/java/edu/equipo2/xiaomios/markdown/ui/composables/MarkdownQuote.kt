package edu.equipo2.xiaomios.markdown.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.ui.BuildMarkdownString
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

@Composable
fun MarkdownQuote(block: MarkdownBlock.Quote){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryBackgroundColor)
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 3.dp.toPx()
                )
            }
            .padding(start = 8.dp)
    ) {
        Text(
            text = BuildMarkdownString(block.content),
            style = TextStyle(
                color = SecondaryTextColor
            )
        )
    }
    }
