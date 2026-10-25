package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.Orange

@Composable
fun BarraInferior() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable{}
            ) {
                Image(painter = painterResource(R.drawable.home_icon),
                    contentDescription = "home_icon",
                    colorFilter = ColorFilter.tint(Brown)
                )
                Text(
                    "Inicio",
                    color = Brown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable{}
            ) {
                Image(painter = painterResource(R.drawable.explore_icon),
                    contentDescription = "explore_icon"
                )
                Text(
                    "Explorar",
                    color = Brown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                containerColor = Orange,
                modifier = Modifier
                    .size(40.dp)
            ) {
                Image(painter = painterResource(R.drawable.plus_icon),
                    contentDescription = "plus_icon")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable{}
            ) {
                Image(painter = painterResource(R.drawable.guardar_cafe_icon),
                    contentDescription = "guardar_cafe_icon"
                )
                Text(
                    "Guardadas",
                    color = Brown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable{}
            ) {
                Image(painter = painterResource(R.drawable.person_icon),
                    contentDescription = "person_icon",
                    colorFilter = ColorFilter.tint(Brown)
                )
                Text(
                    "Perfil",
                    color = Brown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BarraInferiorPreview() {
    BarraInferior()
}