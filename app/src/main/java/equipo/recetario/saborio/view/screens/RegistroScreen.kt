package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import equipo.recetario.saborio.ui.theme.BackgrounLightBrown
import equipo.recetario.saborio.ui.theme.Brown
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.view.components.CampoRegistro
import equipo.recetario.saborio.view.components.SelectorGenero
import equipo.recetario.saborio.viewModel.UsuarioViewModel

@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit
) {

    val viewModel: UsuarioViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgrounLightBrown)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Saborío",
            fontSize = 28.sp,
            color = Orange,
            fontWeight = FontWeight.Bold
        )

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

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
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
                    onValueChange = {
                        viewModel.actualizarNombre(it)
                    },
                    placeholder = "Sofía Valenzuela"
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Correo electrónico",
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                CampoRegistro(
                    value = viewModel.correo,
                    onValueChange = {
                        viewModel.actualizarCorreo(it)
                    },
                    placeholder = "chef@correo.com"
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Fecha de nacimiento",
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                CampoRegistro(
                    value = viewModel.fechaNacimiento,
                    onValueChange = {
                        viewModel.actualizarFecha(it)
                    },
                    placeholder = "DD/MM/AAAA"
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

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
                    modifier = Modifier.height(10.dp)
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
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Contraseña",
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                CampoRegistro(
                    value = viewModel.password,
                    onValueChange = {
                        viewModel.actualizarPassword(it)
                    },
                    placeholder = "Mínimo 8 caracteres"
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Confirmar contraseña",
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                CampoRegistro(
                    value = viewModel.confirmarPassword,
                    onValueChange = {
                        viewModel.actualizarConfirmPassword(it)
                    },
                    placeholder = "Confirma tu contraseña"
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

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
                        text = "Crear Cuenta",
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}