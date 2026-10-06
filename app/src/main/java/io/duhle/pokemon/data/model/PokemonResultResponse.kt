package io.duhle.pokemon.data.model

data class PokemonResultResponse(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResultItem>,
)

data class PokemonResultItem(
    val name: String,
    val url: String,
)

// masakin dulu le
data class PokemonResultItemParsed(
    val pokemonId: Int,
    val name: String,
    val imageUrl: String?
)