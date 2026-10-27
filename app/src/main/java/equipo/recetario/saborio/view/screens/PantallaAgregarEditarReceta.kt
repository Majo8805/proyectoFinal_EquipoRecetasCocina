package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.EstadoFormularioReceta
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.BackgrounLightBrown
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.WhiteText
import equipo.recetario.saborio.view.components.BarraSuperior
import equipo.recetario.saborio.view.components.CampoTextoReceta
import equipo.recetario.saborio.view.components.ListaDinamicaIngredientes
import equipo.recetario.saborio.view.components.ListaDinamicaPasos
import equipo.recetario.saborio.view.components.SeleccionadorImagenReceta

@Composable
fun PantallaAgregarEditarReceta(
    estado: EstadoFormularioReceta,
    usuario: Usuario = usuarioMuestra,
    onCambioTitulo: (String) -> Unit,
    onCambioDescripcion: (String) -> Unit,
    onCambioTiempoPreparacion: (String) -> Unit,
    onCambioPorciones: (String) -> Unit,
    onCambioCategoria: (String) -> Unit,
    onCambioIngrediente: (Int, String) -> Unit,
    onAgregarIngrediente: () -> Unit,
    onEliminarIngrediente: (Int) -> Unit,
    onCambioPaso: (Int, String) -> Unit,
    onAgregarPaso: () -> Unit,
    onEliminarPaso: (Int) -> Unit,
    onSeleccionarImagen: () -> Unit,
    onClicGuardar: () -> Unit,
    onClicRegresar: () -> Unit,
    onClicPerfil: () -> Unit = {}
) {
    val tituloPantalla = if (estado.esModoEdicion) "Editar Receta" else "Añadir Receta"

    Scaffold(
        topBar = {
            BarraSuperior(
                usuario = usuario
            )
        },
        containerColor = BackgrounLightBrown
    ) { valoresRelleno ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresRelleno)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = tituloPantalla,
                style = MaterialTheme.typography.headlineMedium
            )

            SeleccionadorImagenReceta(
                rutaImagen = estado.rutaImagen,
                onSeleccionarImagen = onSeleccionarImagen
            )

            CampoTextoReceta(
                valor = estado.titulo,
                onCambioValor = onCambioTitulo,
                etiqueta = "Nombre de la Receta"
            )

            CampoTextoReceta(
                valor = estado.descripcion,
                onCambioValor = onCambioDescripcion,
                etiqueta = "Descripción",
                esLineaUnica = false,
                maxLineas = 3
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CampoTextoReceta(
                    valor = estado.tiempoPreparacionMinutos,
                    onCambioValor = onCambioTiempoPreparacion,
                    etiqueta = "Tiempo (min)",
                    modifier = Modifier.weight(1f)
                )
                CampoTextoReceta(
                    valor = estado.porciones,
                    onCambioValor = onCambioPorciones,
                    etiqueta = "Porciones",
                    modifier = Modifier.weight(1f)
                )
            }

            CampoTextoReceta(
                valor = estado.categoria,
                onCambioValor = onCambioCategoria,
                etiqueta = "Categoría (ej. Postres, Comidas)"
            )

            ListaDinamicaIngredientes(
                ingredientes = estado.ingredientes,
                onCambioIngrediente = onCambioIngrediente,
                onAgregarIngrediente = onAgregarIngrediente,
                onEliminarIngrediente = onEliminarIngrediente
            )

            ListaDinamicaPasos(
                pasos = estado.pasos,
                onCambioPaso = onCambioPaso,
                onAgregarPaso = onAgregarPaso,
                onEliminarPaso = onEliminarPaso
            )

            Button(
                onClick = onClicGuardar,
                colors = ButtonDefaults.buttonColors(containerColor = Brown),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = if (estado.esModoEdicion) "Actualizar Receta" else "Guardar Receta",
                    color = WhiteText,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaAgregarEditarRecetaPreview() {
    PantallaAgregarEditarReceta(
        estado = EstadoFormularioReceta(),
        onCambioTitulo = {},
        onCambioDescripcion = {},
        onCambioTiempoPreparacion = {},
        onCambioPorciones = {},
        onCambioCategoria = {},
        onCambioIngrediente = { _, _ -> },
        onAgregarIngrediente = {},
        onEliminarIngrediente = {},
        onCambioPaso = { _, _ -> },
        onAgregarPaso = {},
        onEliminarPaso = {},
        onSeleccionarImagen = {},
        onClicGuardar = {},
        onClicRegresar = {}
    )
}