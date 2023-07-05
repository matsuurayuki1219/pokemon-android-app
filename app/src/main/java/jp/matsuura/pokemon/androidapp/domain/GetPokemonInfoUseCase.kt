package jp.matsuura.pokemon.androidapp.domain

import androidx.paging.PagingData
import androidx.paging.map
import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import jp.matsuura.pokemon.androidapp.ext.extractLastPathFromUrl
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    operator fun invoke(): Flow<PagingData<PokemonModel>> {
        return pokemonRepository.getPokemonInfo().map { pagingData ->
            pagingData.map {
                val pokemonId = it.url.extractLastPathFromUrl()
                val enName = it.name
                val imageUrl = pokemonRepository.getPokemonDetail(
                    pokemonId = pokemonId,
                ).sprites.other.officialArtwork.frontDefault
                PokemonModel(
                    id = pokemonId.toString(),
                    enName = enName,
                    imageUrl = imageUrl,
                )
            }
        }
    }
}
