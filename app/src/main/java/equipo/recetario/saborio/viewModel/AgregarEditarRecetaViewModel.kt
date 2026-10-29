package equipo.recetario.saborio.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.view.components.IngredienteItem

/**
 * ViewModel que gestiona el estado y realiza las validaciones para la pantalla
 * PantallaAgregarEditarReceta.
 */
class AgregarEditarRecetaViewModel : ViewModel() {

    // --- ESTADOS DEL FORMULARIO ---
    var esEdicion by mutableStateOf(false)
        private set

    var recetaIdEdicion by mutableStateOf<String?>(null)
        private set

    var nombreReceta by mutableStateOf("")
        private set

    var descripcionReceta by mutableStateOf("")
        private set

    val listaIngredientes = mutableStateListOf<IngredienteItem>()

    val listaPasos = mutableStateListOf<String>()

    val etiquetasSeleccionadas = mutableStateListOf<String>()

    // Mutuamente exclusivos: sólo uno puede ser true a la vez
    var compartirReceta by mutableStateOf(true)
        private set

    var recetaSecreta by mutableStateOf(false)
        private set

    var enlaceFuenteUrl by mutableStateOf("")
        private set

    var fotoDrawableRes by mutableStateOf<Int?>(null)
        private set

    // --- MENSAJES DE ERROR DE VALIDACIÓN ---
    var errorNombre by mutableStateOf<String?>(null)
        private set

    var errorDescripcion by mutableStateOf<String?>(null)
        private set

    var errorIngredientes by mutableStateOf<String?>(null)
        private set

    var errorPasos by mutableStateOf<String?>(null)
        private set

    var errorEtiquetas by mutableStateOf<String?>(null)
        private set

    var errorEnlaceFuente by mutableStateOf<String?>(null)
        private set

    var errorGeneral by mutableStateOf<String?>(null)
        private set

    /** Carga la información de la receta si se entra en modo edición. */
    fun cargarRecetaParaEditar(id: String?, modoEdicion: Boolean = true) {
        recetaIdEdicion = id
        esEdicion = modoEdicion

        if (id != null) {
            val receta = listaRecetas.find { it.id == id }
            if (receta != null) {
                nombreReceta = receta.nombre
                descripcionReceta = receta.descripcionBreve
                listaIngredientes.clear()
                listaIngredientes.addAll(receta.ingredientes.map {
                    IngredienteItem(
                        nombre = it.nombre,
                        cantidad = if (it.cantidad % 1.0 == 0.0) it.cantidad.toInt().toString() else it.cantidad.toString(),
                        unidad = it.unidadMedida
                    )
                })
                listaPasos.clear()
                listaPasos.addAll(receta.pasosPreparacion)
                etiquetasSeleccionadas.clear()
                etiquetasSeleccionadas.addAll(receta.etiquetas)
                compartirReceta = receta.esPublica
                recetaSecreta = receta.esSecreta
                enlaceFuenteUrl = receta.enlaceWeb ?: ""
                fotoDrawableRes = receta.fotografia
            }
        }
    }

    fun actualizarNombre(nuevoNombre: String) {
        nombreReceta = nuevoNombre
        validarNombre(nuevoNombre)
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        descripcionReceta = nuevaDescripcion
        validarDescripcion(nuevaDescripcion)
    }

    /**
     * Alterna la opción de compartir (pública).
     * Garantiza exclusividad mutua con 'recetaSecreta' (privada).
     */
    fun actualizarCompartirReceta(compartir: Boolean) {
        if (compartir) {
            compartirReceta = true
            recetaSecreta = false
        } else {
            compartirReceta = false
            recetaSecreta = true
        }
    }

    /**
     * Alterna la opción de receta secreta (privada).
     * Garantiza exclusividad mutua con 'compartirReceta' (pública).
     */
    fun actualizarRecetaSecreta(secreta: Boolean) {
        if (secreta) {
            recetaSecreta = true
            compartirReceta = false
        } else {
            recetaSecreta = false
            compartirReceta = true
        }
    }

    fun actualizarEnlaceFuente(url: String) {
        enlaceFuenteUrl = url
        validarEnlaceFuente(url)
    }

    // --- GESTIÓN DE INGREDIENTES ---

    fun agregarIngrediente(ingrediente: IngredienteItem): Boolean {
        val errorIng = validarIngrediente(ingrediente.nombre, ingrediente.cantidad, ingrediente.unidad)
        if (errorIng != null) {
            errorIngredientes = errorIng
            return false
        }
        listaIngredientes.add(ingrediente)
        errorIngredientes = null
        return true
    }

    fun eliminarIngrediente(ingrediente: IngredienteItem) {
        listaIngredientes.remove(ingrediente)
        validarListaIngredientes()
    }

    // --- GESTIÓN DE PASOS ---

    fun agregarPaso(paso: String): Boolean {
        val errorP = validarPasoTexto(paso)
        if (errorP != null) {
            errorPasos = errorP
            return false
        }
        listaPasos.add(paso.trim())
        errorPasos = null
        return true
    }

    fun eliminarPaso(index: Int) {
        if (index in listaPasos.indices) {
            listaPasos.removeAt(index)
        }
        validarListaPasos()
    }

    // --- GESTIÓN DE ETIQUETAS ---

    fun alternarEtiqueta(etiqueta: String) {
        if (etiquetasSeleccionadas.contains(etiqueta)) {
            etiquetasSeleccionadas.remove(etiqueta)
        } else {
            etiquetasSeleccionadas.add(etiqueta)
        }
        validarEtiquetas()
    }

    fun agregarEtiquetaPersonalizada(etiqueta: String): Boolean {
        val etiquetaLimpia = etiqueta.trim()
        if (etiquetaLimpia.isBlank()) {
            errorEtiquetas = "La etiqueta no puede estar vacía"
            return false
        }
        if (!etiquetasSeleccionadas.contains(etiquetaLimpia)) {
            etiquetasSeleccionadas.add(etiquetaLimpia)
        }
        errorEtiquetas = null
        return true
    }

    // --- VALIDACIONES INDIVIDUALES ---

    fun validarNombre(nombre: String = nombreReceta): Boolean {
        return when {
            nombre.isBlank() -> {
                errorNombre = "El nombre de la receta es obligatorio"
                false
            }
            nombre.trim().length < 3 -> {
                errorNombre = "El nombre debe tener al menos 3 caracteres"
                false
            }
            else -> {
                errorNombre = null
                true
            }
        }
    }

    fun validarDescripcion(descripcion: String = descripcionReceta): Boolean {
        return when {
            descripcion.isBlank() -> {
                errorDescripcion = "La descripción es obligatoria"
                false
            }
            else -> {
                errorDescripcion = null
                true
            }
        }
    }

    fun validarListaIngredientes(): Boolean {
        return if (listaIngredientes.isEmpty()) {
            errorIngredientes = "Debes agregar al menos un ingrediente"
            false
        } else {
            errorIngredientes = null
            true
        }
    }

    fun validarIngrediente(nombre: String, cantidad: String, unidad: String): String? {
        if (nombre.isBlank()) return "El nombre del ingrediente no puede estar vacío"
        if (cantidad.isBlank()) return "La cantidad no puede estar vacía"
        return null
    }

    fun validarListaPasos(): Boolean {
        return if (listaPasos.isEmpty()) {
            errorPasos = "Debes agregar al menos un paso de preparación"
            false
        } else {
            errorPasos = null
            true
        }
    }

    fun validarPasoTexto(paso: String): String? {
        if (paso.isBlank()) return "El paso no puede estar vacío"
        return null
    }

    fun validarEtiquetas(): Boolean {
        return if (etiquetasSeleccionadas.isEmpty()) {
            errorEtiquetas = "Debes seleccionar al menos una etiqueta"
            false
        } else {
            errorEtiquetas = null
            true
        }
    }

    fun validarEnlaceFuente(url: String = enlaceFuenteUrl): Boolean {
        if (url.isBlank()) {
            errorEnlaceFuente = null
            return true
        }
        val urlRegex = Regex("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$")
        return if (!urlRegex.matches(url.trim())) {
            errorEnlaceFuente = "El enlace no es una URL válida"
            false
        } else {
            errorEnlaceFuente = null
            true
        }
    }

    fun validarFormularioCompleto(): Boolean {
        val vNombre = validarNombre()
        val vDescripcion = validarDescripcion()
        val vIngredientes = validarListaIngredientes()
        val vPasos = validarListaPasos()
        val vEtiquetas = validarEtiquetas()
        val vEnlace = validarEnlaceFuente()

        val esValido = vNombre && vDescripcion && vIngredientes && vPasos && vEtiquetas && vEnlace
        errorGeneral = if (!esValido) "Revisa los campos para continuar" else null
        return esValido
    }
}
