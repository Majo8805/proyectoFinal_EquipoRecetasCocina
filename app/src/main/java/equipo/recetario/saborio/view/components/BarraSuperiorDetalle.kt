package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.Brown

@Composable
fun BarraSuperiorDetalle(
    usuario: Usuario,
    onBack: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {onBack}
        ) {
            Image(
                painter = painterResource(R.drawable.arrow_back_icon),
                contentDescription = "back"
            )
        }

        Text(
            text = "Detalle de Receta",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Brown
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onNavigateToPerfil
        ) {
            Image(
                painter = painterResource(usuario.fotoPerfil),
                contentDescription = "perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview
@Composable
fun preview(){
    BarraSuperiorDetalle(usuarioMuestra,{},{})


}