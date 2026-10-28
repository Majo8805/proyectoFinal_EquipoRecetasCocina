package equipo.recetario.saborio.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.ui.theme.WhiteText
import equipo.recetario.saborio.utilities.EtiquetasPredeterminadas

@Composable
fun FiltrosCategorias(
    categoriaSeleccionada: String,
    onCategoriaSelected: (String) -> Unit
) {
    val categorias = listOf(
        "Todas",
        EtiquetasPredeterminadas.DESAYUNO,
        EtiquetasPredeterminadas.COMIDA,
        EtiquetasPredeterminadas.CENA,
        EtiquetasPredeterminadas.POSTRE,
        EtiquetasPredeterminadas.SNACK,
        EtiquetasPredeterminadas.SALUDABLE,
        EtiquetasPredeterminadas.VEGETARIANA,
        EtiquetasPredeterminadas.BEBIDA,
        EtiquetasPredeterminadas.ENSALADA,
        EtiquetasPredeterminadas.PANADERIA,
        "Otras"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(categorias) { categoria ->
            val esActiva = categoria == categoriaSeleccionada

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        color = if (esActiva) Orange else MaterialTheme.colorScheme.surfaceVariant
                    )
                    .clickable {
                        onCategoriaSelected(categoria)
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = categoria,
                    color = if (esActiva) WhiteText else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FiltrosCategoriasPreview() {
    var categoriaPrueba by remember { mutableStateOf("Todas") }
    FiltrosCategorias(
        categoriaSeleccionada = categoriaPrueba,
        onCategoriaSelected = { categoriaPrueba = it }
    )
}