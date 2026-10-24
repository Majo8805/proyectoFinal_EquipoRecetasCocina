package equipo.recetario.saborio.model.domain

data class Usuario (
    val id: String,
    val nombre: String,
    val correo: String,
    val fechaNacimiento: String,
    val genero: String,
    val fotoPerfil: String?,
    val descripcionUsuario: String = "Hola! Estoy usando Saborío!"
)