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
    var recetasAMostrar by mutableStateOf<List<Receta>>(emptyList())
        private set

    val hayRecetas: Boolean
        get() = recetasAMostrar.isNotEmpty()

    var textoBusqueda by mutableStateOf("")
        private set

    private var listaBasePorPestaña: List<Receta> = emptyList()

    var pestanaActual by mutableStateOf("Inicio")
        private set

    private var ordenAscendente = false

    var categoriaSeleccionada by mutableStateOf("Todas")
        private set

    val titulo: String
        get() = when (pestanaActual) {
            "Inicio" -> "Mis Recetas"
            "Guardadas" -> "Guardadas"
            else -> "Explorar"
        }

    val mostrarBotonGuardar: Boolean
        get() = pestanaActual == "Explorar" || pestanaActual == "Guardadas"

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

    private fun String.limpiarTexto(): String {
        val normalizado = normalize(this, Normalizer.Form.NFD)
        return "\\p{InCombiningDiacriticalMarks}+".toRegex().replace(normalizado, "").lowercase()
            .trim()
    }

    fun onSearchQueryChanged(nuevoTexto: String) {
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

    fun ordenarPorEstrellas() {
        ordenAscendente = !ordenAscendente

        recetasAMostrar = if (ordenAscendente) {
            recetasAMostrar.sortedBy { it.calificacion }
        } else {
            recetasAMostrar.sortedByDescending { it.calificacion }
        }
    }

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
     * Este metodo se usa para el botón de guardar, esta llega cambiar su guardado y se vuelve
     * a desguardar al volver a usarlo
     */
    fun alternarGuardado(recetaSeleccionada: Receta) {
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