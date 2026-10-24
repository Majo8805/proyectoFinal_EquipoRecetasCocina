package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.SaborioTheme

@Composable
fun EncabezadoPerfil(usuario: Usuario){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = usuario.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = usuario.descripcionUsuario, fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEncabezadoPerfil(){
    SaborioTheme {
        EncabezadoPerfil(usuarioMuestra)
    }
}
