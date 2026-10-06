package io.duhle.pokemon.data.repository

import io.duhle.pokemon.data.model.PokemonDetail
import io.duhle.pokemon.data.model.PokemonResultItem
import io.duhle.pokemon.data.model.PokemonResultItemParsed
import io.duhle.pokemon.data.model.PokemonResultResponse
import io.duhle.pokemon.data.network.ApiClient
import io.duhle.pokemon.data.network.PokemonApiInterface
import androidx.core.net.toUri
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

class PokemonRepository(
    private val api: PokemonApiInterface = ApiClient.pokemonApi
) {
    private companion object {
        const val PAGE_LIMIT = 40
        const val MAX_CONCURRENT_REQ = 5

        val detailPokemonCache = ConcurrentHashMap<Int, PokemonDetail>()
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        val cached = detailPokemonCache[id]
        if(cached != null) return cached

        val detail = api.getPokemonItem(id)
        detailPokemonCache[id] = detail
        return detail
    }

    suspend fun getAllPokemonResponse(): PokemonResultResponse = api.getListOfPokemon(PAGE_LIMIT)
    suspend fun getPokemonItemOnly(): List<PokemonResultItem> = getAllPokemonResponse().results

    private fun parseIdFromURL(url: String): Int {
        return url.toUri().lastPathSegment?.toIntOrNull() ?: -1
    }

    private fun getImageUrlOfficialArtworkYangFrontDefaultAjalah(pokemonDetail: PokemonDetail): String? {
        return pokemonDetail.sprites.other?.officialArtwork?.frontDefault ?: pokemonDetail.sprites.frontDefault
    }

    suspend fun getPokemonItemResultParsed(): List<PokemonResultItemParsed> {
        return coroutineScope {
            val items = getPokemonItemOnly()
            val semaphore = Semaphore(MAX_CONCURRENT_REQ)

            val task = items.map { item ->
                async {
                    semaphore.withPermit {
                        try {
                            val id = parseIdFromURL(item.url)
                            val detail = getPokemonDetail(id)

                            PokemonResultItemParsed(
                                pokemonId = detail.id,
                                name = detail.name,
                                imageUrl = getImageUrlOfficialArtworkYangFrontDefaultAjalah(detail)
                            )
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
            }

            val result = task.awaitAll()
            val parsedList = result.filterNotNull()

            if(parsedList.isEmpty() && items.isEmpty()) {
                throw IOException("Duh Le gagal nge fetch data pokemon, skill issue apa skill issue")
            }

            parsedList
        }
    }
}