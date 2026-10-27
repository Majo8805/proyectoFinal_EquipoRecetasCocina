package view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.CustomBg
import equipo.recetario.saborio.ui.theme.DarkText
import equipo.recetario.saborio.ui.theme.LightBrown

@Composable
fun SeleccionadorImagenReceta(
    rutaImagen: String?,
    onSeleccionarImagen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(CustomBg)
            .clickable { onSeleccionarImagen() },
        contentAlignment = Alignment.Center
    ) {
        if (rutaImagen.isNullOrEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Carga la imagen o vector desde la carpeta res/drawable
                Image(
                    painter = painterResource(id = R.drawable.photo_camera_24px),
                    contentDescription = "Seleccionar Imagen",
                    colorFilter = ColorFilter.tint(Brown),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Añadir foto de la receta",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = LightBrown
                )
            }
        } else {
            Text(
                text = "Imagen seleccionada: $rutaImagen",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = DarkText
            )
        }
    }
}