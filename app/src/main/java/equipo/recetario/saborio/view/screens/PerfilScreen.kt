package equipo.recetario.saborio.view.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import equipo.recetario.saborio.ui.theme.Epilogue
import equipo.recetario.saborio.ui.theme.LightBrown
import equipo.recetario.saborio.ui.theme.LightBrownGrey
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.ui.theme.Peach
import equipo.recetario.saborio.ui.theme.PlusJakartaSans
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
    onNavigateToAgregarReceta: () -> Unit = {},
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
            TarjetaEncabezadoPerfil(
                usuario = usuario,
                recetas = misRecetas.size,
                secretas = misRecetasSecretas.size,
                onEditarClick = onEditarClick
            )
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
            onNavigateToPerfil,
            onNavigateToAgregarReceta
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
            Text(
                text = "Mis recetas secretas",
                fontSize = 24.sp,
                fontFamily = Epilogue,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            if (recetas.isEmpty()) {
                Text(
                    text = "Aún no hay recetas secretas",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
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
fun TarjetaEncabezadoPerfil(
    usuario: Usuario,
    recetas: Int,
    secretas: Int,
    onEditarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EncabezadoPerfil(usuario)
            Spacer(modifier = Modifier.height(16.dp))
            EstadisticasPerfil(recetas = recetas, secretas = secretas)
            Spacer(modifier = Modifier.height(12.dp))
            BotonEditarPerfil(onEditarClick)
        }
    }
}

@Composable
fun EncabezadoPerfil(usuario: Usuario) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(usuario.fotoPerfil),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Orange)
            ) {
                Icon(
                    painter = painterResource(R.drawable.photo_camera_24px),
                    contentDescription = "Cambiar foto",
                    tint = WhiteText,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = usuario.nombre,
            fontSize = 22.sp,
            fontFamily = Epilogue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = usuario.descripcionUsuario,
            fontSize = 13.sp,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = LightBrown
        )
    }
}

@Composable
fun EstadisticaPerfil(valor: Int, etiqueta: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = LightBrownGrey)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor.toString(),
                color = Orange,
                fontSize = 24.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = etiqueta,
                color = Brown,
                fontSize = 13.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EstadisticasPerfil(recetas: Int, secretas: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EstadisticaPerfil(valor = recetas, etiqueta = "Recetas", modifier = Modifier.weight(1f))
        EstadisticaPerfil(valor = secretas, etiqueta = "Secretas", modifier = Modifier.weight(1f))
    }
}

@Composable
fun BotonEditarPerfil(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(40.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBrownGrey,
            contentColor = Brown
        ),
        elevation = null
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_editar_datos),
            contentDescription = "Editar datos personales",
            modifier = Modifier.size(18.dp),
            tint = Brown
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Editar datos personales",
            fontSize = 14.sp,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            color = Brown
        )
    }
}

@Composable
fun BotonCerrarSesion(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Peach,
            contentColor = Orange
        ),
        elevation = null
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_cerrar_sesion),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Orange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Cerrar sesión",
            fontSize = 15.sp,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            color = Orange
        )
    }
}

@Composable
fun TarjetaFolderSecretas(cantidad: Int, onDesbloquearClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    Text(
                        text = "Folder de Recetas",
                        fontSize = 18.sp,
                        fontFamily = Epilogue,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "$cantidad recetas secretas",
                        fontSize = 13.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Normal,
                        color = LightBrown
                    )
                }
            }
            Button(
                onClick = onDesbloquearClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = WhiteText
                ),
                elevation = null
            ) {
                Text(
                    text = "Desbloquear",
                    fontSize = 14.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold
                )
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
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = titulo,
                fontSize = 18.sp,
                fontFamily = Epilogue,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
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
fun SwitchEstiloStoryboard(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val trackColor by animateColorAsState(
        targetValue = if (checked) Orange else Color(0xFFE5DFD7),
        label = "trackColor"
    )
    val bias by animateFloatAsState(
        targetValue = if (checked) 1f else -1f,
        label = "thumbBias"
    )

    Box(
        modifier = Modifier
            .width(46.dp)
            .height(26.dp)
            .clip(CircleShape)
            .background(trackColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onCheckedChange(!checked) }
            .padding(3.dp),
        contentAlignment = BiasAlignment(bias, 0f)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(WhiteText)
        )
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
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Modo oscuro",
                fontSize = 16.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold
            )
            SwitchEstiloStoryboard(
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
        fontFamily = Epilogue,
        fontWeight = FontWeight.SemiBold,
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

@Preview(showBackground = true, heightDp = 1350)
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
        Column(modifier = Modifier.padding(16.dp)) {
            TituloSeccion("Preferencias de la aplicación")
            TarjetaPreferencias()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTarjetaGrafica() {
    SaborioTheme {
        TarjetaGrafica("Recetas guardadas por mes", R.drawable.grafica_actividad) }
}

@Preview(showBackground = true)
@Composable
fun PreviewBotonCerrarSesion() {
    SaborioTheme {
        BotonCerrarSesion(onClick = {})
    }
}