package edu.equipo2.xiaomios.data

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