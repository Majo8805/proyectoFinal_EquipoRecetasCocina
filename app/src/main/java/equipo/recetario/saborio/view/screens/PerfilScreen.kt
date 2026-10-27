package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.model.data.getMisRecetas
import equipo.recetario.saborio.model.data.getRecetasSecretas
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.BackgrounLightBrown
import equipo.recetario.saborio.ui.theme.SaborioTheme
import equipo.recetario.saborio.view.components.BarraInferior
import equipo.recetario.saborio.view.components.BarraSuperior
import equipo.recetario.saborio.view.components.ListaRecetas

@Composable
fun PerfilScreen(
    usuario: Usuario,
    onNavigateToInicio: () -> Unit,
    onNavigateToExplorar: () -> Unit,
    onNavigateToGuardadas: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onEditarClick: () -> Unit,
    onDesbloquearClick: () -> Unit,
    onCerrarSesionClick: () -> Unit
) {
    val misRecetas = getMisRecetas(usuario.nombre)
    val misRecetasSecretas = getRecetasSecretas(usuario.nombre)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgrounLightBrown)
    ) {
        BarraSuperior(usuario, onNavigateToPerfil)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            EncabezadoPerfil(usuario)
            EstadisticasPerfil(recetas = misRecetas.size, secretas = misRecetasSecretas.size)
            AccionesPerfil(
                onEditarClick = onEditarClick,
                onDesbloquearClick = onDesbloquearClick,
                onCerrarSesionClick = onCerrarSesionClick
            )
        }
        BarraInferior(
            "Perfil",
            onNavigateToInicio,
            onNavigateToExplorar,
            onNavigateToGuardadas,
            onNavigateToPerfil
        )
    }
}

@Composable
fun RecetasSecretasScreen(
    recetas: List<Receta>,
    onVolverClick: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize().background(BackgrounLightBrown)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onVolverClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text("Mis recetas secretas", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Box(modifier = Modifier.weight(1f)){
            if (recetas.isEmpty()){
                Text("Aún no hay recetas secretas",
                modifier = Modifier.align(Alignment.Center))
            } else {
                ListaRecetas(recetas, mostrarBotonGuardar = false, onGuardarClick = {})
            }
        }
    }
}


@Composable
fun EncabezadoPerfil(usuario: Usuario) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(usuario.fotoPerfil),
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = usuario.nombre,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = usuario.descripcionUsuario,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EstadisticaPerfil(valor: Int, etiqueta: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = valor.toString(), fontSize = 23.sp, fontWeight = FontWeight.Bold)
            Text(text = etiqueta, fontSize = 14.sp)
        }
    }
}

@Composable
fun EstadisticasPerfil(recetas: Int, secretas: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EstadisticaPerfil(valor = recetas, etiqueta = "Recetas", modifier = Modifier.weight(1f))
        EstadisticaPerfil(valor = secretas, etiqueta = "Secretas", modifier = Modifier.weight(1f))
    }
}

@Composable
fun AccionesPerfil(
    onEditarClick: () -> Unit,
    onDesbloquearClick: () -> Unit,
    onCerrarSesionClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledTonalButton(
            onClick = onEditarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar perfil"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Editar Perfil")
        }

        FilledTonalButton(
            onClick = onDesbloquearClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Desbloquear recetas"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Desbloquear Recetas Secretas")
        }

        OutlinedButton(
            onClick = onCerrarSesionClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(text = "Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecetasSecretasScreenPreview(){
    SaborioTheme {
        RecetasSecretasScreen(getRecetasSecretas(usuarioMuestra.nombre), {})
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    SaborioTheme {
        PerfilScreen(usuarioMuestra, {}, {}, {}, {}, {}, {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEncabezadoPerfil() {
    SaborioTheme {
        EncabezadoPerfil(usuarioMuestra)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEstadisticasPerfil() {
    SaborioTheme {
        EstadisticasPerfil(recetas = 3, secretas = 1)
    }
}