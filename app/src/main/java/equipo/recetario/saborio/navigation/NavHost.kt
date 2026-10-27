package equipo.recetario.saborio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.view.screens.DetalleRecetaScreen
import equipo.recetario.saborio.view.screens.PrincipalScreen

@Composable
fun AppNavHost(usuario: Usuario) {
    val navController = rememberNavController()
//Cuando el Login este hecho cambiar el startDestination para que use la ruta del Login
    NavHost(navController = navController, startDestination = VerMisRecetas) {
        composable<VerMisRecetas> {
            PrincipalScreen(
                usuario,
                "Inicio",
                onNavigateToInicio = { },
                onNavigateToExplorar = {
                    navController.navigate(VerExplorar)
                },
                onNavigateToGuardadas = {
                    navController.navigate(VerGuardadas)
                },
                onNavigateToPerfil = {
                    navController.navigate(VerPerfil)
                },
                onRecetaClick = { receta ->
                    navController.navigate(
                        VerDetalleReceta(receta.id, true)
                    )
                }
            )
        }
        composable<VerExplorar> {
            PrincipalScreen(
                usuario,
                "Explorar",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { navController.navigate(VerPerfil) },
                onRecetaClick = { receta ->
                    navController.navigate(
                        VerDetalleReceta(receta.id, false)
                    )
                }
            )
        }
        composable<VerGuardadas> {
            PrincipalScreen(
                usuario,
                "Guardadas",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = { },
                onNavigateToPerfil = { navController.navigate(VerPerfil) },
                onRecetaClick = { receta ->
                    navController.navigate(
                        VerDetalleReceta(receta.id, false)
                    )
                }
            )
        }
        composable<VerDetalleReceta> { backStackEntry ->

            val ruta = backStackEntry.toRoute<VerDetalleReceta>()

            val receta = listaRecetas.first {
                it.id == ruta.recetaId
            }

            DetalleRecetaScreen(
                usuario = usuario,
                receta = receta,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}