package equipo.recetario.saborio.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.data.agregarUsuario
import equipo.recetario.saborio.model.data.existeCorreo
import equipo.recetario.saborio.model.domain.Usuario
import java.util.UUID

class UsuarioViewModel : ViewModel() {

    var nombre by mutableStateOf("")
        private set

    var correo by mutableStateOf("")
        private set

    var fechaNacimiento by mutableStateOf("")
        private set

    var genero by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmarPassword by mutableStateOf("")
        private set

    fun actualizarNombre(valor: String) {
        nombre = valor
    }

    fun actualizarCorreo(valor: String) {
        correo = valor
    }

    fun actualizarFecha(valor: String) {
        fechaNacimiento = valor
    }

    fun actualizarGenero(valor: String) {
        genero = valor
    }

    fun actualizarPassword(valor: String) {
        password = valor
    }

    fun actualizarConfirmPassword(valor: String) {
        confirmarPassword = valor
    }

    fun correoValido(): Boolean {
        return correo.contains("@")
                && correo.endsWith(".com")
    }

    fun passwordsCoinciden(): Boolean {
        return password == confirmarPassword
    }

    fun formularioValido(): Boolean {

        return nombre.isNotBlank()
                && correoValido()
                && fechaNacimiento.isNotBlank()
                && genero.isNotBlank()
                && password.length >= 8
                && passwordsCoinciden()
                && !existeCorreo(correo)
    }

    fun registrarUsuario(): Usuario? {

        if (!formularioValido()) {
            return null
        }

        val nuevoUsuario = Usuario(
            id = UUID.randomUUID().toString(),
            nombre = nombre,
            correo = correo,
            fechaNacimiento = fechaNacimiento,
            genero = genero,
            password = password,
            fotoPerfil = R.drawable.avatar_default
        )

        agregarUsuario(nuevoUsuario)

        return nuevoUsuario
    }
}