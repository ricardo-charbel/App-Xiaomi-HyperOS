package edu.equipo2.xiaomios.handlers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import edu.equipo2.xiaomios.cargarImagen
import edu.equipo2.xiaomios.handlers.MenuHandler.cambiarMenu

// Los temas son guardados como información de clases de la aplicación
data class Tema(
    var Nombre: String = "Sin Nombre",
    var Etapa: Int = 1,             // Etapa representa el numero de la fundamental que categoriza a este tema
    var Seccion: Int = 1,           // Sección de la Investigación que cubre en base a la Etapa
    var Miniatura: Boolean = false  // Activa la miniatura para visualizar en el menu principal de Investigación
)

// Lista de temas de la investigación
val ListaTemas = listOf<Tema>(
    // Actividad Fundamental 1
    Tema("Introducción a la Arquitectura del Sistema Operativo", 1,1, true),
    Tema("Definición de Sistemas Operativo", 1, 2, true),
    Tema("Componentes del Sistema Operativo", 1, 3, true),
    Tema("Manejo de recursos y partes del Sistema Operativo", 1, 4, true),
    Tema("Terminología básica y partes de gestión", 1, 5, true),

    // Actividad Fundamental 2
    Tema("Multitarea y Concurrencia", 2, 6, true),
    Tema("Modelos de Programación Concurrente", 2, 7, true),
    Tema("Procesos concurrentes, estados y transiciones", 2, 9, true),
    Tema("Interacciones entre Procesos", 2, 10, true)
)

// Hay detalles importantes que solo se obtienen al cargar los archivos correspondientes del tema,
// de ahí la existencia del DetalleTema, cuenta con la información ya cargada para su uso posterior
data class DetalleTema(
    var Nombre: String,
    var Miniatura: Painter,
    var Contenido: String,
    var Imagenes: String
)

object TopicHandler {
    var idTemaActual: Int by mutableIntStateOf(0) // ID del tema cargado actualmente

    // Se encarga de cargar el menu de visualizador de temas con la ID de tema asignado con el argumento temaId
    fun visualizarTema(temaId: Int){
        if (temaId == -1 || temaId > ListaTemas.size - 1) return // Evitamos cargar IDs de temas inexistentes o cargar el tema que ya esta cargado
        idTemaActual = temaId
        cambiarMenu(MENUS.VISUALIZADOR_TEMA)
    }

    // Creación de detalle tema
    @Composable
    fun obtenerDetalleTema(tm: Tema): DetalleTema{
        // Ubicación de la imagen de miniatura
        val mini = cargarImagen("investigacion/af_${tm.Etapa}/${tm.Seccion}/miniatura.png", true)
        // Ubicación del archivo MD con el contenido del tema
        val contenido = "investigacion/af_${tm.Etapa}/${tm.Seccion}/contenido.md"
        // Ubicación de la carpeta de imagenes del tema
        val imgs = "investigacion/af_${tm.Etapa}/${tm.Seccion}/imagenes"

        // Una vez cargados, se retornan a donde sea que la función haya sido llamada como objeto DetalleTema
        return DetalleTema(
            Nombre = tm.Nombre,
            Miniatura = mini,
            Contenido = contenido,
            Imagenes = imgs
        )
    }
}