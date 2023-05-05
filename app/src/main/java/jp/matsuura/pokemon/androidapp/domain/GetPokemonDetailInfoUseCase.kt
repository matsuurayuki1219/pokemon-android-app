package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.converter.toModel
import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import jp.matsuura.pokemon.androidapp.model.PokemonDetailModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonDetailInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {

    suspend operator fun invoke(pokemonId: Int): PokemonDetailModel {
        val pokemon = pokemonRepository.getPokemonDetail(pokemonId = pokemonId)
        val id = pokemonId
        val name = pokemon.name
        val imageUri = pokemon.sprites.other?.official_artwork?.front_default
            ?: throw IllegalStateException("image url is not found.")
        val weight = pokemon.weight
        val height = pokemon.height
        val types = pokemon.types.map {
            it.toModel()
        }
        return PokemonDetailModel(
            id = id,
            name = name,
            imageUrl = imageUri,
            types = types,
            weight = weight,
            height = height,
        )
    }
}