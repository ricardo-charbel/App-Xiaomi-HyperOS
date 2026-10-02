package edu.equipo2.xiaomios.markdown.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.markdown.ui.BuildMarkdownString
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.NormalTextSize

@Composable
fun MarkdownOrderedList(block: MarkdownBlock.OrderedList){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        var index = 1
        block.items.forEach{item ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "${index}.",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = NormalTextSize,
                    color = AccentColor
                )

                Text(
                    text = BuildMarkdownString(item),
                    modifier = Modifier.weight(1f),
                    fontSize = NormalTextSize,
                    color = AccentColor
                )

                index++
            }
        }
    }
}