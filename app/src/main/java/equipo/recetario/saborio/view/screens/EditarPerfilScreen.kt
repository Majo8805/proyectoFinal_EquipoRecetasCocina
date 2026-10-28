package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.SaborioTheme

@Composable
fun EditarPerfilScreen(
    usuario: Usuario,
    onGuardarClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var descripcion by remember { mutableStateOf(usuario.descripcionUsuario) }
    var fechaNacimiento by remember { mutableStateOf(usuario.fechaNacimiento) }
    var genero by remember { mutableStateOf(usuario.genero) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Editar datos personales", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it},
            label = { Text("Fecha de nacimiento")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it},
            label = { Text("Género")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = usuario.correo,
            onValueChange = {},
            label = { Text("Correo") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onGuardarClick) { Text("Guardar") }
            OutlinedButton(onClick = onCancelarClick) { Text("Cancelar") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilScreenPreview() {
    SaborioTheme {
        EditarPerfilScreen(usuarioMuestra, {}, {})
    }
}