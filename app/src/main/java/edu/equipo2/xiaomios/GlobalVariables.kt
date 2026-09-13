package edu.equipo2.xiaomios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.equipo2.xiaomios.menus.Actividad

object GlobalVariables {
    var menuActual by mutableStateOf(MENUS.INTEGRANTES) // Variable que almacena en que menu estamos actualmente
    var actividadCargada: Actividad? by mutableStateOf(null)
}