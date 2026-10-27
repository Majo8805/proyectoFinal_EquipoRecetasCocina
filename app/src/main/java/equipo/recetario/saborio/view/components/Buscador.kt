package equipo.recetario.saborio.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.LightBrown
import equipo.recetario.saborio.ui.theme.LightBrownGrey

@Composable
fun Buscador(
    query: String,
    onQueryChange: (String) -> Unit,
    onSortClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f),
            placeholder = {
                Text("Buscar recetas", color = LightBrown)
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "search_icon",
                    colorFilter = ColorFilter.tint(LightBrown)
                )
            },
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightBrownGrey,
                unfocusedContainerColor = LightBrownGrey,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(color = LightBrownGrey, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { onSortClick() }) {
                Image(
                    painter = painterResource(R.drawable.sort_arrows_icon),
                    contentDescription = "sort_arrows"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuscadorPreview() {
    var textoPrueba by remember { mutableStateOf("") }
    Buscador(
        query = textoPrueba,
        onQueryChange = { textoPrueba = it },
        onSortClick = {}
    )
}