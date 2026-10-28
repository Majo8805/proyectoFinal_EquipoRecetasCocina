package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun ConfiguracionPrivacidad(
    compartirReceta: Boolean,
    onCompartirChange: (Boolean) -> Unit,
    recetaSecreta: Boolean,
    onSecretaChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "CONFIGURACIÓN DE PRIVACIDAD",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Brown,
                letterSpacing = 1.sp
            )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (compartirReceta) 1.5.dp else 0.dp,
                    color = if (compartirReceta) Orange else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { onCompartirChange(!compartirReceta) },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LightBrownGrey)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFFFE0D6), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.explore_icon),
                            contentDescription = "Compartir",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Compartir receta",
                            fontWeight = FontWeight.Bold,
                            color = DarkText,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "La receta será visible para otros usuarios en la sección \"Explorar recetas\". Si no se activa, la receta solo será visible en \"Mis recetas\".",
                            style = MaterialTheme.typography.bodySmall.copy(color = Brown, lineHeight = 18.sp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(if (compartirReceta) Orange else Color.White, shape = CircleShape)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (recetaSecreta) 1.5.dp else 0.dp,
                    color = if (recetaSecreta) Orange else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { onSecretaChange(!recetaSecreta) },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = LightBrownGrey)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFFFE0D6), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.guardar_icon),
                            contentDescription = "Secreta",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Receta secreta",
                            fontWeight = FontWeight.Bold,
                            color = DarkText,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Solo tú podrás acceder desde tu perfil a esta receta, autenticando con tu huella dactilar.",
                            style = MaterialTheme.typography.bodySmall.copy(color = Brown, lineHeight = 18.sp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(if (recetaSecreta) Orange else Color.White, shape = CircleShape)
                )
            }
        }
    }
}