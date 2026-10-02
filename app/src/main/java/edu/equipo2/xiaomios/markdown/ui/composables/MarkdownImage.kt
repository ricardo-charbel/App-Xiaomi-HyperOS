package edu.equipo2.xiaomios.markdown.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.cargarImagen
import edu.equipo2.xiaomios.markdown.parser.MarkdownBlock
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

@Composable
fun MarkdownImage(block: MarkdownBlock.Image) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            contentDescription = block.alt,
            painter = cargarImagen(block.path),
            modifier = Modifier.size(140.dp)
        )

        Text(
            text = block.alt,
            style = TextStyle(
                color = SecondaryTextColor,
                fontSize = 12.sp
            )
        )
    }
}