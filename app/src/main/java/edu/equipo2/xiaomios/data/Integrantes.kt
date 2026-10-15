package edu.equipo2.xiaomios.data

data class Integrante(
    val Nombre: String,         // Nombre del Integrante
    val ApPaterno: String,      // Ap. Paterno del Integrante
    val ApMaterno: String,      // Ap. Materno del Integrante
    val Matricula: String,      // Matrícula del Integrante
    val Carrera:String,         // Carrera/Programa Educativo del Integrante
    var Imagen: String,         // Foto del Integrante
    val Participacion:Float,    // Porcentaje de participación del integrante (Almacenado en un valor de 0.0f a 1.0f)
    val Lider: Boolean = false  // Si está activo, la etiqueta de integrante resaltará que el integrante es líder del equipo
)

val Integrantes = listOf<Integrante>(
    Integrante("Gerardo Alberto", "Bautista", "Hernández", "2062418", "ITS", "integrantes/int_gerardo.png", 1.0f),
    Integrante("Karla Sarahí", "Chávez", "Tamez", "2169086", "IAS", "integrantes/int_karla.png", 1.0f),
    Integrante("Oscar Ernesto", "Bustos", "Mercado", "2117393", "IAS", "integrantes/int_oscar.png", 1.0f),
    Integrante("Diego Armando", "Esparza", "Flores", "1908457", "ITS", "integrantes/int_diego.png", 1.0f),
    Integrante("Marco Giovanni", "García", "Alvares", "2153913", "IAS", "integrantes/int_marco.png", 1.0f),
    Integrante("Victor Alain", "Hernández", "Jaraleño", "1945128", "ITS", "integrantes/int_victor.png", 1.0f),
    Integrante("Ricardo Charbel", "Ibarra", "Miranda", "2117545", "IAS", "integrantes/int_ricardo.png", 1.0f),
    Integrante("Francisco", "Mora", "Ruiz", "2226912", "ITS", "integrantes/int_francisco.png", 1.0f),
    Integrante("Angélica", "Reyna", "García", "2144918", "ITS", "integrantes/int_angela.png", 1.0f),
    Integrante("Maximiliano", "Romero", "García", "2226867", "ITS", "integrantes/int_maximiliano.png", 1.0f),
    Integrante("Alberto", "Treviño", "Menchaca", "2154205", "IAS", "integrantes/int_alberto.png", 1.0f, true),
    Integrante("Camila", "Trujillo", "Quintanilla", "2162775", "ITS", "integrantes/int_camila.png", 1.0f)
)