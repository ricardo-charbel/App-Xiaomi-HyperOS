package edu.equipo2.xiaomios

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.handlers.MenuHandler.cambiarMenuAnterior
import edu.equipo2.xiaomios.menus.MenuActividad
import edu.equipo2.xiaomios.ui.theme.XiaomiOSTheme

import edu.equipo2.xiaomios.menus.MenuIntegrantes
import edu.equipo2.xiaomios.menus.MenuInvestigacion
import edu.equipo2.xiaomios.menus.MenuPortafolio
import edu.equipo2.xiaomios.components.BarraNavegacion
import edu.equipo2.xiaomios.handlers.ListaTemas
import edu.equipo2.xiaomios.handlers.MENUS
import edu.equipo2.xiaomios.handlers.MenuHandler
import edu.equipo2.xiaomios.handlers.MenuHandler.barraNavegacion
import edu.equipo2.xiaomios.handlers.TopicHandler.idTemaActual
import edu.equipo2.xiaomios.menus.MenuVisualizadorTema
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.BackgroundColor

// Función inicial del programa y la primera en ser llamada para su ejecución
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

// Para testing. Esto permite visualizar la aplicación dentro de Android Studio en la resolución de un celular.
@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable // Contenedor principal de la aplicación
fun MainContainer() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // La barra de navegación no se mostrará mientras haya una actividad abierta (Probablemente debería globalizar esto)
            if (barraNavegacion){
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
            when (MenuHandler.menuActual) {
                MENUS.INTEGRANTES -> MenuIntegrantes()
                MENUS.PORTAFOLIO -> MenuPortafolio()
                MENUS.ACTIVIDAD -> MenuActividad(GlobalVariables.actividadCargada)
                MENUS.INVESTIGACION -> MenuInvestigacion()
                MENUS.VISUALIZADOR_TEMA -> MenuVisualizadorTema()
                else -> MenuError()
            }
        }
    }

    BackHandler(enabled = MenuHandler.submenu) {
        cambiarMenuAnterior()
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
            text = "No debería de salir en ningún momento y bajo ninguna acción",
            color = BackgroundColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}