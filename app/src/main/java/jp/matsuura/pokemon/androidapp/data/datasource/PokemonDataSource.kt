package jp.matsuura.pokemon.androidapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.matsuura.pokemon.androidapp.data.api.PokemonApi
import jp.matsuura.pokemon.androidapp.data.entity.PokemonInfoEntity
import jp.matsuura.pokemon.androidapp.ext.requireBody

class PokemonDataSource(
    private val pokemonApi: PokemonApi,
) : PagingSource<Int, PokemonInfoEntity>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonInfoEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonInfoEntity> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val offset = if (params.key != null) {
            ((position - 1) * PAGE_SIZE) + 1
        } else {
            STARTING_PAGE_INDEX
        }
        return try {
            val resp = pokemonApi.getPokemonList(offset = offset, limit = params.loadSize).requireBody()
            val nextKey = if (resp.pokemonList.isEmpty()) {
                null
            } else {
                position + (params.loadSize / PAGE_SIZE)
            }
            LoadResult.Page(
                data = resp.pokemonList,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
        private const val STARTING_PAGE_INDEX = 0
    }
}
