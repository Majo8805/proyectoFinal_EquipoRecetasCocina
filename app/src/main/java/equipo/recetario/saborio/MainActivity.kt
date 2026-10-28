package equipo.recetario.saborio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import equipo.recetario.saborio.model.data.usuarioMuestra
import equipo.recetario.saborio.navigation.AppNavHost
import equipo.recetario.saborio.ui.theme.SaborioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var modoOscuro by rememberSaveable { mutableStateOf(false) }
            SaborioTheme(darkTheme = modoOscuro) {
                AppNavHost(
                    usuario = usuarioMuestra,
                    modoOscuro = modoOscuro,
                    onModoOscuroChange = { modoOscuro = it }
                )
            }
        }
    }
}