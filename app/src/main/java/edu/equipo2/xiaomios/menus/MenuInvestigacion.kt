package edu.equipo2.xiaomios.menus

import edu.equipo2.xiaomios.markdown.parser.MarkdownParser
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import edu.equipo2.xiaomios.markdown.ui.MarkdownRenderer

@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuInvestigacion(){
    // Iniciamos el convertidor de archivo Markdown
    val mdParser = MarkdownParser()
    // Convertimos el contenido del archivo MD en String
    val mdBlocks = mdParser.parse(mdParser.readMarkdown(LocalContext.current, "investigacion/prueba.md"))

    MarkdownRenderer(mdBlocks)

}
