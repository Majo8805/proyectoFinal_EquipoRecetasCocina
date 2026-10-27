package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.DarkText
import equipo.recetario.saborio.ui.theme.Orange

@Composable
fun ListaDinamicaPasos(
    pasos: List<String>,
    onCambioPaso: (Int, String) -> Unit,
    onAgregarPaso: () -> Unit,
    onEliminarPaso: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Pasos de Preparación",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )
        Spacer(modifier = Modifier.height(8.dp))

        pasos.forEachIndexed { indice, paso ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CampoTextoReceta(
                    valor = paso,
                    onCambioValor = { nuevoValor -> onCambioPaso(indice, nuevoValor) },
                    etiqueta = "Paso ${indice + 1}",
                    esLineaUnica = false,
                    maxLineas = 3,
                    modifier = Modifier.weight(1f)
                )
                if (pasos.size > 1) {
                    IconButton(onClick = { onEliminarPaso(indice) }) {
                        Image(
                            painter = painterResource(id = R.drawable.add_24px),
                            contentDescription = "Eliminar paso",
                            colorFilter = ColorFilter.tint(Orange),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        TextButton(
            onClick = onAgregarPaso,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_24px),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Orange),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Añadir paso",
                color = Orange,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}