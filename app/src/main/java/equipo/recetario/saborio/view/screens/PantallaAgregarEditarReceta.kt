package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.*
import equipo.recetario.saborio.view.components.AccionesGuardado
import equipo.recetario.saborio.view.components.ConfiguracionPrivacidad
import equipo.recetario.saborio.view.components.EnlaceFuente
import equipo.recetario.saborio.view.components.EtiquetasReceta
import equipo.recetario.saborio.view.components.FotoPlatillo
import equipo.recetario.saborio.view.components.InformacionBasicaReceta
import equipo.recetario.saborio.view.components.IngredienteItem
import equipo.recetario.saborio.view.components.ListaIngredientes
import equipo.recetario.saborio.view.components.PasosPreparacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarEditarReceta(
    esEdicion: Boolean = false,
    onVolverClick: () -> Unit = {},
    onGuardarExitoso: () -> Unit = {}
) {
    // --- ESTADOS DE LA RECETA ---
    var nombreReceta by remember { mutableStateOf("") }
    var descripcionReceta by remember { mutableStateOf("") }

    // Listas dinámicas
    val listaIngredientesState = remember { mutableStateListOf<IngredienteItem>() }
    val listaPasosState = remember { mutableStateListOf<String>() }
    val etiquetasSeleccionadasState = remember { mutableStateListOf<String>() }

    // Opciones de configuración y privacidad
    var compartirReceta by remember { mutableStateOf(true) }
    var recetaSecreta by remember { mutableStateOf(false) }
    var enlaceFuenteUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (esEdicion) "Editar Receta" else "Agregar Receta",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = DarkText,
                            fontSize = 20.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_back_24px),
                            contentDescription = "Regresar",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            val isTabletOrWide = maxWidth >= 600.dp
            val paddingHorizontal = if (isTabletOrWide) 32.dp else 16.dp
            val spacingBetween = if (isTabletOrWide) 24.dp else 16.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = paddingHorizontal, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(spacingBetween),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isTabletOrWide) {
                    // --- DISEÑO RESPONSIVO (2 COLUMNAS PARA TABLETS / PANTALLAS ANCHAS) ---
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Columna Izquierda
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(spacingBetween)
                        ) {
                            InformacionBasicaReceta(
                                nombre = nombreReceta,
                                onNombreChange = { nombreReceta = it },
                                descripcion = descripcionReceta,
                                onDescripcionChange = { descripcionReceta = it }
                            )

                            FotoPlatillo(
                                onSubirFotoClick = { /* Galería / Cámara */ }
                            )

                            EtiquetasReceta(
                                etiquetasSeleccionadas = etiquetasSeleccionadasState,
                                onToggleEtiqueta = { tag ->
                                    if (etiquetasSeleccionadasState.contains(tag)) {
                                        etiquetasSeleccionadasState.remove(tag)
                                    } else {
                                        etiquetasSeleccionadasState.add(tag)
                                    }
                                },
                                onAddCustomEtiqueta = { nuevaEtiqueta ->
                                    if (!etiquetasSeleccionadasState.contains(nuevaEtiqueta)) {
                                        etiquetasSeleccionadasState.add(nuevaEtiqueta)
                                    }
                                }
                            )
                        }

                        // Columna Derecha
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(spacingBetween)
                        ) {
                            ListaIngredientes(
                                ingredientes = listaIngredientesState,
                                onAddIngrediente = { ingrediente ->
                                    listaIngredientesState.add(ingrediente)
                                },
                                onDeleteIngrediente = { ingrediente ->
                                    listaIngredientesState.remove(ingrediente)
                                }
                            )

                            PasosPreparacion(
                                pasos = listaPasosState,
                                onAddPaso = { nuevoPaso ->
                                    listaPasosState.add(nuevoPaso)
                                },
                                onDeletePaso = { index ->
                                    if (index in listaPasosState.indices) {
                                        listaPasosState.removeAt(index)
                                    }
                                }
                            )

                            ConfiguracionPrivacidad(
                                compartirReceta = compartirReceta,
                                onCompartirChange = { compartirReceta = it },
                                recetaSecreta = recetaSecreta,
                                onSecretaChange = { recetaSecreta = it }
                            )

                            EnlaceFuente(
                                url = enlaceFuenteUrl,
                                onUrlChange = { enlaceFuenteUrl = it }
                            )
                        }
                    }
                } else {
                    // --- DISEÑO MÓVIL (1 COLUMNA) ---
                    InformacionBasicaReceta(
                        nombre = nombreReceta,
                        onNombreChange = { nombreReceta = it },
                        descripcion = descripcionReceta,
                        onDescripcionChange = { descripcionReceta = it }
                    )

                    FotoPlatillo(
                        onSubirFotoClick = { /* Galería / Cámara */ }
                    )

                    ListaIngredientes(
                        ingredientes = listaIngredientesState,
                        onAddIngrediente = { ingrediente ->
                            listaIngredientesState.add(ingrediente)
                        },
                        onDeleteIngrediente = { ingrediente ->
                            listaIngredientesState.remove(ingrediente)
                        }
                    )

                    PasosPreparacion(
                        pasos = listaPasosState,
                        onAddPaso = { nuevoPaso ->
                            listaPasosState.add(nuevoPaso)
                        },
                        onDeletePaso = { index ->
                            if (index in listaPasosState.indices) {
                                listaPasosState.removeAt(index)
                            }
                        }
                    )

                    EtiquetasReceta(
                        etiquetasSeleccionadas = etiquetasSeleccionadasState,
                        onToggleEtiqueta = { tag ->
                            if (etiquetasSeleccionadasState.contains(tag)) {
                                etiquetasSeleccionadasState.remove(tag)
                            } else {
                                etiquetasSeleccionadasState.add(tag)
                            }
                        },
                        onAddCustomEtiqueta = { nuevaEtiqueta ->
                            if (!etiquetasSeleccionadasState.contains(nuevaEtiqueta)) {
                                etiquetasSeleccionadasState.add(nuevaEtiqueta)
                            }
                        }
                    )

                    ConfiguracionPrivacidad(
                        compartirReceta = compartirReceta,
                        onCompartirChange = { compartirReceta = it },
                        recetaSecreta = recetaSecreta,
                        onSecretaChange = { recetaSecreta = it }
                    )

                    EnlaceFuente(
                        url = enlaceFuenteUrl,
                        onUrlChange = { enlaceFuenteUrl = it }
                    )
                }

                // Botones de acción principales
                AccionesGuardado(
                    onGuardarClick = {
                        onGuardarExitoso()
                    },
                    onDescartarClick = {
                        onVolverClick()
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}