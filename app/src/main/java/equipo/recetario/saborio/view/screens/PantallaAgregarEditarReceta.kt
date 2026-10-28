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
import equipo.recetario.saborio.view.components.*

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

    // Lista de ingredientes
    val listaIngredientesState = remember { mutableStateListOf<IngredienteItem>() }

    // Lista de pasos de preparación
    val listaPasosState = remember { mutableStateListOf<String>() }

    // Etiquetas elegidas
    val etiquetasSeleccionadasState = remember { mutableStateListOf<String>() }

    // Opciones de privacidad
    var compartirReceta by remember { mutableStateOf(true) }
    var recetaSecreta by remember { mutableStateOf(false) }

    // Enlace a fuente original
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
                            painter = painterResource(id = R.drawable.plus_icon), // Reemplazar con tu icono de flecha atrás / regresar
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Información Básica
            InformacionBasicaReceta(
                nombre = nombreReceta,
                onNombreChange = { nombreReceta = it },
                descripcion = descripcionReceta,
                onDescripcionChange = { descripcionReceta = it }
            )

            // 2. Foto del Platillo
            FotoPlatillo(
                onSubirFotoClick = {
                    // Acción para abrir la galería o tomar foto
                }
            )

            // 3. Lista de Ingredientes
            ListaIngredientes(
                ingredientes = listaIngredientesState,
                onAddIngrediente = { ingrediente ->
                    listaIngredientesState.add(ingrediente)
                },
                onDeleteIngrediente = { ingrediente ->
                    listaIngredientesState.remove(ingrediente)
                }
            )

            // 4. Pasos de Preparación
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

            // 5. Etiquetas de la Receta
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

            // 6. Configuración de Privacidad
            ConfiguracionPrivacidad(
                compartirReceta = compartirReceta,
                onCompartirChange = { compartirReceta = it },
                recetaSecreta = recetaSecreta,
                onSecretaChange = { recetaSecreta = it }
            )

            // 7. Enlace a la Fuente
            EnlaceFuente(
                url = enlaceFuenteUrl,
                onUrlChange = { enlaceFuenteUrl = it }
            )

            // 8. Botones de Acción
            AccionesGuardado(
                onGuardarClick = {
                    // Procesar el guardado de la receta
                    onGuardarExitoso()
                },
                onDescartarClick = {
                    onVolverClick()
                }
            )

            // Espacio final para scroll cómodo
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}