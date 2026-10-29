package equipo.recetario.saborio.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import equipo.recetario.saborio.ui.theme.LightBrownGrey
import equipo.recetario.saborio.ui.theme.Brown

@Composable
fun CampoRegistro(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Brown
            )
        },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                LightBrownGrey,
                RoundedCornerShape(24.dp)
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = LightBrownGrey,
            unfocusedContainerColor = LightBrownGrey,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}