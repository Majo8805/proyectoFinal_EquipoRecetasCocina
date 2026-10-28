package equipo.recetario.saborio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.model.data.getRecetasSecretas
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.view.screens.detalleRecetaScreen
import equipo.recetario.saborio.view.screens.EditarPerfilScreen
import equipo.recetario.saborio.view.screens.PerfilScreen
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.view.screens.DetalleRecetaScreen
import equipo.recetario.saborio.view.screens.PrincipalScreen
import equipo.recetario.saborio.view.screens.RecetasSecretasScreen

@Composable
fun AppNavHost(
    usuario: Usuario,
    modoOscuro: Boolean = false,
    onModoOscuroChange: (Boolean) -> Unit = {}
) {
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
                        VerDetalleReceta(receta.id)
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

            detalleRecetaScreen(
                usuario = usuario,
                receta = receta,
                esPropia = ruta.esPropia
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
                onCerrarSesionClick = { },
                modoOscuro = modoOscuro,
                onModoOscuroChange = onModoOscuroChange
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
                onRecetaClick = { receta -> navController.navigate(VerDetalleReceta(receta.id)) },
                onVolverClick = { navController.popBackStack() }
            )
        }
    }
}