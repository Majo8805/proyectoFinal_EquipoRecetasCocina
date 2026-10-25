package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.data.RecetaPrueba
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.utilities.getColorByEtiqueta

@Composable
fun RecetaElement(receta: Receta, mostrarBotonGuardar: Boolean) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 18.dp)
        ) {
            Box() {
                Image(painter = painterResource(id = receta.fotografia),
                    contentDescription = receta.nombre + "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(shape = RoundedCornerShape(size = 24.dp))
                )
                if (mostrarBotonGuardar) {
                    BtnGuardar(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                    )
                }
                EtiquetaEstrellas(
                    calificacion = receta.calificacion,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
            }
            Text(
                text = receta.nombre,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp,10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                receta.etiquetas.forEach { tag ->
                    EtiquetaTag(texto = tag)
                }
            }
        }
    }
}

@Composable
fun BtnGuardar(modifier: Modifier) {
    IconButton(
        onClick = {},
        modifier
            .size(48.dp)
            .background(Color.White, CircleShape)
    ) {
        Image(
            painter = painterResource(R.drawable.guardar_icon),
            contentDescription = "guardar_icon"
        )
    }
}

@Composable
fun EtiquetaEstrellas(calificacion: Double, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.6f)
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Image(painter = painterResource(R.drawable.star_icon),
                contentDescription = "stars_icon",
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = calificacion.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun EtiquetaTag(texto: String) {
    val colores = getColorByEtiqueta(texto)
    Surface(
        color = colores.first,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(end = 6.dp)
    ) {
        Text(
            text = texto,
            color = colores.second,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RecetaElementPreview() {
    RecetaElement(RecetaPrueba, true)
}