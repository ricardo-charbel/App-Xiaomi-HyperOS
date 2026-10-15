package edu.equipo2.xiaomios.handlers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.equipo2.xiaomios.data.Actividad
import edu.equipo2.xiaomios.GlobalVariables.actividadCargada
import edu.equipo2.xiaomios.handlers.TopicHandler.idTemaActual

// Los diferentes tipos de Menus se encuentran enumerados en el siguiente ENUM
enum class MENUS{
    NINGUNO,            // No representa a ningún menú, utilizado como valor NULO
    PORTADA,        // Muestra a la portada del PIA
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

// DetalleMenu se encarga de listar unos cuantos detalles extra sobre los menus que se están accediendo
// Los detalles se pueden ver a partir de la línea 24
val ListaMenus = listOf<DetalleMenu>(
    DetalleMenu(MENUS.NINGUNO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.PORTADA, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.PORTAFOLIO, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.ACTIVIDAD, submenu = true, barraNavegacion = false),
    DetalleMenu(MENUS.INVESTIGACION, submenu = false, barraNavegacion = true),
    DetalleMenu(MENUS.VISUALIZADOR_TEMA, submenu = true, barraNavegacion = false)
)

object MenuHandler{
    // En que Menu está el usuario
    var menuActual by mutableStateOf(MENUS.PORTADA)
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

    // Cambia al usuario de menu
    fun cambiarMenu(menu: MENUS){
        if (menuActual == menu) return                              // Evitamos cambiar de un menu a si mismo
        if (menuActual == menuAnterior) return                      // Evitamos cambiar de un menu a si mismo si es que se clasifica como menuAnterior
        if (menuActual == MENUS.ACTIVIDAD) actividadCargada = null  // Evitamos cambiar al menu de actividad si no hay actividad por cargar
        if (menuActual == MENUS.VISUALIZADOR_TEMA) idTemaActual = -1// Evitamos cambiar al menu de temas si es que no hay tema por cargar

        val dtMenu = ListaMenus[menu.ordinal]   // En base a la ID del menu se obtiene su DetalleMenu

        menuAnterior = menuActual   // El menu actual se almacena como el menu anterior
        menuActual = menu           // El menu nuevo se almacena como el menu actual

        submenu = dtMenu.submenu    // Dependiendo del DetalleMenu si este Menu es un Submenu
        barraNavegacion = dtMenu.barraNavegacion    // Dependiendo del DetalleMenu si la barra de navegación se tiene que mostrar o no

    }

    // Carga una actividad dada por el argumento "Actividad" para mostrar en el menu de actividad
    fun visualizarActividad(act: Actividad){
        actividadCargada = act
        cambiarMenu(MENUS.ACTIVIDAD)
    }
}