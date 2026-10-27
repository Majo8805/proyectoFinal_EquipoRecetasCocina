package equipo.recetario.saborio.utilities

import androidx.compose.ui.graphics.Color
import equipo.recetario.saborio.ui.theme.BebidaBg
import equipo.recetario.saborio.ui.theme.CenaBg
import equipo.recetario.saborio.ui.theme.ComidaBg
import equipo.recetario.saborio.ui.theme.CustomBg
import equipo.recetario.saborio.ui.theme.DarkText
import equipo.recetario.saborio.ui.theme.DesayunoBg
import equipo.recetario.saborio.ui.theme.EnsaladaBg
import equipo.recetario.saborio.ui.theme.PanaderiaBg
import equipo.recetario.saborio.ui.theme.PostreBg
import equipo.recetario.saborio.ui.theme.SaludableBg
import equipo.recetario.saborio.ui.theme.SnackBg
import equipo.recetario.saborio.ui.theme.VegetarianaBg
import equipo.recetario.saborio.ui.theme.WhiteText

fun getColorByEtiqueta(etiqueta: String): Pair<Color, Color> {
    return when (etiqueta) {
        EtiquetasPredeterminadas.DESAYUNO -> Pair(DesayunoBg, WhiteText)
        EtiquetasPredeterminadas.COMIDA -> Pair(ComidaBg, WhiteText)
        EtiquetasPredeterminadas.CENA -> Pair(CenaBg, WhiteText)
        EtiquetasPredeterminadas.POSTRE -> Pair(PostreBg, WhiteText)
        EtiquetasPredeterminadas.SNACK -> Pair(SnackBg, DarkText)
        EtiquetasPredeterminadas.SALUDABLE -> Pair(SaludableBg, WhiteText)
        EtiquetasPredeterminadas.VEGETARIANA -> Pair(VegetarianaBg, WhiteText)
        EtiquetasPredeterminadas.BEBIDA -> Pair(BebidaBg, WhiteText)
        EtiquetasPredeterminadas.ENSALADA -> Pair(EnsaladaBg, WhiteText)
        EtiquetasPredeterminadas.PANADERIA -> Pair(PanaderiaBg, WhiteText)
        else -> Pair(CustomBg, DarkText)
    }
}