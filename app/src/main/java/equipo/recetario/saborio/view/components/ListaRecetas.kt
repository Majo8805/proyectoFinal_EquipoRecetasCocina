package equipo.recetario.saborio.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.model.domain.Receta

@Composable
fun ListaRecetas(
    recetas: List<Receta>,
    mostrarBotonGuardar: Boolean,
    onGuardarClick: (Receta) -> Unit,
    onRecetaClick: (Receta) -> Unit
    ) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 15.dp,
            end = 15.dp,
            bottom = 32.dp
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        items(recetas) { receta ->
            RecetaElement(receta, mostrarBotonGuardar, onGuardarClick, onRecetaClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaRecetasPreview() {
    ListaRecetas(
        listaRecetas,
        true,
        {},
        {}
    )
}