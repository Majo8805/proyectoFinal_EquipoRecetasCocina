package equipo.recetario.saborio.model.domain
import equipo.recetario.saborio.model.domain.Ingrediente

data class Receta (
    val id: String,
    val nombre: String,
    val descripcionBreve: String,
    val fotografia: Int,
    val ingredientes: List<Ingrediente>,
    val pasosPreparacion: List<String>,
    val etiquetas: List<String>,
    val enlaceWeb: String?,
    val esPublica: Boolean,
    val esSecreta: Boolean,
    val nombreAutor: String?,
    val calificacion: Double,
    val esGuardada: Boolean
)