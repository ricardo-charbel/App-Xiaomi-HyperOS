package edu.equipo2.xiaomios.menus

import android.graphics.Paint
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.GlobalVariables
import edu.equipo2.xiaomios.MENUS
import edu.equipo2.xiaomios.MainActivity
import edu.equipo2.xiaomios.R

import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

data class Actividad(
    val Numero: Int,
    val Nombre: String,
    val Imagen: Int,
    val NombreArchivo:String
)

val Actividades = listOf<Actividad>(
    Actividad(1, "Arquitectura, Desempeño y tipos de un Sistema Operativo", R.drawable.ic_act1, "af_1.pdf"),
    Actividad(2, "Multitarea y Control de Concurrencia", R.drawable.ic_act2, "af_2.pdf")
)

@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuPortafolio(){
    var scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .padding(24.dp)
            .fillMaxSize()
    ){
        Column(){
            Text(
                text = "Portafolio de Actividades",
                color = AccentColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=32.dp, bottom=8.dp)
            )

            Text(
                text = "Presione en la actividad que desee visualizar",
                color = SecondaryTextColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom=8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Actividades.forEach{act ->
                    BotonActividad(act)
                }
            }
        }



    }
}

@Composable
fun BotonActividad(Actividad: Actividad){
    Button(
        onClick = {
                    GlobalVariables.actividadCargada = Actividad
                    GlobalVariables.menuActual = MENUS.ACTIVIDAD
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
                .heightIn(96.dp, 96.dp)
                .background(SecondaryBackgroundColor)
        ) {
            Image(
                painter = painterResource(id = Actividad.Imagen),
                contentDescription = "Imagen de Actividad",
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)

            )

            Box(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                SecondaryBackgroundColor,
                                SecondaryBackgroundColor,
                                SecondaryBackgroundColor,
                                SecondaryBackgroundColor,
                                Color.Transparent
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
                        text = "Actividad ${Actividad.Numero.toString()}",
                        fontSize = 16.sp,
                        color = SecondaryTextColor,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = Actividad.Nombre,
                        fontSize = 18.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}