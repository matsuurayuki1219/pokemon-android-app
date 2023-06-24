package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.converter.toModel
import jp.matsuura.pokemon.androidapp.data.entity.generated.Chain
import jp.matsuura.pokemon.androidapp.data.entity.generated.SpeciesXXX
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
        val jaName = pokemonRepository.getPokemonJaName(pokemonId = pokemonId)
        val imageUri = pokemon.sprites.other.official_artwork.front_default
        val weight = pokemon.weight
        val height = pokemon.height
        val types = pokemon.types.map { it.toModel() }
        val evolutionChainId = pokemonRepository.getPokemonSpecies(
            pokemonId = pokemonId
        ).evolution_chain.url.extractLastPathFromUrl()
        val evolutionInfo = pokemonRepository.getPokemonEvolution(
            id = evolutionChainId
        ).chain
        val evolutions = flattenEvolutionChain(evolutionChain = evolutionInfo).map {
            val id = it.url.extractLastPathFromUrl()
            val jaName = pokemonRepository.getPokemonJaName(pokemonId = id)
            PokemonEvolutionModel(
                id = id,
                enName = it.name,
                jaName = jaName,
                imageUrl = pokemonRepository.getPokemonDetail(
                    pokemonId = id,
                ).sprites.other.official_artwork.front_default
            )
        }
        return PokemonDetailModel(
            id = pokemonId,
            enName= enName,
            jaName = jaName,
            imageUrl = imageUri,
            types = types,
            weight = weight,
            height = height,
            evolutions = evolutions,
        )
    }

    private fun flattenEvolutionChain(evolutionChain: Chain): List<SpeciesXXX> {
        val speciesList = mutableListOf<SpeciesXXX>()
        speciesList.add(evolutionChain.species)
        for (evolvesTo in evolutionChain.evolves_to) {
            speciesList.addAll(flattenEvolutionChain(evolvesTo))
        }
        return speciesList
    }

}