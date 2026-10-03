package edu.equipo2.xiaomios.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import edu.equipo2.xiaomios.markdown.parser.MarkdownParser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.R
import edu.equipo2.xiaomios.cargarImagen
import edu.equipo2.xiaomios.data.Actividad
import edu.equipo2.xiaomios.data.Actividades
import edu.equipo2.xiaomios.handlers.ListaTemas
import edu.equipo2.xiaomios.handlers.MenuHandler
import edu.equipo2.xiaomios.handlers.Tema
import edu.equipo2.xiaomios.handlers.TopicHandler
import edu.equipo2.xiaomios.markdown.ui.MarkdownRenderer
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor


@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuInvestigacion(){
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .statusBarsPadding()
            .fillMaxSize()
            .padding(24.dp)
    ){
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(SecondaryBackgroundColor)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(id = R.drawable.ic_xiaomi),
                    contentDescription = "Xiaomi Hyper OS",
                    tint = AccentColor
                )

                Text(
                    text = "Xiaomi Hyper OS",
                    style = TextStyle(
                        color = AccentColor,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "Investigación del Sistema Operativo",
                    style = TextStyle(
                        color = AccentColor,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
            }



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "Presione en el tema que desee visualizar",
                    color = AccentColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top=8.dp)
                )
                ListaTemas.forEach{ tm ->
                    BotonTema(tm)
                }
            }
        }



    }
}

@Composable
private fun BotonTema(tm: Tema){
    Button(
        onClick = {
            TopicHandler.visualizarTema(ListaTemas.indexOf(tm))
        },
        modifier = Modifier
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(64.dp, 64.dp)
                .background(SecondaryBackgroundColor)
        ) {
            Image(
                painter = cargarImagen(tm.Imagen),
                contentDescription = "Imagen de Tema",
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)

            )

            Box(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .background(
                        brush = Brush.horizontalGradient(
                            colorStops = arrayOf(
                                0.0f to SecondaryBackgroundColor,
                                0.83f to SecondaryBackgroundColor,
                                0.90f to SecondaryBackgroundColor.copy(alpha = 0.65f),
                                1.0f to Color.Transparent
                            )
                        )
                    )

            ){
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ){

                    Text(
                        text = tm.Nombre,
                        fontSize = 18.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}