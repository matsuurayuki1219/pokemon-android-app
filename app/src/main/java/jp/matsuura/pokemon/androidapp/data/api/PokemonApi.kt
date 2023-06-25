package jp.matsuura.pokemon.androidapp.data.api

import jp.matsuura.pokemon.androidapp.data.entity.PokemonDetailEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEvolutionEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonSpeciesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ): Response<PokemonEntity>

    @GET("api/v2/pokemon/{pokemonId}")
    suspend fun getPokemonDetail(
        @Path("pokemonId") pokemonId: Int,
    ): Response<PokemonDetailEntity>

    @GET("api/v2/pokemon-species/{pokemonId}")
    suspend fun getPokemonSpecies(
        @Path("pokemonId") pokemonId: Int,
    ): Response<PokemonSpeciesEntity>

    @GET("api/v2/evolution-chain/{chainId}")
    suspend fun getEvolutionChain(
        @Path("chainId") chainId: Int,
    ): Response<PokemonEvolutionEntity>
}
