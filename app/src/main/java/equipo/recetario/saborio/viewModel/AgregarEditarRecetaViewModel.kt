package equipo.recetario.saborio.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import equipo.recetario.saborio.view.components.IngredienteItem

/**
 * ViewModel que gestiona el estado y realiza las validaciones para los componentes
 * de la pantalla PantallaAgregarEditarReceta.
 */
class AgregarEditarRecetaViewModel : ViewModel() {

    // --- ESTADOS DEL FORMULARIO ---
    var esEdicion by mutableStateOf(false)
        private set

    var nombreReceta by mutableStateOf("")
        private set

    var descripcionReceta by mutableStateOf("")
        private set

    val listaIngredientes = mutableStateListOf<IngredienteItem>()

    val listaPasos = mutableStateListOf<String>()

    val etiquetasSeleccionadas = mutableStateListOf<String>()

    var compartirReceta by mutableStateOf(true)
        private set

    var recetaSecreta by mutableStateOf(false)
        private set

    var enlaceFuenteUrl by mutableStateOf("")
        private set

    var fotoUri by mutableStateOf<String?>(null)
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

    var errorFoto by mutableStateOf<String?>(null)
        private set

    var errorGeneral by mutableStateOf<String?>(null)
        private set

    /** Indica si todo el formulario pasa todas las validaciones actuales. */
    val esFormularioValido: Boolean
        get() = errorNombre == null &&
                errorDescripcion == null &&
                errorIngredientes == null &&
                errorPasos == null &&
                errorEtiquetas == null &&
                errorEnlaceFuente == null &&
                nombreReceta.isNotBlank() &&
                descripcionReceta.isNotBlank() &&
                listaIngredientes.isNotEmpty() &&
                listaPasos.isNotEmpty() &&
                etiquetasSeleccionadas.isNotEmpty()

    // --- MÉTODOS DE ACTUALIZACIÓN DE CAMPOS ---

    fun inicializarModo(esEdicionModo: Boolean) {
        esEdicion = esEdicionModo
    }

    fun actualizarNombre(nuevoNombre: String) {
        nombreReceta = nuevoNombre
        validarNombre(nuevoNombre)
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        descripcionReceta = nuevaDescripcion
        validarDescripcion(nuevaDescripcion)
    }

    fun actualizarCompartirReceta(compartir: Boolean) {
        compartirReceta = compartir
    }

    fun actualizarRecetaSecreta(secreta: Boolean) {
        recetaSecreta = secreta
    }

    fun actualizarEnlaceFuente(url: String) {
        enlaceFuenteUrl = url
        validarEnlaceFuente(url)
    }

    fun actualizarFotoUri(uri: String?) {
        fotoUri = uri
        validarFoto(uri)
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
        if (etiquetaLimpia.length < 2) {
            errorEtiquetas = "La etiqueta debe tener al menos 2 caracteres"
            return false
        }
        if (!etiquetasSeleccionadas.contains(etiquetaLimpia)) {
            etiquetasSeleccionadas.add(etiquetaLimpia)
        }
        errorEtiquetas = null
        return true
    }

    // --- VALIDACIONES INDIVIDUALES DE CAMPOS ---

    /**
     * Valida el nombre de la receta.
     * @return true si es válido, false de lo contrario.
     */
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
            nombre.length > 100 -> {
                errorNombre = "El nombre no puede exceder los 100 caracteres"
                false
            }
            else -> {
                errorNombre = null
                true
            }
        }
    }

    /**
     * Valida la descripción de la receta.
     * @return true si es válida, false de lo contrario.
     */
    fun validarDescripcion(descripcion: String = descripcionReceta): Boolean {
        return when {
            descripcion.isBlank() -> {
                errorDescripcion = "La descripción de la receta es obligatoria"
                false
            }
            descripcion.trim().length < 10 -> {
                errorDescripcion = "La descripción debe tener al menos 10 caracteres"
                false
            }
            descripcion.length > 180 -> {
                errorDescripcion = "La descripción no puede exceder los 180 caracteres"
                false
            }
            else -> {
                errorDescripcion = null
                true
            }
        }
    }

    /**
     * Valida que la lista de ingredientes no esté vacía.
     */
    fun validarListaIngredientes(): Boolean {
        return if (listaIngredientes.isEmpty()) {
            errorIngredientes = "Debes agregar al menos un ingrediente"
            false
        } else {
            errorIngredientes = null
            true
        }
    }

    /**
     * Valida los datos de un ingrediente individual antes de agregarlo.
     */
    fun validarIngrediente(nombre: String, cantidad: String, unidad: String): String? {
        if (nombre.isBlank()) {
            return "El nombre del ingrediente no puede estar vacío"
        }
        if (cantidad.isBlank()) {
            return "La cantidad no puede estar vacía"
        }
        val cantidadNum = cantidad.toDoubleOrNull()
        if (cantidadNum == null || cantidadNum <= 0) {
            return "La cantidad debe ser un número mayor a 0"
        }
        if (unidad.isBlank()) {
            return "Debes seleccionar una unidad de medida"
        }
        return null
    }

    /**
     * Valida que la lista de pasos no esté vacía.
     */
    fun validarListaPasos(): Boolean {
        return if (listaPasos.isEmpty()) {
            errorPasos = "Debes agregar al menos un paso de preparación"
            false
        } else {
            errorPasos = null
            true
        }
    }

    /**
     * Valida el texto de un paso individual antes de agregarlo.
     */
    fun validarPasoTexto(paso: String): String? {
        if (paso.isBlank()) {
            return "El paso de preparación no puede estar vacío"
        }
        if (paso.trim().length < 5) {
            return "El paso debe describir el procedimiento (mínimo 5 caracteres)"
        }
        return null
    }

    /**
     * Valida que al menos una etiqueta esté seleccionada.
     */
    fun validarEtiquetas(): Boolean {
        return if (etiquetasSeleccionadas.isEmpty()) {
            errorEtiquetas = "Debes seleccionar al menos una etiqueta o categoría"
            false
        } else {
            errorEtiquetas = null
            true
        }
    }

    /**
     * Valida el enlace de la fuente si fue introducido (campo opcional).
     */
    fun validarEnlaceFuente(url: String = enlaceFuenteUrl): Boolean {
        if (url.isBlank()) {
            errorEnlaceFuente = null
            return true
        }
        val urlRegex = Regex("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$")
        return if (!urlRegex.matches(url.trim())) {
            errorEnlaceFuente = "El enlace introducido no es una URL válida (ej. https://ejemplo.com)"
            false
        } else {
            errorEnlaceFuente = null
            true
        }
    }

    /**
     * Valida la foto del platillo.
     */
    fun validarFoto(uri: String? = fotoUri): Boolean {
        errorFoto = null
        return true
    }

    /**
     * Ejecuta todas las validaciones del formulario de una sola vez.
     * @return true si todas las validaciones son exitosas, false en caso contrario.
     */
    fun validarFormularioCompleto(): Boolean {
        val vNombre = validarNombre()
        val vDescripcion = validarDescripcion()
        val vIngredientes = validarListaIngredientes()
        val vPasos = validarListaPasos()
        val vEtiquetas = validarEtiquetas()
        val vEnlace = validarEnlaceFuente()
        val vFoto = validarFoto()

        val esValido = vNombre && vDescripcion && vIngredientes && vPasos && vEtiquetas && vEnlace && vFoto

        errorGeneral = if (!esValido) {
            "Por favor, corrige los errores resaltados antes de guardar la receta"
        } else {
            null
        }

        return esValido
    }
}
