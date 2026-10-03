package edu.equipo2.xiaomios.handlers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.equipo2.xiaomios.handlers.MenuHandler.cambiarMenu

data class Tema(
    val Nombre: String,
    val Archivo: String,
    val Imagen: String = "fundamentales/img_af_2.png"
)

val ListaTemas = listOf<Tema>(
    Tema("Historia del Sistema Operativo", "investigacion/prueba.md"),
    Tema("Gestión de Memoria", "investigacion/prueba_2.md"),
    Tema("Gestión de Procesos", "investigacion/prueba_3.md")
)

object TopicHandler {
    var idTemaActual: Int by mutableIntStateOf(0)

    fun visualizarTema(temaId: Int){
        if (temaId == -1 || temaId > ListaTemas.size - 1) return
        idTemaActual = temaId
        cambiarMenu(MENUS.VISUALIZADOR_TEMA)
    }
}