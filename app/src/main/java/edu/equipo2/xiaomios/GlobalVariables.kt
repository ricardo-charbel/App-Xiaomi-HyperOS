package edu.equipo2.xiaomios

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import edu.equipo2.xiaomios.data.Actividad

// Carga imagenes respectivo a su ubicación dentro de la carpeta de Imagenes
// Está funciona se utiliza con la finalidad de que se puedan cargar imagenes de diferentes carpetas y no tenerlas todas en una sola
@Composable
fun cargarImagen(ubicacion:String): Painter {
    val ctx = LocalContext.current
    val preUbicacion = "imagenes/$ubicacion"

    return remember(preUbicacion){
        ctx.assets.open(preUbicacion).use{ iS ->
            val bitmap = BitmapFactory.decodeStream(iS)
            BitmapPainter(bitmap.asImageBitmap())

        }
    }
}

object GlobalVariables {
    var actividadCargada: Actividad? by mutableStateOf(null)


}