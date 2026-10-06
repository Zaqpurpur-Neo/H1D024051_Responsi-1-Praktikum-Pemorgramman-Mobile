package io.duhle.pokemon.ui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.duhle.pokemon.data.model.PokemonDetail
import io.duhle.pokemon.ui.viewmodel.PokemonDetailUIState
import io.duhle.pokemon.ui.viewmodel.PokemonDetailViewModel

@Composable
fun TextBerjarakGakTauNamaAslinya(label: String, isinya: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
        Text(
            text = isinya,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}

private fun agakDikapitalin(str: String) = str.replaceFirstChar {
    if(it.isLowerCase()) it.titlecase() else it.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDetailPokemon(
    pokemonDetail: PokemonDetail,
    onBackClick: () -> Unit
) {
    val nameCap = agakDikapitalin(pokemonDetail.name)
    val iniGambar = pokemonDetail.sprites.other?.officialArtwork?.frontDefault ?: pokemonDetail.sprites.frontDefault
    val pokemonTypesss = pokemonDetail.types.joinToString(separator = ", ") { agakDikapitalin(it.type.name) }
    val pokemonStatistick = pokemonDetail.stats

    val density = LocalDensity.current
    val floatDistancePx = with(density) { 12.dp.toPx() }
    val naikTurunMendatMendut = rememberInfiniteTransition(label = "PokemonAgakMelayangDikit")

    val offsetY by naikTurunMendatMendut.animateFloat(
        initialValue = -floatDistancePx,
        targetValue = floatDistancePx,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PokemonOffsetY::NaikTurunMendatMendut"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pokemon") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Icon::Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.onSurface)
                .verticalScroll(state = rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) {
                coil3.compose.AsyncImage(
                    model = iniGambar,
                    contentDescription = nameCap,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .graphicsLayer {
                            translationY = offsetY
                        }

                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    ))
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(vertical = 16.dp, horizontal = 16.dp)

            ) {
                Text(
                    text = nameCap,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 42.sp
                )
                Text(
                    text = "Pokemon ID #${pokemonDetail.id}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 2.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )

                LazyColumn {
                    item {
                        TextBerjarakGakTauNamaAslinya(
                            label = "Berat",
                            isinya = "${pokemonDetail.weight} Kg"
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        TextBerjarakGakTauNamaAslinya(
                            label = "Tinggi",
                            isinya = "${pokemonDetail.height} cm"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        TextBerjarakGakTauNamaAslinya(
                            label = "Tipe",
                            isinya = "${pokemonTypesss}"
                        )
                    }

                    items(items = pokemonStatistick) { item ->
                        Spacer(modifier = Modifier.height(12.dp))
                        TextBerjarakGakTauNamaAslinya(
                            label = agakDikapitalin(item.stat.name.replace("-", " ")),
                            isinya = "${item.baseStat}"
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPokemonScreen(
    navController: NavController? = null,
    pokemonId: Int,
    viewModel: PokemonDetailViewModel = viewModel()
) {
    LaunchedEffect(pokemonId) {
        viewModel.loadDoksliPokemonItem(pokemonId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is PokemonDetailUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is PokemonDetailUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
            }
        }
        is PokemonDetailUIState.Success -> {
            val pokemonItem = state.pokemonDetail

            if (pokemonItem == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Duh le gak ketemu")
                }
            } else {
                StatelessDetailPokemon(
                    pokemonDetail = pokemonItem,
                    onBackClick = { navController?.popBackStack() }
                )
            }
        }
    }
}