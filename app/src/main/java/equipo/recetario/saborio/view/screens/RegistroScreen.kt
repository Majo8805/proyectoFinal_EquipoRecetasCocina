package equipo.recetario.saborio.view.screens

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import equipo.recetario.saborio.R
import equipo.recetario.saborio.ui.theme.BackgrounLightBrown
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.LightBrownGrey
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.view.components.CampoRegistro
import equipo.recetario.saborio.view.components.SelectorGenero
import equipo.recetario.saborio.viewModel.UsuarioViewModel

@Composable
fun registroScreen(
    onRegistroExitoso: () -> Unit
) {

    val viewModel: UsuarioViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgrounLightBrown)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            //.padding(13.dp,100.dp,13.dp,1.dp)
            //majo dijo que no se ocupa...
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        headerRegistro()

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        tarjetaRegistro(
            viewModel = viewModel,
            onRegistroExitoso = onRegistroExitoso
        )
    }
}

@Composable
fun headerRegistro() {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.logo_icon),
            contentDescription = "logo_icon",
            modifier = Modifier
                .background(
                    color = Orange,
                    shape = CircleShape
                )
                .padding(8.dp)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text = "Saborío",
            fontSize = 28.sp,
            color = Orange,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(
        modifier = Modifier.height(24.dp)
    )

    Text(
        text = "Crear Cuenta",
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    Text(
        text = "¡Crea tu cuenta hoy mismo!",
        color = Brown,
        fontSize = 18.sp
    )
}

@Composable
fun tarjetaRegistro(
    viewModel: UsuarioViewModel,
    onRegistroExitoso: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp),

    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            campoNombre(viewModel)

            campoCorreo(viewModel)

            campoFechaNacimiento(viewModel)

            seccionGenero(viewModel)

            campoPassword(viewModel)

            campoConfirmPassword(viewModel)

            botonCrearCuenta(
                viewModel = viewModel,
                onRegistroExitoso = onRegistroExitoso
            )
        }
    }
}

@Composable
fun campoNombre(
    viewModel: UsuarioViewModel
) {

    Text(
        text = "Nombre de Usuario",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    CampoRegistro(
        value = viewModel.nombre,
        onValueChange = viewModel::actualizarNombre,
        placeholder = "Sofía Valenzuela"
    )

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun campoCorreo(
    viewModel: UsuarioViewModel
) {

    Text(
        text = "Correo electrónico",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    CampoRegistro(
        value = viewModel.correo,
        onValueChange = viewModel::actualizarCorreo,
        placeholder = "chef@correo.com"
    )

    if (viewModel.correo.isNotBlank()) {

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        if (viewModel.correoValido()) {

            Text(
                text = "✓ Correo válido",
                color = Color(0xFF2E7D32),
                fontSize = 12.sp
            )

        } else {

            Text(
                text = "Ingresa un correo válido",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun campoFechaNacimiento(
    viewModel: UsuarioViewModel
) {

    val context = LocalContext.current

    val calendar = remember {
        Calendar.getInstance()
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->

            val fechaSeleccionada =
                "%02d/%02d/%04d".format(
                    dayOfMonth,
                    month + 1,
                    year
                )

            viewModel.actualizarFecha(fechaSeleccionada)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Text(
        text = "Fecha de nacimiento",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    OutlinedTextField(
        value = viewModel.fechaNacimiento,
        onValueChange = {},
        enabled = false,
        readOnly = true,
        placeholder = {
            Text("Selecciona una fecha")
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                datePickerDialog.show()
            },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = LightBrownGrey,
            unfocusedContainerColor = LightBrownGrey,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    )

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun seccionGenero(
    viewModel: UsuarioViewModel
) {

    Text(
        text = "Género",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SelectorGenero(
            texto = "Femenino",
            seleccionado = viewModel.genero == "Femenino",
            onClick = {
                viewModel.actualizarGenero("Femenino")
            }
        )

        SelectorGenero(
            texto = "Masculino",
            seleccionado = viewModel.genero == "Masculino",
            onClick = {
                viewModel.actualizarGenero("Masculino")
            }
        )
    }

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SelectorGenero(
            texto = "Otros",
            seleccionado = viewModel.genero == "Otros",
            onClick = {
                viewModel.actualizarGenero("Otros")
            }
        )

        SelectorGenero(
            texto = "Prefiero no decirlo",
            seleccionado = viewModel.genero == "Prefiero no decirlo",
            onClick = {
                viewModel.actualizarGenero("Prefiero no decirlo")
            }
        )
    }

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun CampoPassword(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation(),
        placeholder = {
            Text(placeholder)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = LightBrownGrey,
            unfocusedContainerColor = LightBrownGrey,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun campoPassword(
    viewModel: UsuarioViewModel
) {

    Text(
        text = "Contraseña",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    CampoPassword(
        value = viewModel.password,
        onValueChange = viewModel::actualizarPassword,
        placeholder = "Mínimo 8 caracteres"
    )

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun campoConfirmPassword(
    viewModel: UsuarioViewModel
) {

    Text(
        text = "Confirmar contraseña",
        fontWeight = FontWeight.SemiBold
    )

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    CampoPassword(
        value = viewModel.confirmarPassword,
        onValueChange = viewModel::actualizarConfirmPassword,
        placeholder = "Confirma tu contraseña"
    )

    if (viewModel.confirmarPassword.isNotBlank()) {

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        if (viewModel.passwordsCoinciden()) {

            Text(
                text = "✓ Las contraseñas coinciden",
                color = Color(0xFF2E7D32),
                fontSize = 12.sp
            )

        } else {

            Text(
                text = "Las contraseñas no coinciden",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }

    Spacer(
        modifier = Modifier.height(20.dp)
    )
}

@Composable
fun botonCrearCuenta(
    viewModel: UsuarioViewModel,
    onRegistroExitoso: () -> Unit
) {

    Button(
        onClick = {

            val usuario = viewModel.registrarUsuario()

            if (usuario != null) {
                onRegistroExitoso()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
    ) {

        Text(
            text = "Crear Cuenta"
        )
    }
}


@Preview
@Composable
fun previewRegistroScreen() {
    registroScreen({})
}