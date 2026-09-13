package edu.equipo2.xiaomios

import android.R
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.menus.MenuActividad
import edu.equipo2.xiaomios.ui.theme.XiaomiOSTheme

import edu.equipo2.xiaomios.menus.MenuIntegrantes
import edu.equipo2.xiaomios.menus.MenuInvestigacion
import edu.equipo2.xiaomios.menus.MenuPortafolio
import edu.equipo2.xiaomios.navbar.BarraNavegacion
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor

enum class MENUS{
    INTEGRANTES,
    PORTAFOLIO,
    INVESTIGACION,
    ACTIVIDAD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XiaomiOSTheme {
                MainContainer()
            }
        }
    }
}


@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MainContainer() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // La barra de navegación no se mostrará mientras haya una actividad abierta (Probablemente debería globalizar esto)
            if (GlobalVariables.actividadCargada == null){
                BarraNavegacion()
            }
        }
    ) { innerPadding ->
        val bottomPadding = innerPadding.calculateBottomPadding()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    bottom = (bottomPadding - 20.dp).coerceAtLeast(0.dp)
                )
        ) {
            when (GlobalVariables.menuActual) {
                MENUS.INTEGRANTES -> MenuIntegrantes()
                MENUS.PORTAFOLIO -> MenuPortafolio()
                MENUS.ACTIVIDAD -> MenuActividad(GlobalVariables.actividadCargada)
                MENUS.INVESTIGACION -> MenuInvestigacion()
                else -> MenuError()
            }
        }
    }

    BackHandler(enabled = (GlobalVariables.actividadCargada != null)) {
        GlobalVariables.actividadCargada = null
        GlobalVariables.menuActual = MENUS.PORTAFOLIO
    }
}

@Composable
fun MenuError(){
    Column(
        modifier = Modifier
            .background(AccentColor)
            .padding(12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Pantalla de error",
            color = BackgroundColor,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "No debería de salir en ningun momento y bajo ninguna acción",
            color = BackgroundColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}