package edu.equipo2.xiaomios.handlers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import edu.equipo2.xiaomios.cargarImagen
import edu.equipo2.xiaomios.data.ListaTemas
import edu.equipo2.xiaomios.data.Tema
import edu.equipo2.xiaomios.handlers.MenuHandler.cambiarMenu


// Hay detalles importantes que solo se obtienen al cargar los archivos correspondientes del tema,
// de ahí la existencia del DetalleTema, cuenta con la información ya cargada para su uso posterior
data class DetalleTema(
    var Nombre: String,
    var Miniatura: Painter,
    var Contenido: String,
    var Imagenes: String
)

object TopicHandler {
    var idTemaActual: Int by mutableIntStateOf(0) // id del tema cargado actualmente

    // Se encarga de cargar el menu de visualizador de temas con la ID de tema asignado con el argumento temaId
    fun visualizarTema(temaId: Int){
        if (temaId == -1 || temaId > ListaTemas.size - 1) return // Evitamos cargar ids de temas inexistentes o cargar el tema que ya está cargado
        idTemaActual = temaId
        cambiarMenu(MENUS.VISUALIZADOR_TEMA)
    }

    // Creación de detalle tema
    @Composable
    fun obtenerDetalleTema(tm: Tema): DetalleTema{
        // Ubicación de la imagen de miniatura
        val mini = cargarImagen("investigacion/af_${tm.Etapa}/${tm.Seccion}/miniatura.png", true)
        // Ubicación del archivo markdown con el contenido del tema
        val contenido = "investigacion/af_${tm.Etapa}/${tm.Seccion}/contenido.md"
        // Ubicación de la carpeta de imágenes del tema
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