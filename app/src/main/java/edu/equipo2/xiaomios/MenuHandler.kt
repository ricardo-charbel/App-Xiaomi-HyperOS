package edu.equipo2.xiaomios

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.equipo2.xiaomios.GlobalVariables.actividadCargada

// Los diferentes tipos de Menus se encuentran enumerados en el siguiente ENUM
enum class MENUS{
    NINGUNO,        // No representa a ningún menú, utilizado como valor NULO
    INTEGRANTES,    // Muestra a los integrantes del equipo y sus datos personales.
    PORTAFOLIO,     // Muestra el portafolio de actividades fundamentales.
    INVESTIGACION,  // Muestra la investigación del PIA sobre el Sistema Operativo elegido.
    ACTIVIDAD       // SUBMENU. Muestra el PDF de la actividadCargada
    // IMPORTANTE: Este menu solo se muestra cuando actividadCargada no tenga valores nulos
}

data class DetalleMenu(
    val menu: MENUS,
    val submenu: Boolean = false,
    val barraNavegacion: Boolean = true
)

val ListaMenus = listOf<DetalleMenu>(
    DetalleMenu(MENUS.NINGUNO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.INTEGRANTES, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.PORTAFOLIO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.INVESTIGACION, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.ACTIVIDAD, submenu = true, barraNavegacion = false)
)

object MenuHandler{
    // En que Menu está el usuario
    var menuActual by mutableStateOf(MENUS.INTEGRANTES)
    // En que Menu ESTUVO el usuario
    var menuAnterior by mutableStateOf(MENUS.NINGUNO)
    // Si esta variable está activa, al darle al botón de regresar, se regresará al menu anterior
    var submenu by mutableStateOf(false);
    // Si esta variable está activa, se mostrará la barra de navegación
    var barraNavegacion by mutableStateOf(true);

    fun cambiarMenuAnterior(){
        if (!submenu || menuAnterior == null) return
        cambiarMenu(menuAnterior);
    }

    fun cambiarMenu(menu: MENUS){
        if (menuActual == menuAnterior) return
        if (menuActual == MENUS.ACTIVIDAD) actividadCargada = null;

        var dtMenu = ListaMenus[menu.ordinal]

        menuAnterior = menuActual;
        menuActual = menu;

        submenu = dtMenu.submenu;
        barraNavegacion = dtMenu.barraNavegacion

    }

    fun visualizarActividad(act: Actividad){
        actividadCargada = act;
        cambiarMenu(MENUS.ACTIVIDAD)
    }
}