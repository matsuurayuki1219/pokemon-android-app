package jp.matsuura.pokemon.androidapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jp.matsuura.pokemon.androidapp.data.api.PokemonApi
import jp.matsuura.pokemon.androidapp.data.datasource.PokemonDataSource
import jp.matsuura.pokemon.androidapp.data.datasource.PokemonDataSource.Companion.PAGE_SIZE
import jp.matsuura.pokemon.androidapp.data.entity.*
import jp.matsuura.pokemon.androidapp.ext.requireBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
) {

    fun getPokemonInfo(): Flow<PagingData<PokemonInfoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PokemonDataSource(pokemonApi)
            },
        ).flow
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
