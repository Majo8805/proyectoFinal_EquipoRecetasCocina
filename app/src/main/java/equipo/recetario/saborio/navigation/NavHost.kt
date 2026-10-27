package equipo.recetario.saborio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import equipo.recetario.saborio.model.data.getRecetasSecretas
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.view.screens.EditarPerfilScreen
import equipo.recetario.saborio.view.screens.PerfilScreen
import equipo.recetario.saborio.view.screens.PrincipalScreen
import equipo.recetario.saborio.view.screens.RecetasSecretasScreen

@Composable
fun AppNavHost(usuario: Usuario) {
    val navController = rememberNavController()
//Cuando el Login este hecho cambiar el startDestination para que use la ruta del Login
    NavHost(navController = navController, startDestination = VerMisRecetas) {
        composable<VerMisRecetas> {
            PrincipalScreen(
                usuario,
                "Inicio",
                onNavigateToInicio = {  },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { navController.navigate(VerPerfil) }
            )
        }
        composable<VerExplorar> {
            PrincipalScreen(
                usuario,
                "Explorar",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = {  },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { navController.navigate(VerPerfil) }
            )
        }
        composable<VerGuardadas> {
            PrincipalScreen(
                usuario,
                "Guardadas",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = {  },
                onNavigateToPerfil = { navController.navigate(VerPerfil) }
            )
        }
        composable<VerPerfil> {
            PerfilScreen(
                usuario,
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { },
                onEditarClick = { navController.navigate(VerEditarPerfil)},
                onDesbloquearClick = { navController.navigate(VerRecetasSecretas) },
                onCerrarSesionClick = { }
            )
        }
        composable<VerEditarPerfil> {
            EditarPerfilScreen(
                usuario,
                onGuardarClick = { navController.popBackStack() },
                onCancelarClick = { navController.popBackStack() }
            )
        }
        composable<VerRecetasSecretas>{
            RecetasSecretasScreen(
                recetas = getRecetasSecretas(usuario.nombre),
                onVolverClick = { navController.popBackStack() }
            )
        }
    }
}