package io.duhle.pokemon.data.network

import io.duhle.pokemon.data.model.PokemonDetail
import io.duhle.pokemon.data.model.PokemonResultResponse
import io.duhle.pokemon.utility.PokemonConstant.POKEAPI_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiInterface {
    @GET("pokemon")
    suspend fun getListOfPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): PokemonResultResponse

    @GET("pokemon/{itemId}")
    suspend fun getPokemonItem(
        @Path("itemId") itemId: Int
    ): PokemonDetail
}

object ApiClient {
    val pokemonApi: PokemonApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(POKEAPI_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiInterface::class.java)
    }
}