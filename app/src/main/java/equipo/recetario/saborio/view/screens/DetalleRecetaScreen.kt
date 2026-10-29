package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import equipo.recetario.saborio.model.data.RecetaPrueba
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Ingrediente
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.view.components.BarraSuperiorDetalle
import equipo.recetario.saborio.view.components.EtiquetaEstrellas
import equipo.recetario.saborio.view.components.EtiquetaTag
import equipo.recetario.saborio.viewModel.RecetasViewModel

@Composable
fun detalleRecetaScreen(
    usuario: Usuario,
    receta: Receta,
    esPropia: Boolean,
    viewModel: RecetasViewModel = viewModel(),
    onEditarClick: (String) -> Unit = {}
) {

    var guardada by remember {
        mutableStateOf(receta.esGuardada)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        item {
            BarraSuperiorDetalle(
                usuario = usuario, onNavigateToPerfil = {})
        }

        item {
            heroReceta(receta)
        }

        item {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                encabezadoAutor(
                    usuario = usuario,
                    receta = receta,
                    esPropia = esPropia,
                    guardada = guardada,
                    onAccionClick = {
                        if (esPropia) {
                            onEditarClick(receta.id)
                        } else {
                            viewModel.cambiarEstadoGuardado(receta)
                            guardada = !guardada
                        }
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                tituloReceta(
                    receta
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                seccionIngredientes(
                    receta
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                seccionPreparacion(
                    receta
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                enlaceReceta(
                    receta
                )

                if (esPropia) {
                    seccionCalificacionPropia(receta)
                } else {
                    seccionCalificacion(receta)
                }

                Spacer(
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}

@Composable
fun seccionCalificacion(
    receta: Receta
) {

    val estrellas = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)
            )
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = receta.calificacion.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "★★★★★", color = Orange, fontSize = 24.sp
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .background(
                    MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)
                )
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Toca las estrellas para calificar esta receta",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            repeat(5) { index ->

                Text(
                    text = if (index < estrellas.intValue) "★" else "☆",
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun enlaceReceta(
    receta: Receta
) {

    receta.enlaceWeb?.let {

        Text(
            text = "Ver receta original en $it", color = Orange, fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}

@Composable
fun numeroPaso(
    numeroPaso: Int
) {

    Box(
        modifier = Modifier
            .size(34.dp)
            .background(
                Orange, CircleShape
            ), contentAlignment = Alignment.Center
    ) {

        Text(
            text = numeroPaso.toString(), color = Color.White, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun pasoPreparacionItem(
    numeroPaso: Int, descripcion: String
) {

    Row {

        numeroPaso(
            numeroPaso = numeroPaso
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column {

            Text(
                text = "Paso $numeroPaso", fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = descripcion
            )
        }
    }
}

@Composable
fun seccionPreparacion(
    receta: Receta
) {

    Text(
        text = "Preparación", fontSize = 22.sp, fontWeight = FontWeight.Bold
    )

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    receta.pasosPreparacion.forEachIndexed { index, paso ->

        pasoPreparacionItem(
            numeroPaso = index + 1, descripcion = paso
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
fun puntoIngrediente() {

    Box(
        modifier = Modifier
            .size(10.dp)
            .background(
                Orange, CircleShape
            )
    )
}

@Composable
fun ingredienteItem(
    ingrediente: Ingrediente
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)
            )
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        puntoIngrediente()

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Text(
            text = "${ingrediente.cantidad} ${ingrediente.unidadMedida} ${ingrediente.nombre}"
        )
    }
}

@Composable
fun seccionIngredientes(
    receta: Receta
) {

    Text(
        text = "Ingredientes", fontSize = 22.sp, fontWeight = FontWeight.Bold
    )

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    receta.ingredientes.forEach { ingrediente ->

        ingredienteItem(
            ingrediente
        )
    }
}

@Composable
fun tituloReceta(
    receta: Receta
) {

    Text(
        text = receta.nombre, fontSize = 32.sp, fontWeight = FontWeight.Bold, lineHeight = 35.sp
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    Text(
        text = receta.descripcionBreve, color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun fotoAutor(
    usuario: Usuario
) {

    Image(
        painter = painterResource(usuario.fotoPerfil),
        contentDescription = "autor",
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
    )
}

@Composable
fun encabezadoAutor(
    usuario: Usuario,
    receta: Receta,
    esPropia: Boolean,
    guardada: Boolean,
    onAccionClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        fotoAutor(usuario)

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = receta.nombreAutor ?: "Autor", fontWeight = FontWeight.Medium
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        botonAccionReceta(
            esPropia = esPropia,
            guardada = guardada,
            onClick = onAccionClick
        )
    }
}

@Composable
fun botonAccionReceta(
    guardada: Boolean,
    esPropia: Boolean,
    onClick: () -> Unit
) {

    val colorFondo = when {
        esPropia -> Orange
        guardada -> MaterialTheme.colorScheme.surface
        else -> Orange
    }

    val colorTexto = when {
        esPropia -> MaterialTheme.colorScheme.onPrimary
        guardada -> Orange
        else -> MaterialTheme.colorScheme.onPrimary
    }

    val texto = when {
        esPropia -> "Editar"
        guardada -> "Guardado"
        else -> "Guardar"
    }

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(
                colorFondo,
                RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {

        Text(
            text = if (esPropia) "Editar" else "Guardar",
            color = colorTexto,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun heroReceta(
    receta: Receta
) {

    Box {

        Image(
            painter = painterResource(receta.fotografia),
            contentDescription = receta.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            receta.etiquetas.take(3).forEach {
                EtiquetaTag(it)
            }
        }

        EtiquetaEstrellas(
            calificacion = receta.calificacion,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

@Composable
fun seccionCalificacionPropia(
    receta: Receta
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)
            )
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = receta.calificacion.toString(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = "★★★★☆", color = Orange, fontSize = 24.sp
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)
                )
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Tu receta ha sido calificada por ${receta.cantidadCalificaciones} personas",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preview() {
    detalleRecetaScreen(
        usuarioMuestra, RecetaPrueba, true, viewModel()
    )
}
