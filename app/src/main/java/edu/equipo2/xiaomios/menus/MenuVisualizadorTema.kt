package edu.equipo2.xiaomios.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.handlers.MenuHandler.cambiarMenuAnterior
import edu.equipo2.xiaomios.R
import edu.equipo2.xiaomios.handlers.ListaTemas
import edu.equipo2.xiaomios.handlers.TopicHandler.idTemaActual
import edu.equipo2.xiaomios.handlers.TopicHandler.obtenerDetalleTema
import edu.equipo2.xiaomios.markdown.parser.MarkdownDocument
import edu.equipo2.xiaomios.markdown.parser.MarkdownParser
import edu.equipo2.xiaomios.markdown.ui.MarkdownRenderer
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var topicTitleSize = 16.sp

@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuVisualizadorTema(){
    if (idTemaActual == -1){
        Text(text="Error al Cargar Tema $idTemaActual")
        return
    }
    val tema = ListaTemas[idTemaActual]
    val dt = obtenerDetalleTema(tema)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {BarraSuperior(tema.Nombre)},
        bottomBar = {
            if (ListaTemas.size > 1) NavegadorTemas()
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BackgroundColor)
                .padding(
                    innerPadding
                )
        ){
            if (idTemaActual > -1){
                val context = LocalContext.current

                val document by produceState(
                    initialValue = MarkdownDocument(emptyList(), ""),
                    key1 = idTemaActual,
                    key2 = dt.Contenido,
                    key3 = dt.Imagenes
                ) {
                    value = withContext(Dispatchers.IO) {
                        val mdParser = MarkdownParser()
                        val md = mdParser.readMarkdown(context, dt.Contenido)

                        val blocks = withContext(Dispatchers.Default) {
                            mdParser.parse(md)
                        }

                        MarkdownDocument(blocks, dt.Imagenes)
                    }
                }

                key(idTemaActual) {
                    MarkdownRenderer(document)
                }

            }
        }
    }
}

@Composable
private fun BarraSuperior(nombre: String = "Sin Nombre"){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryBackgroundColor)
            .statusBarsPadding()
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        // Botón de Regreso
        Button(
            onClick = {
                cambiarMenuAnterior()
            },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ){
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Button Icon",
                tint = AccentColor
            )
        }

        // Título Superior del Visualizador de Actividad
        Text(
            text = nombre,
            color = AccentColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

    }
}

@Composable
private fun NavegadorTemas(){
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .navigationBarsPadding()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryBackgroundColor)
                .heightIn(96.dp, 96.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            if (idTemaActual > 0){
                CrearBotonTema(idTemaActual - 1)
            }

            if (idTemaActual != ListaTemas.size - 1){
                CrearBotonTema(idTemaActual + 1)
            }
        }
    }
}

@Composable
private fun RowScope.CrearBotonTema(temaId: Int){
    Button(
        modifier = Modifier
            .weight(1f)
            .padding(12.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            idTemaActual = temaId
        }
    ){
        if (temaId > idTemaActual){
            BotonTemaSiguiente(temaId)
        } else {
            BotonTemaAnterior(temaId)
        }
    }
}

@Composable
private fun BotonTemaSiguiente(temaId:Int){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = ListaTemas[temaId].Nombre,
                style = TextStyle(
                    color = Color.White,
                    fontSize = topicTitleSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            )
        }

        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Button Icon",
            tint = Color.White
        )

    }
}

@Composable
private fun BotonTemaAnterior(temaId:Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp),
            painter = painterResource(id = R.drawable.ic_previous),
            contentDescription = "Button Icon",
            tint = Color.White
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = ListaTemas[temaId].Nombre,
                style = TextStyle(
                    color = Color.White,
                    fontSize = topicTitleSize,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}