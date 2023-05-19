package jp.matsuura.pokemon.repository

import jp.matsuura.pokemon.api.PokemonApi
import jp.matsuura.pokemon.api.entity.PokemonDetailEntity
import jp.matsuura.pokemon.api.entity.PokemonEntity
import jp.matsuura.pokemon.api.entity.PokemonEvolutionEntity
import jp.matsuura.pokemon.api.entity.PokemonSpeciesEntity
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
        )
    }

    suspend fun getPokemonDetail(pokemonId: Int): PokemonDetailEntity {
        return pokemonApi.getPokemonDetail(
            pokemonId = pokemonId,
        )
    }

    suspend fun getPokemonSpecies(pokemonId: Int): PokemonSpeciesEntity {
        return pokemonApi.getPokemonSpecies(
            pokemonId = pokemonId
        )
    }

    suspend fun getPokemonEvolution(id: Int): PokemonEvolutionEntity {
        return pokemonApi.getEvolutionChain(
            chainId = id,
        )
    }

    suspend fun getPokemonJaName(pokemonId: Int): String {
        val names = pokemonApi.getPokemonSpecies(pokemonId = pokemonId).names
        return names.find { it.language.name == "ja" }?.name ?: throw IllegalStateException()
    }

}