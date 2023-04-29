package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
){
    suspend operator fun invoke(): List<PokemonModel> {
        val pokemonList = pokemonRepository.getPokemonInfo()
        return pokemonList.results.map {
            val pokemonId = "2"
            val name = it.name
            val imageUrl = pokemonRepository.getPokemonDetail(
                pokemonId = pokemonId.toInt(),
            ).sprites.front_default
            PokemonModel(
                id = pokemonId,
                name = name,
                imageUrl,
            )
        }
    }
}