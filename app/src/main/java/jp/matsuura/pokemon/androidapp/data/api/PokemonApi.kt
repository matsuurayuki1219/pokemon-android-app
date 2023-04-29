package jp.matsuura.pokemon.androidapp.data.api

import jp.matsuura.pokemon.androidapp.data.entity.PokemonDetailEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ): PokemonEntity

    @GET("/pokemon/{pokemonId}")
    suspend fun getPokemonDetail(
        @Path("pokemonId") pokemonId: Int,
    ): PokemonDetailEntity

}