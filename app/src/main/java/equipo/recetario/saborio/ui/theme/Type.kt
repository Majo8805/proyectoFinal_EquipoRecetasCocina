package equipo.recetario.saborio.ui.theme

import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import equipo.recetario.saborio.R

import androidx.compose.ui.text.TextStyle

val Epilogue = FontFamily(
    Font(R.font.epilogue_regular, FontWeight.Normal),
    Font(R.font.epilogue_semibold, FontWeight.SemiBold),
    Font(R.font.epilogue_bold, FontWeight.Bold),
)

val PlusJakartaSans = FontFamily(
    Font(R.font.plusjakartasans_regular, FontWeight.Normal),
    Font(R.font.plusjakartasans_semibold, FontWeight.SemiBold),
    Font(R.font.plusjakartasans_bold, FontWeight.Bold),
)

val Typography = MaterialTypography(
    displayLarge = TextStyle(fontFamily = PlusJakartaSans),
    displayMedium = TextStyle(fontFamily = PlusJakartaSans),
    displaySmall = TextStyle(fontFamily = PlusJakartaSans),
    headlineLarge = TextStyle(fontFamily = PlusJakartaSans),
    headlineMedium = TextStyle(fontFamily = PlusJakartaSans),
    headlineSmall = TextStyle(fontFamily = PlusJakartaSans),
    titleLarge = TextStyle(fontFamily = PlusJakartaSans),
    titleMedium = TextStyle(fontFamily = PlusJakartaSans),
    titleSmall = TextStyle(fontFamily = PlusJakartaSans),
    bodyLarge = TextStyle(fontFamily = PlusJakartaSans),
    bodyMedium = TextStyle(fontFamily = PlusJakartaSans),
    bodySmall = TextStyle(fontFamily = PlusJakartaSans),
    labelLarge = TextStyle(fontFamily = PlusJakartaSans),
    labelMedium = TextStyle(fontFamily = PlusJakartaSans),
    labelSmall = TextStyle(fontFamily = PlusJakartaSans)
)
