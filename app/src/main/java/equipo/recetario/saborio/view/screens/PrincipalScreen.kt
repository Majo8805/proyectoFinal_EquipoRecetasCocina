package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.view.components.BarraInferior
import equipo.recetario.saborio.view.components.BarraSuperior
import equipo.recetario.saborio.view.components.Buscador
import equipo.recetario.saborio.view.components.FiltrosCategorias
import equipo.recetario.saborio.view.components.ListaRecetas
import equipo.recetario.saborio.viewModel.RecetasViewModel

@Composable
fun PrincipalScreen(
    usuario: Usuario,
    pestana: String,
    onNavigateToInicio: () -> Unit,
    onNavigateToExplorar: () -> Unit,
    onNavigateToGuardadas: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToAgregarReceta: () -> Unit = {},
    onRecetaClick: (Receta) -> Unit,
    viewModel: RecetasViewModel = viewModel()
) {
    LaunchedEffect(key1 = pestana) {
        viewModel.cargarRecetas(pestana = pestana, nombreUsuario = usuario.nombre)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
      Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
      ) {
        BarraSuperior(usuario, onNavigateToPerfil)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Buscador(query = viewModel.textoBusqueda, onQueryChange = { nuevoTexto ->
                viewModel.actualizarBusqueda(nuevoTexto)
            }, onSortClick = {
                viewModel.ordenarPorEstrellas()
            })

            Text(
                text = viewModel.titulo,
                color = Orange,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            FiltrosCategorias(
                viewModel.categoriaSeleccionada, onCategoriaSelected = { nuevaCategoria ->
                    viewModel.seleccionarCategoria(nuevaCategoria, usuario.nombre)
                })

            if (!viewModel.hayRecetas) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron recetas",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                ListaRecetas(
                    recetas = viewModel.recetasAMostrar,
                    mostrarBotonGuardar = viewModel.mostrarBotonGuardar,
                    onGuardarClick = { receta ->
                        viewModel.cambiarEstadoGuardado(receta)
                    },
                    onRecetaClick = onRecetaClick
                )
            }


        }

        BarraInferior(
            pestana,
            onNavigateToInicio,
            onNavigateToExplorar,
            onNavigateToGuardadas,
            onNavigateToPerfil,
            onNavigateToAgregarReceta
        )
    }
}
}

@Composable
@Preview(showBackground = true)
fun PrincipalScreenPreview() {
    PrincipalScreen(usuarioMuestra, "Inicio", {}, {}, {}, {}, {}, {})
}