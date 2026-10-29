package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EtiquetasReceta(
    etiquetasSeleccionadas: List<String>,
    onToggleEtiqueta: (String) -> Unit,
    onAddCustomEtiqueta: (String) -> Unit
) {
    var nuevaEtiquetaText by remember { mutableStateOf("") }
    val etiquetasPredeterminadas = listOf(
        "Cena", "Comida", "Desayuno", "Postre",
        "Snack", "Saludable", "Bebida", "Vegetariana",
        "Ensalada", "Panaderia"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = LightBrownGrey)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFFEBE3DA), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sell_24px),
                        contentDescription = "Etiquetas",
                        modifier = Modifier.size(22.dp)
                    )
                }
                Column {
                    Text(
                        text = "Etiquetas de la receta",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = DarkText,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = "Ayuda a otros a descubrirla por ocasión o dieta",
                        style = MaterialTheme.typography.bodySmall.copy(color = LightBrown)
                    )
                }
            }


            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                etiquetasPredeterminadas.forEach { tag ->
                    val isSelected = etiquetasSeleccionadas.contains(tag)
                    Surface(
                        modifier = Modifier.clickable { onToggleEtiqueta(tag) },
                        shape = RoundedCornerShape(50),
                        color = if (isSelected) Orange else Color.White
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = tag,
                                color = if (isSelected) Color.White else DarkText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            if (isSelected) {
                                Text("✓", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = nuevaEtiquetaText,
                    onValueChange = { nuevaEtiquetaText = it },
                    placeholder = { Text("Añadir otra etiqueta...", color = Color.Gray, fontSize = 14.sp) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = DarkText,
                        unfocusedTextColor = DarkText
                    ),
                    singleLine = true
                )

                Button(
                    onClick = {
                        if (nuevaEtiquetaText.isNotBlank()) {
                            onAddCustomEtiqueta(nuevaEtiquetaText)
                            nuevaEtiquetaText = ""
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Text("Agregar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
