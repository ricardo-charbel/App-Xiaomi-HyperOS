package edu.equipo2.xiaomios.data

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