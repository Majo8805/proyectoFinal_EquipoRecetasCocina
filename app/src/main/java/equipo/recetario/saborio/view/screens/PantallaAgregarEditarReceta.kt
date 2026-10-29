package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.*
import equipo.recetario.saborio.view.components.AccionesGuardado
import equipo.recetario.saborio.view.components.BarraSuperiorAnadirReceta
import equipo.recetario.saborio.view.components.ConfiguracionPrivacidad
import equipo.recetario.saborio.view.components.EnlaceFuente
import equipo.recetario.saborio.view.components.EtiquetasReceta
import equipo.recetario.saborio.view.components.FotoPlatillo
import equipo.recetario.saborio.view.components.InformacionBasicaReceta
import equipo.recetario.saborio.view.components.ListaIngredientes
import equipo.recetario.saborio.view.components.PasosPreparacion
import equipo.recetario.saborio.viewModel.AgregarEditarRecetaViewModel

@Composable
fun PantallaAgregarEditarReceta(
    usuario: Usuario = usuarioMuestra,
    recetaId: String? = null,
    esEdicion: Boolean = false,
    onVolverClick: () -> Unit = {},
    onGuardarExitoso: () -> Unit = {},
    onNavigateToPerfil: () -> Unit = {},
    viewModel: AgregarEditarRecetaViewModel = viewModel()
) {
    LaunchedEffect(key1 = recetaId, key2 = esEdicion) {
        viewModel.cargarRecetaParaEditar(recetaId, esEdicion)
    }


    val nombreReceta = viewModel.nombreReceta
    val descripcionReceta = viewModel.descripcionReceta
    val listaIngredientesState = viewModel.listaIngredientes
    val listaPasosState = viewModel.listaPasos
    val etiquetasSeleccionadasState = viewModel.etiquetasSeleccionadas
    val compartirReceta = viewModel.compartirReceta
    val recetaSecreta = viewModel.recetaSecreta
    val enlaceFuenteUrl = viewModel.enlaceFuenteUrl
    val fotoDrawableRes = viewModel.fotoDrawableRes

    Scaffold(
        topBar = {
            BarraSuperiorAnadirReceta(
                titulo = if (esEdicion) "Editar Receta" else "Agregar Receta",
                usuario = usuario,
                onVolverClick = onVolverClick,
                onNavigateToPerfil = onNavigateToPerfil
            )
        },
        containerColor = BackgrounLightBrown
    ) { innerPadding ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            val isTabletOrWide = maxWidth >= 600.dp
            val paddingHorizontal = if (isTabletOrWide) 24.dp else 16.dp
            val spacingBetween = if (isTabletOrWide) 20.dp else 16.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = paddingHorizontal, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(spacingBetween),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                viewModel.errorGeneral?.let { errorMsg ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMsg,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                if (isTabletOrWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(spacingBetween)
                        ) {
                            InformacionBasicaReceta(
                                nombre = nombreReceta,
                                onNombreChange = { viewModel.actualizarNombre(it) },
                                descripcion = descripcionReceta,
                                onDescripcionChange = { viewModel.actualizarDescripcion(it) }
                            )
                            viewModel.errorNombre?.let { MensajeErrorCampo(it) }
                            viewModel.errorDescripcion?.let { MensajeErrorCampo(it) }

                            FotoPlatillo(
                                fotoDrawableRes = fotoDrawableRes,
                                onSubirFotoClick = {  }
                            )

                            EtiquetasReceta(
                                etiquetasSeleccionadas = etiquetasSeleccionadasState,
                                onToggleEtiqueta = { viewModel.alternarEtiqueta(it) },
                                onAddCustomEtiqueta = { viewModel.agregarEtiquetaPersonalizada(it) }
                            )
                            viewModel.errorEtiquetas?.let { MensajeErrorCampo(it) }
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(spacingBetween)
                        ) {
                            ListaIngredientes(
                                ingredientes = listaIngredientesState,
                                onAddIngrediente = { viewModel.agregarIngrediente(it) },
                                onDeleteIngrediente = { viewModel.eliminarIngrediente(it) }
                            )
                            viewModel.errorIngredientes?.let { MensajeErrorCampo(it) }

                            PasosPreparacion(
                                pasos = listaPasosState,
                                onAddPaso = { viewModel.agregarPaso(it) },
                                onDeletePaso = { viewModel.eliminarPaso(it) }
                            )
                            viewModel.errorPasos?.let { MensajeErrorCampo(it) }

                            ConfiguracionPrivacidad(
                                compartirReceta = compartirReceta,
                                onCompartirChange = { viewModel.actualizarCompartirReceta(it) },
                                recetaSecreta = recetaSecreta,
                                onSecretaChange = { viewModel.actualizarRecetaSecreta(it) }
                            )

                            EnlaceFuente(
                                url = enlaceFuenteUrl,
                                onUrlChange = { viewModel.actualizarEnlaceFuente(it) }
                            )
                            viewModel.errorEnlaceFuente?.let { MensajeErrorCampo(it) }
                        }
                    }
                } else {
                    InformacionBasicaReceta(
                        nombre = nombreReceta,
                        onNombreChange = { viewModel.actualizarNombre(it) },
                        descripcion = descripcionReceta,
                        onDescripcionChange = { viewModel.actualizarDescripcion(it) }
                    )
                    viewModel.errorNombre?.let { MensajeErrorCampo(it) }
                    viewModel.errorDescripcion?.let { MensajeErrorCampo(it) }

                    FotoPlatillo(
                        fotoDrawableRes = fotoDrawableRes,
                        onSubirFotoClick = { /* Galería / Cámara */ }
                    )

                    ListaIngredientes(
                        ingredientes = listaIngredientesState,
                        onAddIngrediente = { viewModel.agregarIngrediente(it) },
                        onDeleteIngrediente = { viewModel.eliminarIngrediente(it) }
                    )
                    viewModel.errorIngredientes?.let { MensajeErrorCampo(it) }

                    PasosPreparacion(
                        pasos = listaPasosState,
                        onAddPaso = { viewModel.agregarPaso(it) },
                        onDeletePaso = { viewModel.eliminarPaso(it) }
                    )
                    viewModel.errorPasos?.let { MensajeErrorCampo(it) }

                    EtiquetasReceta(
                        etiquetasSeleccionadas = etiquetasSeleccionadasState,
                        onToggleEtiqueta = { viewModel.alternarEtiqueta(it) },
                        onAddCustomEtiqueta = { viewModel.agregarEtiquetaPersonalizada(it) }
                    )
                    viewModel.errorEtiquetas?.let { MensajeErrorCampo(it) }

                    ConfiguracionPrivacidad(
                        compartirReceta = compartirReceta,
                        onCompartirChange = { viewModel.actualizarCompartirReceta(it) },
                        recetaSecreta = recetaSecreta,
                        onSecretaChange = { viewModel.actualizarRecetaSecreta(it) }
                    )

                    EnlaceFuente(
                        url = enlaceFuenteUrl,
                        onUrlChange = { viewModel.actualizarEnlaceFuente(it) }
                    )
                    viewModel.errorEnlaceFuente?.let { MensajeErrorCampo(it) }
                }

                AccionesGuardado(
                    onGuardarClick = {
                        if (viewModel.validarFormularioCompleto()) {
                            onGuardarExitoso()
                        }
                    },
                    onDescartarClick = {
                        onVolverClick()
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun MensajeErrorCampo(mensaje: String) {
    Text(
        text = mensaje,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaAgregarEditarRecetaPreview() {
    SaborioTheme {
        PantallaAgregarEditarReceta()
    }
}