package edu.equipo2.xiaomios.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.handlers.MENUS
import edu.equipo2.xiaomios.handlers.MenuHandler
import edu.equipo2.xiaomios.R

import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

data class BotonNavegacion(
    val Nombre: String,
    val Icono: Int,
    val Menu: MENUS
)
val Botones = listOf<BotonNavegacion>(
    BotonNavegacion("Portada", R.drawable.ic_portada, MENUS.PORTADA),
    BotonNavegacion("Portafolio", R.drawable.ic_portafolio, MENUS.PORTAFOLIO),
    BotonNavegacion("Investigación", R.drawable.ic_xiaomi, MENUS.INVESTIGACION)
)

@Preview (
    showBackground = false,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun BarraNavegacion(){
    Column(
        verticalArrangement = Arrangement.Bottom,
        // Padding para mover los botones de la barra de navegación en caso de que la navegación por botones de Android está activada
        modifier = Modifier.navigationBarsPadding()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryBackgroundColor)
                .heightIn(96.dp, 96.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Botones.forEach{Boton -> CrearBotonNavegacion(Boton)}
        }
    }
}

@Composable
fun RowScope.CrearBotonNavegacion(Boton: BotonNavegacion){
    Button(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
        ,
        onClick = {
            MenuHandler.cambiarMenu(Boton.Menu)
                  },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ){


        val isActive = Boton.Menu == MenuHandler.menuActual
        val mainColor: Color by animateColorAsState(if (isActive) AccentColor else SecondaryTextColor)
        val mainSize: Dp by animateDpAsState(if (isActive) 36.dp else 32.dp)
        //val mainColor = if (isActive) AccentColor else SecondaryTextColor
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                modifier = Modifier.size(mainSize),
                painter = painterResource(id = Boton.Icono),
                contentDescription = "Button Icon",
                tint = mainColor
            )
            Text(
                text = Boton.Nombre.uppercase(),
                color = mainColor,
                fontSize = 12.sp
            )
        }
    }
}
