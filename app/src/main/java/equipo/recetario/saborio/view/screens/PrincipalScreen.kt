package equipo.recetario.saborio.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.ui.theme.BackgrounLightBrown
import equipo.recetario.saborio.ui.theme.Orange
import equipo.recetario.saborio.view.components.BarraInferior
import equipo.recetario.saborio.view.components.BarraSuperior
import equipo.recetario.saborio.view.components.Buscador
import equipo.recetario.saborio.view.components.FiltrosCategorias

@Composable
fun PrincipalScreen(
    usuario: Usuario,
    pestana: String,
    onNavigateToInicio: () -> Unit,
    onNavigateToExplorar: () -> Unit,
    onNavigateToGuardadas: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgrounLightBrown)
    ) {
        BarraSuperior(usuario)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Buscador()

            val titulo = when (pestana) {
                "Inicio" -> "Mis Recetas"
                "Explorar" -> "Explorar"
                else -> "Guardadas"
            }

            Text(
                text = titulo,
                color = Orange,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            FiltrosCategorias()
        }

        BarraInferior(
            pestana,
            onNavigateToInicio,
            onNavigateToExplorar,
            onNavigateToGuardadas,
            onNavigateToPerfil
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PrincipalScreenPreview() {
    PrincipalScreen(
        usuarioMuestra,
        "Inicio",
        {},
        {},
        {},
        {}
    )
}