package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.DarkText
import equipo.recetario.saborio.ui.theme.Epilogue
import equipo.recetario.saborio.ui.theme.Orange

@Composable
fun BarraSuperior(usuario: Usuario, onNavigateToPerfil: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.logo_icon),
                contentDescription = "logo_icon",
                modifier = Modifier
                    .background(
                        color = Orange,
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Saborío",
                color = Orange,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = Epilogue
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onNavigateToPerfil() }) {
                Image(
                    painter = painterResource(usuario.fotoPerfil),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun BarraSuperiorAnadirReceta(
    titulo: String,
    onVolverClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back_icon),
            contentDescription = "Regresar",
            colorFilter = ColorFilter.tint(DarkText),
            modifier = Modifier
                .size(28.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onVolverClick() }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText,
                fontSize = 22.sp
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BarraSuperiorPreview() {
    BarraSuperior(usuarioMuestra, {})
}

@Composable
@Preview(showBackground = true)
fun BarraSuperiorAnadirRecetaPreviewSample() {
    BarraSuperiorAnadirReceta(titulo = "Agregar Receta", onVolverClick = {})
}
