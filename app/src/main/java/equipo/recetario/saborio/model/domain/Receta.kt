package equipo.recetario.saborio.model.domain

data class Receta (
    val id: String,
    val nombre: String,
    val descripcionBreve: String,
    val fotografia: String,
    val ingredientes: List<Ingrediente>,
    val pasosPreparacion: List<String>,
    val etiquetas: List<String>,
    val enlaceWeb: String?,
    val esPublica: Boolean,
    val esSecreta: Boolean,
    val nombreAutor: String?,
    val calificacion: Double
)