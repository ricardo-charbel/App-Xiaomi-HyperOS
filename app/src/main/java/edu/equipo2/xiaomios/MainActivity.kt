package edu.equipo2.xiaomios

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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

import edu.equipo2.xiaomios.menus.MenuPortada
import edu.equipo2.xiaomios.menus.MenuInvestigacion
import edu.equipo2.xiaomios.menus.MenuPortafolio
import edu.equipo2.xiaomios.components.BarraNavegacion
import edu.equipo2.xiaomios.handlers.MENUS
import edu.equipo2.xiaomios.handlers.MenuHandler
import edu.equipo2.xiaomios.handlers.MenuHandler.barraNavegacion
import edu.equipo2.xiaomios.handlers.MenuHandler.menuActual
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

// Cualquier mención de @Preview es unicamente para probar la interfaz dentro de Android Studio
@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable // Contenedor principal de la aplicación
fun MainContainer() {
    // El scaffold es una estructura de aplicación que permite mantener componentes como barras inferiores y superiores junto al contenido principal de la app.
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // La variable "barraNavegacion" es la que determina si se mostrará dicha barra de navegación en el menu actual
            // Ver MenuHandler.kt para una mejor explicación.
            if (barraNavegacion){
                BarraNavegacion()
            }
        }
    ) { innerPadding ->
        // Pequeño ajuste de espacio para acomodar el contenido entre las barras superiores e inferiores de la app
        val bottomPadding = innerPadding.calculateBottomPadding()

        // Contenedor principal de los menus
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    bottom = (bottomPadding - 20.dp).coerceAtLeast(0.dp)
                )
        ) {
            // El animated content se agregó para añadir una transición más suave entre pantallas
            // En este caso y como se muestra, un efecto "Crossfade"
            AnimatedContent(
                targetState = menuActual, // Toma de referencia la variable menuActual
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) togetherWith
                    fadeOut(animationSpec = tween(500))
                },
                label = "menuContent"
            ){ targetMenu ->
                // Dependiendo del menu seleccionado, se hace la transición adecuada
                when(targetMenu){
                    MENUS.PORTADA -> MenuPortada()
                    MENUS.PORTAFOLIO -> MenuPortafolio()
                    MENUS.ACTIVIDAD -> MenuActividad(GlobalVariables.actividadCargada)
                    MENUS.INVESTIGACION -> MenuInvestigacion()
                    MENUS.VISUALIZADOR_TEMA -> MenuVisualizadorTema()
                    else -> MenuError()
                }
            }
        }
    }

    // Traduciendolo de la manera mas literal posible, el "administrador" de regreso
    // se encarga de administrar lo relacionado a lo que sucede cuando el usuario decide ir hacia "atrás" en la app
    // Si estamos en un submenu, se regresa al menu principal del que vino el usuario o caso contrario, cierra la app
    // Depende del argumento "enabled" el que se ejecute cambiarMenuAnterior
    BackHandler(enabled = MenuHandler.submenu) {
        cambiarMenuAnterior()
    }
}

// Menu de prueba y de error, solo aparece si se accede a un menu no configurado, lo cual es imposible.
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