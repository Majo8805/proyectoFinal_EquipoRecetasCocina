package equipo.recetario.saborio.navigation

import kotlinx.serialization.Serializable

@Serializable
object VerEditarPerfil

@Serializable
object VerPerfil

@Serializable
object VerRecetasSecretas

@Serializable
object VerMisRecetas

@Serializable
object VerExplorar

@Serializable
object VerGuardadas

@Serializable
data class VerAgregarEditarReceta(
    val recetaId: String? = null,
    val esEdicion: Boolean = false
)

@Serializable
data class VerDetalleReceta(
    val recetaId: String,
    val esPropia: Boolean
)

@Serializable
data object VerRegistro
