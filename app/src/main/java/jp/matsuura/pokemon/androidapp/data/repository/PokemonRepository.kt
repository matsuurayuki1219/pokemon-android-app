package jp.matsuura.pokemon.androidapp.data.repository

import jp.matsuura.pokemon.androidapp.data.api.PokemonApi
import jp.matsuura.pokemon.androidapp.data.entity.PokemonDetailEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonEvolutionEntity
import jp.matsuura.pokemon.androidapp.data.entity.PokemonSpeciesEntity
import jp.matsuura.pokemon.androidapp.ext.requireBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        return withContext(Dispatchers.IO) {
            pokemonApi.getPokemonList(
                offset = offset,
                limit = limit,
            ).requireBody()
        }
    }

    suspend fun getPokemonDetail(pokemonId: Int): PokemonDetailEntity {
        return withContext(Dispatchers.IO) {
            pokemonApi.getPokemonDetail(
                pokemonId = pokemonId,
            ).requireBody()
        }
    }

    suspend fun getPokemonSpecies(pokemonId: Int): PokemonSpeciesEntity {
        return withContext(Dispatchers.IO) {
            pokemonApi.getPokemonSpecies(
                pokemonId = pokemonId,
            ).requireBody()
        }
    }

    suspend fun getPokemonEvolution(id: Int): PokemonEvolutionEntity {
        return withContext(Dispatchers.IO) {
            pokemonApi.getEvolutionChain(
                chainId = id,
            ).requireBody()
        }
    }

    suspend fun getPokemonJaName(pokemonId: Int): String {
        return withContext(Dispatchers.IO) {
            val names = pokemonApi.getPokemonSpecies(pokemonId = pokemonId).requireBody().names
            names.find { it.language.name == "ja" }?.name ?: throw IllegalStateException()
        }
    }
}
