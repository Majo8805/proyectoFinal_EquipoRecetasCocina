package equipo.recetario.saborio.model.domain

data class EstadoFormularioReceta(
    val id: String? = null,
    val titulo: String = "",
    val descripcion: String = "",
    val tiempoPreparacionMinutos: String = "",
    val porciones: String = "",
    val categoria: String = "",
    val ingredientes: List<String> = listOf(""),
    val pasos: List<String> = listOf(""),
    val rutaImagen: String? = null,
    val esModoEdicion: Boolean = false
)