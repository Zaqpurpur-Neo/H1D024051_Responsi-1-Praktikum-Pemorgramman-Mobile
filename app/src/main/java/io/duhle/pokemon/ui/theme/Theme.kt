package io.duhle.pokemon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PokemonDarkColorScheme = darkColorScheme(
    primary = Sage,
    onPrimary = Abyss,
    primaryContainer = Emerald,
    onPrimaryContainer = MintWhite,

    secondary = Emerald,
    onSecondary = MintWhite,
    secondaryContainer = Teal,
    onSecondaryContainer = MintWhite,

    background = Abyss,
    onBackground = MintWhite,

    surface = AbyssLigher,
    onSurface = MintWhiteDimmer,

    surfaceContainerLowest = AbyssDimmer,

    surfaceVariant = Teal,
    onSurfaceVariant = Sage,

    outline = Emerald,
)

@Composable
fun PokemonTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PokemonDarkColorScheme,
        typography = Typography,
        content = content
    )
}