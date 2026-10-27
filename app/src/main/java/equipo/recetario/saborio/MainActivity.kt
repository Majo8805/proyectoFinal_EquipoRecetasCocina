package equipo.recetario.saborio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.navigation.AppNavHost
import equipo.recetario.saborio.ui.theme.SaborioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaborioTheme {
                AppNavHost(usuarioMuestra)
            }
        }
    }
}