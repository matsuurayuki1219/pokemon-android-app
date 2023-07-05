package jp.matsuura.pokemon.androidapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.matsuura.pokemon.androidapp.data.api.PokemonApi
import jp.matsuura.pokemon.androidapp.data.entity.PokemonInfoEntity
import jp.matsuura.pokemon.androidapp.ext.requireBody
import java.lang.Integer.max

class PokemonDataSource(
    private val pokemonApi: PokemonApi,
) : PagingSource<Int, PokemonInfoEntity>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonInfoEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonInfoEntity> {
        // Note: Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_PAGE_INDEX
        // Note: // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)
        return try {
            val resp = pokemonApi.getPokemonList(offset = range.first, limit = params.loadSize).requireBody()
            val nextKey = if (resp.pokemonList.isEmpty()) {
                null
            } else {
                range.last + 1
            }
            // Note: Make sure we don't try to load items behind the STARTING_KEY
            val prevKey = if (start == STARTING_PAGE_INDEX) {
                null
            } else {
                ensureValidKey(key = range.first - params.loadSize)
            }
            LoadResult.Page(
                data = resp.pokemonList,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX, key)

    companion object {
        const val PAGE_SIZE = 20
        const val MAX_CACHE_SIZE = 100
        private const val STARTING_PAGE_INDEX = 0
    }
}
