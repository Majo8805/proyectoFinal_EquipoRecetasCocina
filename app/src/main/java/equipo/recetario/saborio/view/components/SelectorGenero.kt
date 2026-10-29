package equipo.recetario.saborio.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.LightBrownGrey
import equipo.recetario.saborio.ui.theme.Orange

@Composable
fun SelectorGenero(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(
                color =
                    if (seleccionado)
                        Orange
                    else
                        LightBrownGrey,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 18.dp,
                vertical = 10.dp
            )
    ) {

        Text(
            text = texto,
            color =
                if (seleccionado)
                    Color.White
                else
                    Brown,
            fontWeight = FontWeight.SemiBold
        )
    }
}