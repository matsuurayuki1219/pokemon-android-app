package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.converter.toModel
import jp.matsuura.pokemon.androidapp.data.entity.ChainEntity
import jp.matsuura.pokemon.androidapp.data.entity.SpeciesDetailEntity
import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import jp.matsuura.pokemon.androidapp.ext.extractLastPathFromUrl
import jp.matsuura.pokemon.androidapp.model.PokemonDetailModel
import jp.matsuura.pokemon.androidapp.model.PokemonEvolutionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonDetailInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(pokemonId: Int): PokemonDetailModel {
        val pokemon = pokemonRepository.getPokemonDetail(pokemonId = pokemonId)
        val enName = pokemon.name
        val imageUri = pokemon.sprites.other.officialArtwork.frontDefault
        val weight = pokemon.weight
        val height = pokemon.height
        val types = pokemon.types.map { it.toModel() }
        val evolutionChainId = pokemonRepository.getPokemonSpecies(
            pokemonId = pokemonId,
        ).evolutionChain.url.extractLastPathFromUrl()
        val evolutionInfo = pokemonRepository.getPokemonEvolution(
            id = evolutionChainId,
        ).chain
        val evolutions = flattenEvolutionChain(evolutionChain = evolutionInfo).map {
            val id = it.url.extractLastPathFromUrl()
            PokemonEvolutionModel(
                id = id,
                enName = it.name,
                imageUrl = pokemonRepository.getPokemonDetail(
                    pokemonId = id,
                ).sprites.other.officialArtwork.frontDefault,
            )
        }
        return PokemonDetailModel(
            id = pokemonId,
            enName = enName,
            imageUrl = imageUri,
            types = types,
            weight = weight,
            height = height,
            evolutions = evolutions,
        )
    }

    private fun flattenEvolutionChain(evolutionChain: ChainEntity): List<SpeciesDetailEntity> {
        val speciesList = mutableListOf<SpeciesDetailEntity>()
        speciesList.add(evolutionChain.species)
        for (evolvesTo in evolutionChain.evolvesTo) {
            speciesList.addAll(flattenEvolutionChain(evolvesTo))
        }
        return speciesList
    }
}
