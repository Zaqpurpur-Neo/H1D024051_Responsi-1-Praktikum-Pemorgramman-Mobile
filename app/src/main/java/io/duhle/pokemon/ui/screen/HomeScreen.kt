package io.duhle.pokemon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.duhle.pokemon.R
import io.duhle.pokemon.data.model.PokemonResultItemParsed
import io.duhle.pokemon.ui.viewmodel.PokemonUIState
import io.duhle.pokemon.ui.viewmodel.PokemonViewModel
import java.util.Locale

@Composable
fun MyOwnSearchBar(
    textQuery: String,
    onTextQueryChange: (String) ->  Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Cari Pokemonnya"
) {
    val containerColor = MaterialTheme.colorScheme.surfaceContainerLowest

    TextField(
        value = textQuery,
        modifier = modifier,
        onValueChange = onTextQueryChange,
        placeholder = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSurface) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search::Icon",
                modifier = Modifier.padding(16.dp)
            )
        },
        trailingIcon = { if(textQuery.isNotEmpty()) {
            IconButton(onClick = { onTextQueryChange("") }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Search::Clear")
            }
        }},
        shape = CircleShape,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search) // ngubah tombol enter jadi search le
    )
}

@Composable
fun PokemonItemCard(pokemonItem: PokemonResultItemParsed, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().clipToBounds()
        ) {

            coil3.compose.AsyncImage(
                model = pokemonItem.imageUrl,
                contentScale = ContentScale.Fit,
                contentDescription = pokemonItem.name,
                modifier = Modifier
                    .matchParentSize()
                    .aspectRatio(1f)
                    .scale(0.8f)
                    .align(Alignment.BottomEnd)
                    .offset(x=70.dp, y=35.dp)
                    .alpha(0.1f)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.CatchingPokemon,
                        contentDescription = "Search::Pokemon",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Column {

                        Text(
                            text = pokemonItem.name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 32.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(1.dp))


                        Text(
                            text = "Pokemon ID #${pokemonItem.pokemonId}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f),
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                    }

                }

                coil3.compose.AsyncImage(
                    model = pokemonItem.imageUrl,
                    contentDescription = pokemonItem.name,
                    modifier = Modifier.size(120.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDaftarPokemon(
    searchQuery: String, onSearchQueryChange: (String) -> Unit,
    onCardClick: (PokemonResultItemParsed) -> Unit,
    isLoading: Boolean,
    pokemonItems: List<PokemonResultItemParsed>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CatchingPokemon,
                            contentDescription = "Icon::Pokemon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Pokemon Dex", fontWeight = FontWeight.SemiBold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
        ) {
            MyOwnSearchBar(
                textQuery = searchQuery,
                onTextQueryChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp)
            )

            Text(
                text = "Pokemon List",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )

            if(isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Sabar Loading dikit")
                    }
                }
            } else {
                if (pokemonItems.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.duh_le),
                            contentDescription = "DuhLe::gambar"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Duh Le, Pokemon tidak ditemukan.")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(items = pokemonItems) { item ->
                            PokemonItemCard(
                                pokemonItem = item,
                                onClick = { onCardClick(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController? = null, viewModel: PokemonViewModel = viewModel()) {
    val ctx = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    when(val state = uiState) {
        is PokemonUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is PokemonUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error ${state.message}", color = MaterialTheme.colorScheme.error)
            }
        }

        is PokemonUIState.Success -> {
            val pokemonItems = if (searchQuery.isBlank()) {
                state.pokemonItems
            } else {
                state.pokemonItems.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
            }

            StatelessDaftarPokemon(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onCardClick = { itemParsed ->
                    navController?.navigate(route = "detail/${itemParsed.pokemonId}")
                },
                isLoading = isLoading,
                pokemonItems = pokemonItems
            )
        }
    }
}