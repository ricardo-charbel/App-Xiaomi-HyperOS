package edu.equipo2.xiaomios.menus

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.R
import edu.equipo2.xiaomios.cargarImagen
import edu.equipo2.xiaomios.data.Integrantes
import edu.equipo2.xiaomios.data.Integrante

import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.round
import kotlin.math.roundToInt


@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuPortada(){
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            DatosPrincipales()
            Spacer(modifier = Modifier.height(8.dp))
            Integrantes()
        }
    }
}

@Composable
fun DatosPrincipales(){

    Text(
        text = "Sistemas Operativos",
        color = AccentColor,
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
    )

    Text(
        text = "Producto Integrador de Aprendizaje",
        color = AccentColor,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

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
            text = "Investigación del Sistema Operativo",
            style = TextStyle(
                color = AccentColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
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


    }

    Spacer(modifier = Modifier.height(8.dp))

    // Datos Generales
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(SecondaryBackgroundColor)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Semestre",
            style = TextStyle(
                fontSize = 18.sp,
                color = SecondaryTextColor
            )
        )

        Text(
            text = "Agosto-Diciembre 2026",
            style = TextStyle(
                fontSize = 20.sp,
                color = AccentColor,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Catedrático",
            style = TextStyle(
                fontSize = 18.sp,
                color = SecondaryTextColor
            )
        )

        Text(
            text = "Dra. Norma Edith Marin Martínez",
            style = TextStyle(
                fontSize = 20.sp,
                color = AccentColor,
                fontWeight = FontWeight.Bold
            )
        )
        // Datos de Grupo
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ){
            Column(){
                Text(
                    text = "Hora",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = SecondaryTextColor
                    )
                )

                Text(
                    text = "M6",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Column(){
                Text(
                    text = "Grupo",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = SecondaryTextColor
                    )
                )

                Text(
                    text = "006",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Column(){
                Text(
                    text = "Plan",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = SecondaryTextColor
                    )
                )

                Text(
                    text = "440",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Column(){
                Text(
                    text = "Elaboró",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = SecondaryTextColor
                    )
                )

                Text(
                    text = "Equipo 2",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Column(){
                Text(
                    text = "Aula",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = SecondaryTextColor
                    )
                )

                Text(
                    text = "4211",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

// Sección Integrantes
@Composable
fun Integrantes(){
    Text(
        text = "Integrantes",
        color = AccentColor,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
    )
    Text(
        text = "Deslice hacia abajo para ver al resto de integrantes",
        color = SecondaryTextColor,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Integrantes.forEach { intg ->
            EtiquetaIntegrante(intg)
        }
    }
}

@Composable
fun EtiquetaIntegrante(Integrante: Integrante){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(SecondaryBackgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        DatosIntegrante(Integrante)
    }
}

@Composable
fun RowScope.DatosIntegrante(Integrante: Integrante){
    val porcentaje = remember({Animatable(0f)})
    /*val porcentajeTexto by remember {
        derivedStateOf { (porcentaje.value * 100).toInt() }
    }*/

    LaunchedEffect(Unit) {
        delay(100)
        launch {porcentaje.animateTo(Integrante.Participacion,
            tween(1500, easing = EaseOut))}
    }

    Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically
    ){
        // Imagen del Integrante
        Image(
            painter = cargarImagen(Integrante.Imagen),
            contentDescription = "Imagen de Integrante",
            modifier = Modifier
                .clip(RoundedCornerShape(64.dp))
                .size(64.dp)
                .background(Color.White)

        )

        // Datos
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center

        ){
            Text( // Matricula
                text = Integrante.Matricula + " - " + Integrante.Carrera,
                color = SecondaryTextColor,
                fontSize = 16.sp
            )

            Text( // Nombre
                text= arrayOf(Integrante.ApPaterno, Integrante.ApMaterno, Integrante.Nombre).joinToString(" "),
                color = AccentColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            if (Integrante.Lider){
                Text( // Nombre
                    text= "Lider del Equipo",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(AccentColor)
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)

                )
            }
        }

        // Barra de Participación
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Participación",
                style = TextStyle(
                    color = AccentColor,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(
                    progress = { Integrante.Participacion },
                    modifier = Modifier.fillMaxSize(),
                    color = AccentColor,
                    strokeWidth = 6.dp,
                    trackColor = BackgroundColor,
                    strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )

                Text(
                    text = "${(Integrante.Participacion * 100f).roundToInt()}%",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = AccentColor
                    )
                )
            }
        }
    }
}