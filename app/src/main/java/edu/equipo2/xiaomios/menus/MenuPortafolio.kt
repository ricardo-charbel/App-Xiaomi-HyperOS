package edu.equipo2.xiaomios.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.data.Actividad
import edu.equipo2.xiaomios.data.Actividades
import edu.equipo2.xiaomios.handlers.MenuHandler
import edu.equipo2.xiaomios.cargarImagen

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
fun MenuPortafolio(){
    val scrollState = rememberScrollState()
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
                modifier = Modifier.padding(bottom=8.dp)
                    .statusBarsPadding()
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
private fun BotonActividad(act: Actividad){
    Button(
        onClick = {
                    MenuHandler.visualizarActividad(act)
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
                painter = cargarImagen(act.Imagen),
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
                        text = "Actividad ${act.Numero.toString()}",
                        fontSize = 16.sp,
                        color = SecondaryTextColor,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = act.Nombre,
                        fontSize = 18.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}