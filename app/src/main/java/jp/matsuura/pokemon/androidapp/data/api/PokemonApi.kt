package jp.matsuura.pokemon.androidapp.data.api

import jp.matsuura.pokemon.androidapp.data.entity.PokemonListEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("/pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ): PokemonListEntity

}