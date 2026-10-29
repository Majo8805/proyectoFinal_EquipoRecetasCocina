package equipo.recetario.saborio.model.data

import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.domain.Usuario

val listaUsuarios = mutableListOf<Usuario>()

val usuarioMuestra = Usuario(id = "1",
    nombre = "Tryndamere Gonzales",
    correo = "tryndamere@gmail.com",
    fechaNacimiento = "20/11/2000",
    genero = "Hombre",
    "123321",
    fotoPerfil = R.drawable.trynda)

fun agregarUsuario(usuario: Usuario) {
    listaUsuarios.add(usuario)
}

fun existeCorreo(correo: String): Boolean {
    return listaUsuarios.any {
        it.correo.equals(correo, ignoreCase = true)
    }
}
