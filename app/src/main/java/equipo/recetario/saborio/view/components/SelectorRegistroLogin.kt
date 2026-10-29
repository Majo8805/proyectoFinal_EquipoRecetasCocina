package equipo.recetario.saborio.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.Orange

@Composable
fun selectorAutenticacion(
    esRegistro: Boolean, onNavigateLogin: () -> Unit, onNavigateRegistro: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth().background(
                Color(0xFFF2F0EC), RoundedCornerShape(50.dp)
            ).padding(6.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ButtonSelector(
            texto = "Iniciar Sesión", seleccionado = !esRegistro, onClick = onNavigateLogin
        )

        ButtonSelector(
            texto = "Registro", seleccionado = esRegistro, onClick = onNavigateRegistro
        )
    }
}

@Composable
fun ButtonSelector(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(
                if (seleccionado) Color.White
                else Color.Transparent,
                RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp
            )
            .clickable {
                onClick()
            }
    ) {

        Text(
            text = texto,
            color = if (seleccionado)
                Orange
            else
                Brown,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun previewSelector(){
    selectorAutenticacion(false, {},{})
}