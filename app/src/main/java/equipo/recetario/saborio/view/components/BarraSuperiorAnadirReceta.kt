package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun BarraSuperiorAnadirReceta(
    titulo: String = "Añadir Receta",
    usuario: Usuario,
    onVolverClick: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onVolverClick) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back_24px),
                contentDescription = "Regresar",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText,
                fontSize = 20.sp
            )
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

@Composable
@Preview(showBackground = true)
fun BarraSuperiorAnadirRecetaPreview() {
    BarraSuperiorAnadirReceta(
        titulo = "Editar Receta",
        usuario = usuarioMuestra,
        onVolverClick = {},
        onNavigateToPerfil = {}
    )
}