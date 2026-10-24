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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import equipo.recetario.saborio.R

@Composable
fun EncabezadoPerfil(usuario: Usuario){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_perfil_default),
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer).padding(20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = usuario.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = usuario.descripcionUsuario, fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun EstadisticaPerfil(valor: Int, etiqueta: String, modifier: Modifier = Modifier){
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = valor.toString(), fontSize = 23.sp, fontWeight = FontWeight.Bold)
            Text(text = etiqueta, fontSize = 14.sp)
        }
    }
}

@Composable
fun EstadisticasPerfil(recetas: Int, secretas: Int){
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        EstadisticaPerfil(valor = recetas, etiqueta = "Recetas", modifier = Modifier.weight(1f))
        EstadisticaPerfil(valor = secretas, etiqueta = "Secretas", modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEncabezadoPerfil(){
    SaborioTheme {
        EncabezadoPerfil(usuarioMuestra)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEstadisticasPerfil(){
    SaborioTheme {
        EstadisticasPerfil(recetas = 3, secretas = 1)
    }
}