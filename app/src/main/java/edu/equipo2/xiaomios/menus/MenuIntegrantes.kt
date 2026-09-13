package edu.equipo2.xiaomios.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.R

import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

data class Integrante(
    val Nombre: String,
    val ApPaterno: String,
    val ApMaterno: String,
    val Matricula: String,
    val Carrera:String,
    var Imagen: Int,
    val Lider: Boolean = false
    )

val Integrantes = listOf<Integrante>(
    Integrante("Gerardo Alberto", "Bautista", "Hernández", "2062418", "ITS", R.drawable.int_gerardo),
    Integrante("Karla Sarahí", "Chávez", "Tamez", "2169086", "IAS", R.drawable.int_karla),
    Integrante("Oscar Ernesto", "Bustos", "Mercado", "2117393", "IAS", R.drawable.int_oscar),
    Integrante("Diego Armando", "Esparza", "Flores", "1908457", "ITS", R.drawable.int_diego),
    Integrante("Marco Giovanni", "García", "Alvares", "2153913", "IAS", R.drawable.int_marco),
    Integrante("Victor Alain", "Hernández", "Jaraleño", "1945128", "ITS", R.drawable.int_victor),
    Integrante("Ricardo Charbel", "Ibarra", "Miranda", "2117545", "IAS", R.drawable.int_ricardo),
    Integrante("Francisco", "Mora", "Ruiz", "2226912", "ITS", R.drawable.int_francisco),
    Integrante("Angélica", "Reyna", "García", "2144918", "ITS", R.drawable.int_angela),
    Integrante("Maximiliano", "Romero", "García", "2226867", "ITS", R.drawable.int_maximiliano),
    Integrante("Alberto", "Treviño", "Menchaca", "2154205", "IAS", R.drawable.int_alberto, true),
    Integrante("Camila", "Trujillo", "Quintanilla", "2162775", "ITS", R.drawable.int_camila)
)

@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuIntegrantes(){
    var scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .padding(24.dp)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Integrantes del Equipo 2",
                color = AccentColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=32.dp, bottom=8.dp)
            )

            Text(
                text = "Deslize hacia abajo para ver al resto de integrantes del equipo",
                color = SecondaryTextColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom=8.dp)
            )


            Column(
                modifier = Modifier.fillMaxWidth()
                    .verticalScroll(scrollState)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){

                Integrantes.forEach { intg ->
                    EtiquetaIntegrante(intg)
               }
            }
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
    ){
        // IMAGEN DEL INTEGRANTE
        Image(
            painter = painterResource(id = Integrante.Imagen),
            contentDescription = "Imagen de Integrante",
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(64.dp))
                .size(64.dp, 64.dp)
                .background(Color.White)

        )

        // Datos
        Column(
            modifier = Modifier.padding(8.dp).fillMaxHeight(),
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
    }
}