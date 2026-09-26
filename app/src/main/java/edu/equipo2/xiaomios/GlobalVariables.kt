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
import androidx.core.graphics.BitmapCompat

// Las Actividades son tratadas como objetos para un mejor despliegue en la aplicación
data class Actividad(
    val Numero: Int,            // Número de la Actividad (1,2,3 o 4)
    val Nombre: String,         // El nombre de la actividad (E.j. Arquitectura de los Sistemas Operativos)
    val Imagen: String,            // Imagen representativa de lo abarcado en la actividad
    val NombreArchivo:String    // Archivo PDF de la Actividad
)

// Cada actividad elaborada pasa a ser un objeto con sus datos respectivos
val Actividades = listOf<Actividad>(
    Actividad(1, "Arquitectura, Desempeño y tipos de un Sistema Operativo", "fundamentales/img_af_1.png", "fundamentales/af_1.pdf"),
    Actividad(2, "Multitarea y Control de Concurrencia", "fundamentales/img_af_2.png", "fundamentales/af_2.pdf"),
    Actividad(3, "Almacenaje: Memoria y Archivo", "fundamentales/img_af_3.png", "fundamentales/af_3.pdf")
)

data class Integrante(
    val Nombre: String,
    val ApPaterno: String,
    val ApMaterno: String,
    val Matricula: String,
    val Carrera:String,
    var Imagen: String,
    val Lider: Boolean = false
)

val Integrantes = listOf<Integrante>(
    Integrante("Gerardo Alberto", "Bautista", "Hernández", "2062418", "ITS", "integrantes/int_gerardo.png"),
    Integrante("Karla Sarahí", "Chávez", "Tamez", "2169086", "IAS", "integrantes/int_karla.png"),
    Integrante("Oscar Ernesto", "Bustos", "Mercado", "2117393", "IAS", "integrantes/int_oscar.png"),
   Integrante("Diego Armando", "Esparza", "Flores", "1908457", "ITS", "integrantes/int_diego.png"),
    Integrante("Marco Giovanni", "García", "Alvares", "2153913", "IAS", "integrantes/int_marco.png"),
    Integrante("Victor Alain", "Hernández", "Jaraleño", "1945128", "ITS", "integrantes/int_victor.png"),
    Integrante("Ricardo Charbel", "Ibarra", "Miranda", "2117545", "IAS", "integrantes/int_ricardo.png"),
    Integrante("Francisco", "Mora", "Ruiz", "2226912", "ITS", "integrantes/int_francisco.png"),
    Integrante("Angélica", "Reyna", "García", "2144918", "ITS", "integrantes/int_angela.png"),
    Integrante("Maximiliano", "Romero", "García", "2226867", "ITS", "integrantes/int_maximiliano.png"),
   Integrante("Alberto", "Treviño", "Menchaca", "2154205", "IAS", "integrantes/int_alberto.png", true),
    Integrante("Camila", "Trujillo", "Quintanilla", "2162775", "ITS", "integrantes/int_camila.png")
)


@Composable
fun cargarImagen(ubicacion:String): Painter {
    val ctx = LocalContext.current;
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