package edu.equipo2.xiaomios.handlers

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.equipo2.xiaomios.data.Actividad
import edu.equipo2.xiaomios.handlers.TopicHandler
import edu.equipo2.xiaomios.GlobalVariables.actividadCargada
import edu.equipo2.xiaomios.handlers.TopicHandler.idTemaActual

// Los diferentes tipos de Menus se encuentran enumerados en el siguiente ENUM
enum class MENUS{
    NINGUNO,            // No representa a ningún menú, utilizado como valor NULO
    INTEGRANTES,        // Muestra a los integrantes del equipo y sus datos personales.
    PORTAFOLIO,         // Muestra el portafolio de actividades fundamentales.
    ACTIVIDAD,          // SUBMENU. Muestra el PDF de la actividadCargada
    // IMPORTANTE: Este menu solo se muestra cuando actividadCargada no tenga valores nulos
    INVESTIGACION,      // Muestra la investigación del PIA sobre el Sistema Operativo elegido.
    VISUALIZADOR_TEMA   // SUBMENU. Muestra información del tema seleccionado desde el Menu anterior.
    // IMPORTANTE: Este menu solo se muestra cuando idTemaActual no sea igual a -1 o a un tema inexistente
}

data class DetalleMenu(
    val menu: MENUS,                    // Menu que se desea cargar
    val submenu: Boolean = false,       // Si es Submenu, al ir hacia atrás, la aplicación NO se cerrará
    val barraNavegacion: Boolean = true // Dependiendo de su valor, mostrará o no el menu de navegación global
)

val ListaMenus = listOf<DetalleMenu>(
    DetalleMenu(MENUS.NINGUNO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.INTEGRANTES, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.PORTAFOLIO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.ACTIVIDAD, submenu = true, barraNavegacion = false),
    DetalleMenu(MENUS.INVESTIGACION, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.VISUALIZADOR_TEMA, submenu = true, barraNavegacion = false)
)

object MenuHandler{
    // En que Menu está el usuario
    var menuActual by mutableStateOf(MENUS.INTEGRANTES)
    // En que Menu ESTUVO el usuario
    var menuAnterior by mutableStateOf(MENUS.NINGUNO)
    // Si esta variable está activa, al darle al botón de regresar, se regresará al menu anterior
    var submenu by mutableStateOf(false)
    // Si esta variable está activa, se mostrará la barra de navegación
    var barraNavegacion by mutableStateOf(true)

    fun cambiarMenuAnterior(){
        if (!submenu) return
        cambiarMenu(menuAnterior)
    }

    fun cambiarMenu(menu: MENUS){
        if (menuActual == menu) return
        if (menuActual == menuAnterior) return
        if (menuActual == MENUS.ACTIVIDAD) actividadCargada = null
        if (menuActual == MENUS.VISUALIZADOR_TEMA) idTemaActual = -1

        val dtMenu = ListaMenus[menu.ordinal]

        menuAnterior = menuActual
        menuActual = menu

        submenu = dtMenu.submenu
        barraNavegacion = dtMenu.barraNavegacion

    }

    fun visualizarActividad(act: Actividad){
        actividadCargada = act
        cambiarMenu(MENUS.ACTIVIDAD)
    }
}