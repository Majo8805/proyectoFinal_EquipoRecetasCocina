package view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.ui.theme.DarkText

@Composable
fun CampoTextoReceta(
    valor: String,
    onCambioValor: (String) -> Unit,
    etiqueta: String,
    modifier: Modifier = Modifier,
    esLineaUnica: Boolean = true,
    maxLineas: Int = 1
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = etiqueta,
            color = DarkText,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = onCambioValor,
            singleLine = esLineaUnica,
            maxLines = maxLineas,
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = DarkText,
                unfocusedTextColor = DarkText
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}