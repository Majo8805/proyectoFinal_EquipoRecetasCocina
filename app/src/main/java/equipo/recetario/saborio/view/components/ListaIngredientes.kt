package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

data class IngredienteItem(val nombre: String, val cantidad: String, val unidad: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaIngredientes(
    ingredientes: List<IngredienteItem>,
    onAddIngrediente: (IngredienteItem) -> Unit,
    onDeleteIngrediente: (IngredienteItem) -> Unit
) {
    var ingredienteInput by remember { mutableStateOf("") }
    var cantidadInput by remember { mutableStateOf("") }
    var unidadSeleccionada by remember { mutableStateOf("") }
    var expandedComboBox by remember { mutableStateOf(false) }

    val opcionesUnidades = listOf("Unidades", "gr", "kg", "ml", "lt", "Cucharada", "Cucharadita", "Taza", "Pizca")

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
                        .background(Orange, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shopping_cart_24px),
                        contentDescription = "Ingredientes",
                        modifier = Modifier.size(22.dp)
                    )
                }
                Column {
                    Text(
                        text = "Ingredientes",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = DarkText,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = "Agrega proporciones exactas",
                        style = MaterialTheme.typography.bodySmall.copy(color = LightBrown)
                    )
                }
            }


            ingredientes.forEach { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("::", color = Color.LightGray, fontWeight = FontWeight.Bold)
                            Column {
                                Text(
                                    text = item.nombre,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkText,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = "${item.cantidad} ${item.unidad}",
                                    color = Orange,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        IconButton(onClick = { onDeleteIngrediente(item) }) {
                            Image(
                                painter = painterResource(id = R.drawable.delete_24px),
                                contentDescription = "Eliminar",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }


            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = ingredienteInput,
                    onValueChange = { ingredienteInput = it },
                    placeholder = { Text("Ingrediente (ej. Salsa de tomate)", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = cantidadInput,
                        onValueChange = { cantidadInput = it },
                        placeholder = { Text("Cantidad (ej. 2)", color = Color.Gray) },
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


                    ExposedDropdownMenuBox(
                        expanded = expandedComboBox,
                        onExpandedChange = { expandedComboBox = !expandedComboBox },
                        modifier = Modifier.weight(1.3f)
                    ) {
                        OutlinedTextField(
                            value = unidadSeleccionada,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Unidad de medida", color = Color.Gray, fontSize = 13.sp) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedComboBox) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
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

                        ExposedDropdownMenu(
                            expanded = expandedComboBox,
                            onDismissRequest = { expandedComboBox = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            opcionesUnidades.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion, color = DarkText) },
                                    onClick = {
                                        unidadSeleccionada = opcion
                                        expandedComboBox = false
                                    }
                                )
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        if (ingredienteInput.isNotBlank() && cantidadInput.isNotBlank()) {
                            onAddIngrediente(
                                IngredienteItem(
                                    nombre = ingredienteInput,
                                    cantidad = cantidadInput,
                                    unidad = unidadSeleccionada.ifBlank { "Unidades" }
                                )
                            )
                            ingredienteInput = ""
                            cantidadInput = ""
                            unidadSeleccionada = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text("+ Añadir ingrediente", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
