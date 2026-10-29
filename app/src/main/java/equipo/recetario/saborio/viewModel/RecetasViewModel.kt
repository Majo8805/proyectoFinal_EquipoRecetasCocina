package equipo.recetario.saborio.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import equipo.recetario.saborio.model.data.buscarPorNombre
import equipo.recetario.saborio.model.data.filtrarPorEtiqueta
import equipo.recetario.saborio.model.data.getMisRecetas
import equipo.recetario.saborio.model.data.getRecetasExplorar
import equipo.recetario.saborio.model.data.getRecetasGuardadas
import equipo.recetario.saborio.model.data.guardadoGlobalDeRecetas
import equipo.recetario.saborio.model.domain.Receta
import java.text.Normalizer
import java.text.Normalizer.normalize

class RecetasViewModel : ViewModel() {
/**
 * Mantiene el estado de las recetas que se muestran y gestiona su búsqueda,
 * ordenamiento, filtrado por categoría y guardado.
 */
class RecetasViewModel: ViewModel() {
    /** Recetas visibles después de aplicar los filtros y el ordenamiento actuales. */
    var recetasAMostrar by mutableStateOf<List<Receta>>(emptyList())
        private set

    /** Indica si hay recetas visibles. */
    val hayRecetas: Boolean
        get() = recetasAMostrar.isNotEmpty()

    /** Texto escrito en el campo de búsqueda. */
    var textoBusqueda by mutableStateOf("")
        private set

    private var listaBasePorPestaña: List<Receta> = emptyList()

    /** Nombre de la pestaña activa: Inicio, Guardadas o Explorar. */
    var pestanaActual by mutableStateOf("Inicio")
        private set

    private var ordenAscendente = false

    /** Categoría seleccionada para filtrar las recetas. */
    var categoriaSeleccionada by mutableStateOf("Todas")
        private set

    /** Título que corresponde a la pestaña activa. */
    val titulo: String
        get() = when (pestanaActual) {
            "Inicio" -> "Mis Recetas"
            "Guardadas" -> "Guardadas"
            else -> "Explorar"
        }

    /** Indica si la pestaña actual debe mostrar la opción de guardar recetas. */
    val mostrarBotonGuardar: Boolean
        get() = pestanaActual == "Explorar" || pestanaActual == "Guardadas"

    /**
     * Carga las recetas de la pestaña indicada y limpia el texto de búsqueda.
     *
     * @param pestana Pestaña desde la que se obtienen las recetas.
     * @param nombreUsuario Usuario cuyas recetas se consultan en Inicio o Explorar.
     */
    fun cargarRecetas(pestana: String, nombreUsuario: String) {
        pestanaActual = pestana
        textoBusqueda = ""
        listaBasePorPestaña = when (pestana) {
            "Inicio" -> getMisRecetas(miNombre = nombreUsuario)
            "Explorar" -> getRecetasExplorar(miNombre = nombreUsuario)
            "Guardadas" -> getRecetasGuardadas()
            else -> emptyList()
        }
        recetasAMostrar = listaBasePorPestaña
    }

    private fun String.limpiarTexto(): String =
        normalize(this, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .lowercase()
            .trim()

    /**
     * Actualiza la búsqueda y filtra las recetas por nombre, ignorando mayúsculas,
     * espacios en los extremos y diferencias entre letras acentuadas y no acentuadas.
     *
     * @param nuevoTexto Texto introducido en la búsqueda.
     */
    fun actualizarBusqueda(nuevoTexto: String) {
        textoBusqueda = nuevoTexto
        recetasAMostrar = if (nuevoTexto.isBlank()) {
            listaBasePorPestaña
        } else {
            val busquedaLimpia = nuevoTexto.limpiarTexto()
            listaBasePorPestaña.filter { receta ->
                val nombreLimpio = receta.nombre.limpiarTexto()
                nombreLimpio.contains(busquedaLimpia)
            }
        }
    }

    /** Alterna el orden de las recetas visibles por calificación de manera ascendente y descendente. */
    fun ordenarPorEstrellas() {
        ordenAscendente = !ordenAscendente

        recetasAMostrar = if (ordenAscendente) {
            recetasAMostrar.sortedBy { it.calificacion }
        } else {
            recetasAMostrar.sortedByDescending { it.calificacion }
        }
    }

    /**
     * Selecciona una categoría y actualiza la lista, conservando la búsqueda activa.
     *
     * @param categoria Etiqueta por la que se filtrarán las recetas.
     * @param nombreUsuario Usuario necesario para volver a obtener la lista de la pestaña.
     */
    fun seleccionarCategoria(categoria: String, nombreUsuario: String) {
        categoriaSeleccionada = categoria

        val listaBase = when (pestanaActual) {
            "Inicio" -> getMisRecetas(miNombre = nombreUsuario)
            "Explorar" -> getRecetasExplorar(miNombre = nombreUsuario)
            "Guardadas" -> getRecetasGuardadas()
            else -> emptyList()
        }
        val listaFiltradaPorCategoria = filtrarPorEtiqueta(listaBase, categoria)

        listaBasePorPestaña = listaFiltradaPorCategoria
        recetasAMostrar = if (textoBusqueda.isBlank()) {
            listaFiltradaPorCategoria
        } else {
            buscarPorNombre(listaFiltradaPorCategoria, textoBusqueda)
        }
    }

    /**
     * Cambia el estado de guardado de una receta y actualiza las listas visibles.
     *
     * @param recetaSeleccionada Receta cuyo estado de guardado se va a alternar.
     */
    fun cambiarEstadoGuardado(recetaSeleccionada: Receta) {
        guardadoGlobalDeRecetas(recetaSeleccionada.nombre)

        listaBasePorPestaña = listaBasePorPestaña.map { receta ->
            if (receta.nombre == recetaSeleccionada.nombre) {
                receta.copy(esGuardada = !receta.esGuardada)
            } else {
                receta
            }
        }

        if (pestanaActual == "Guardadas") {
            listaBasePorPestaña = listaBasePorPestaña.filter { it.esGuardada }
        }

        recetasAMostrar = if (textoBusqueda.isBlank()) {
            listaBasePorPestaña
        } else {
            buscarPorNombre(listaActual = listaBasePorPestaña, query = textoBusqueda)
        }
    }

}