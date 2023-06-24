package jp.matsuura.pokemon.androidapp.data.repository

import jp.matsuura.pokemon.androidapp.data.api.PokemonApi
import jp.matsuura.pokemon.androidapp.data.converter.toModel
import jp.matsuura.pokemon.androidapp.data.entity.PokemonDetailEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEvolutionEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonSpeciesEntity
import jp.matsuura.pokemon.androidapp.ext.requireBody
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
) {

    suspend fun getPokemonInfo(
        offset: Int? = null,
        limit: Int? = null,
    ): PokemonEntity {
        return pokemonApi.getPokemonList(
            offset = offset,
            limit = limit,
        ).requireBody()
    }

    suspend fun getPokemonDetail(pokemonId: Int): PokemonDetailEntity {
        return pokemonApi.getPokemonDetail(
            pokemonId = pokemonId,
        ).requireBody()
    }

    suspend fun getPokemonSpecies(pokemonId: Int): PokemonSpeciesEntity {
        return pokemonApi.getPokemonSpecies(
            pokemonId = pokemonId
        ).requireBody()
    }

    suspend fun getPokemonEvolution(id: Int): PokemonEvolutionEntity {
        return pokemonApi.getEvolutionChain(
            chainId = id,
        ).requireBody()
    }

    suspend fun getPokemonJaName(pokemonId: Int): String {
        val names = pokemonApi.getPokemonSpecies(pokemonId = pokemonId).requireBody().names
        return names.find { it.language.name == "ja" }?.name ?: throw IllegalStateException()
    }

}