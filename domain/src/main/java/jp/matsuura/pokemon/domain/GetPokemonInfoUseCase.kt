package jp.matsuura.pokemon.domain

import jp.matsuura.pokemon.model.PokemonModel
import jp.matsuura.pokemon.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
){
    suspend operator fun invoke(): List<PokemonModel> {
        val pokemonList = pokemonRepository.getPokemonInfo()
        return pokemonList.results.map {
            val pokemonId = it.url.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/", "")
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