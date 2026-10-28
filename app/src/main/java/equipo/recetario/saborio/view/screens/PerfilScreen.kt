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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.data.getMisRecetas
import equipo.recetario.saborio.model.data.getRecetasSecretas
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.ui.theme.SaborioTheme
import equipo.recetario.saborio.ui.theme.WhiteText
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
    onCerrarSesionClick: () -> Unit,
    modoOscuro: Boolean = false,
    onModoOscuroChange: (Boolean) -> Unit = {}
) {
    val misRecetas = getMisRecetas(usuario.nombre)
    val misRecetasSecretas = getRecetasSecretas(usuario.nombre)
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onBackground) {
      Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
      ) {
        BarraSuperior(usuario, onNavigateToPerfil)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            EncabezadoPerfil(usuario)
            EstadisticasPerfil(recetas = misRecetas.size, secretas = misRecetasSecretas.size)
            BotonEditarPerfil(onEditarClick)
            TarjetaFolderSecretas(
                cantidad = misRecetasSecretas.size,
                onDesbloquearClick = onDesbloquearClick
            )
            TituloSeccion("Gráfica de actividad")
            TarjetaGrafica("Recetas guardadas por mes", R.drawable.grafica_actividad)
            TarjetaGrafica("Distribución de recetas", R.drawable.grafica_distribucion)
            TituloSeccion("Preferencias de la aplicación")
            TarjetaPreferencias(
                modoOscuro = modoOscuro,
                onModoOscuroChange = onModoOscuroChange
            )
            BotonCerrarSesion(onCerrarSesionClick)
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
}

@Composable
fun RecetasSecretasScreen(
    recetas: List<Receta>,
    onRecetaClick: (Receta) -> Unit = {},
    onVolverClick: () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onBackground) {
      Column(modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
          .statusBarsPadding()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            IconButton(onClick = onVolverClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text("Mis recetas secretas", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Box(modifier = Modifier.weight(1f)) {
            if (recetas.isEmpty()) {
                Text(
                    "Aún no hay recetas secretas",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                ListaRecetas(
                    recetas = recetas,
                    mostrarBotonGuardar = false,
                    onGuardarClick = {},
                    onRecetaClick = onRecetaClick
                )
            }
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
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor.toString(),
                color = Orange,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = etiqueta,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
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
fun BotonEditarPerfil(onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Brown
        )
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar datos personales"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Editar datos personales")
    }
}

@Composable
fun BotonCerrarSesion(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Orange
        )
    ) { Text("Cerrar sesión") }
}

@Composable
fun TarjetaFolderSecretas(cantidad: Int, onDesbloquearClick: () -> Unit) {
    Card(
        onClick = onDesbloquearClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Orange)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = WhiteText
                    )
                }
                Column {
                    Text(text = "Folder de Recetas", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$cantidad recetas secretas", fontSize = 14.sp)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onDesbloquearClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange,
                        contentColor = WhiteText
                    )) {
                    Text(text = "Desbloquear")
                }

            }
        }
    }
}

@Composable
fun TarjetaGrafica(titulo: String, imagen: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(imagen),
                contentDescription = titulo,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun TarjetaPreferencias(
    modoOscuro: Boolean = false,
    onModoOscuroChange: (Boolean) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Modo oscuro", fontSize = 16.sp)
            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange
            )
        }
    }
}

@Composable
fun TituloSeccion(texto: String) {
    Text(
        text = texto,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTarjetaFolderSecretas() {
    SaborioTheme { TarjetaFolderSecretas(cantidad = 1, onDesbloquearClick = {}) }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecetasSecretasScreen() {
    SaborioTheme {
        RecetasSecretasScreen(
            recetas = getRecetasSecretas(usuarioMuestra.nombre),
            onVolverClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilScreen() {
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

@Preview(showBackground = true)
@Composable
fun PreviewTarjetaPreferencias() {
    SaborioTheme {
        TituloSeccion("Preferencias de la aplicación")
        TarjetaPreferencias()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTarjetaGrafica() {
    SaborioTheme {
        TarjetaGrafica("Recetas guardadas por mes", R.drawable.grafica_actividad) }
}