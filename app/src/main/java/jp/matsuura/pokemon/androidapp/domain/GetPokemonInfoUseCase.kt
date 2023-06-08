package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import jp.matsuura.pokemon.androidapp.ext.extractPokemonId
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
            val pokemonId = it.url.extractPokemonId()
            val enName = it.name
            val jaName = pokemonRepository.getPokemonJaName(pokemonId = pokemonId.toInt())
            val imageUrl = pokemonRepository.getPokemonDetail(
                pokemonId = pokemonId.toInt(),
            ).sprites.other?.official_artwork?.front_default
            PokemonModel(
                id = pokemonId,
                enName = enName,
                jaName = jaName,
                imageUrl = imageUrl ?: "",
            )
        }
    }
}