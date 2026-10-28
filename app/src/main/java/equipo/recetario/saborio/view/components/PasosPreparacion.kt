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

@Composable
fun PasosPreparacion(
    pasos: List<String>,
    onAddPaso: (String) -> Unit,
    onDeletePaso: (Int) -> Unit
) {
    var nuevoPasoText by remember { mutableStateOf("") }

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                            painter = painterResource(id = R.drawable.logo_icon),
                            contentDescription = "Pasos",
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Pasos de preparación",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = DarkText,
                                fontSize = 18.sp
                            )
                        )
                        Text(
                            text = "Lista de pasos",
                            style = MaterialTheme.typography.bodySmall.copy(color = LightBrown)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFEBE3DA)
                ) {
                    Text(
                        text = "${pasos.size} pasos",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = DarkText,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }


            pasos.forEachIndexed { index, paso ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(Color(0xFFFFE0D6), shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        color = Orange,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                                Text(
                                    text = "Paso ${index + 1}",
                                    fontWeight = FontWeight.Bold,
                                    color = DarkText,
                                    fontSize = 16.sp
                                )
                            }
                            IconButton(onClick = { onDeletePaso(index) }) {
                                Image(
                                    painter = painterResource(id = R.drawable.close_24px),
                                    contentDescription = "Borrar paso",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Text(
                            text = paso,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = DarkText,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }


            OutlinedTextField(
                value = nuevoPasoText,
                onValueChange = { nuevoPasoText = it },
                placeholder = { Text("Escribe el siguiente paso", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = DarkText,
                    unfocusedTextColor = DarkText
                )
            )

            Button(
                onClick = {
                    if (nuevoPasoText.isNotBlank()) {
                        onAddPaso(nuevoPasoText)
                        nuevoPasoText = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Orange)
            ) {
                Text("+ Añadir paso de preparación", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}