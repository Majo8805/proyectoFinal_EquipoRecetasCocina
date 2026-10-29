package equipo.recetario.saborio.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import equipo.recetario.saborio.model.data.getRecetasSecretas
import equipo.recetario.saborio.model.data.listaRecetas
import equipo.recetario.saborio.model.domain.Usuario
import equipo.recetario.saborio.view.screens.EditarPerfilScreen
import equipo.recetario.saborio.view.screens.PantallaAgregarEditarReceta
import equipo.recetario.saborio.view.screens.PerfilScreen
import equipo.recetario.saborio.view.screens.PrincipalScreen
import equipo.recetario.saborio.view.screens.RecetasSecretasScreen
import equipo.recetario.saborio.view.screens.detalleRecetaScreen
import equipo.recetario.saborio.view.screens.loginScreen
import equipo.recetario.saborio.view.screens.registroScreen
import equipo.recetario.saborio.viewModel.RecetasViewModel

@Composable
fun AppNavHost(
    usuario: Usuario, modoOscuro: Boolean = false, onModoOscuroChange: (Boolean) -> Unit = {}
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = VerLogin) {
        composable<VerLogin> {
            loginScreen(
                onLoginClick = {
                    navController.navigate(VerMisRecetas) {
                        popUpTo<VerLogin> { inclusive = true }
                    }
                },
                onNavigateRegistro = {
                    navController.navigate(VerRegistro)
                }
            )
        }
        composable<VerMisRecetas> {
            PrincipalScreen(usuario, "Inicio", onNavigateToInicio = { }, onNavigateToExplorar = {
                navController.navigate(VerExplorar)
            }, onNavigateToGuardadas = {
                navController.navigate(VerGuardadas)
            }, onNavigateToPerfil = {
                navController.navigate(VerPerfil)
            }, onNavigateToAgregarReceta = {
                navController.navigate(VerAgregarEditarReceta())
            }, onRecetaClick = { receta ->
                navController.navigate(
                    VerDetalleReceta(receta.id, true)
                )
            })
        }
        composable<VerExplorar> {
            PrincipalScreen(
                usuario,
                "Explorar",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { navController.navigate(VerPerfil) },
                onNavigateToAgregarReceta = { navController.navigate(VerAgregarEditarReceta()) },
                onRecetaClick = { receta ->
                    navController.navigate(
                        VerDetalleReceta(receta.id, false)
                    )
                })
        }
        composable<VerGuardadas> {
            PrincipalScreen(
                usuario,
                "Guardadas",
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = { },
                onNavigateToPerfil = { navController.navigate(VerPerfil) },
                onNavigateToAgregarReceta = { navController.navigate(VerAgregarEditarReceta()) },
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

            val recetasViewModel: RecetasViewModel = viewModel()

            detalleRecetaScreen(
                usuario = usuario,
                receta = receta,
                esPropia = ruta.esPropia,
                viewModel = recetasViewModel,
                onEditarClick = { recetaId ->
                    navController.navigate(VerAgregarEditarReceta(recetaId = recetaId, esEdicion = true))
                }
            )
        }
        composable<VerPerfil> {
            PerfilScreen(
                usuario,
                onNavigateToInicio = { navController.navigate(VerMisRecetas) },
                onNavigateToExplorar = { navController.navigate(VerExplorar) },
                onNavigateToGuardadas = { navController.navigate(VerGuardadas) },
                onNavigateToPerfil = { },
                onNavigateToAgregarReceta = { navController.navigate(VerAgregarEditarReceta()) },
                onEditarClick = { navController.navigate(VerEditarPerfil) },
                onDesbloquearClick = { navController.navigate(VerRecetasSecretas) },
                onCerrarSesionClick = { },
                modoOscuro = modoOscuro,
                onModoOscuroChange = onModoOscuroChange
            )
        }
        composable<VerAgregarEditarReceta> { backStackEntry ->
            val ruta = backStackEntry.toRoute<VerAgregarEditarReceta>()
            PantallaAgregarEditarReceta(
                usuario = usuario,
                recetaId = ruta.recetaId,
                esEdicion = ruta.esEdicion,
                onVolverClick = { navController.popBackStack() },
                onGuardarExitoso = { navController.popBackStack() },
                onNavigateToPerfil = { navController.navigate(VerPerfil) }
            )
        }
        composable<VerEditarPerfil> {
            EditarPerfilScreen(
                usuario,
                onGuardarClick = { navController.popBackStack() },
                onCancelarClick = { navController.popBackStack() })
        }
        composable<VerRecetasSecretas> {
            RecetasSecretasScreen(
                recetas = getRecetasSecretas(usuario.nombre),
                onRecetaClick = { receta ->
                    navController.navigate(
                        VerDetalleReceta(
                            receta.id,
                            false
                        )
                    )
                },
                onVolverClick = { navController.popBackStack() })
        }

        composable<VerRegistro> {

            registroScreen(
                onRegistroExitoso = {
                    navController.navigate(VerMisRecetas) {
                        popUpTo<VerRegistro> { inclusive = true }
                    }
                },
                onNavigateLogin = { navController.navigate(VerLogin) }
            )
        }

    }
}
