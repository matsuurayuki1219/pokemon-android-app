package jp.matsuura.pokemon.domain

import jp.matsuura.pokemon.domain.converter.toModel
import jp.matsuura.pokemon.api.entity.generated.Chain
import jp.matsuura.pokemon.api.entity.generated.SpeciesXXX
import jp.matsuura.pokemon.model.PokemonDetailModel
import jp.matsuura.pokemon.model.PokemonEvolutionModel
import jp.matsuura.pokemon.repository.PokemonRepository
import timber.log.Timber
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
        val imageUri = pokemon.sprites.other?.official_artwork?.front_default
            ?: throw IllegalStateException("image url is not found.")
        val weight = pokemon.weight
        val height = pokemon.height
        val types = pokemon.types.map {
            it.toModel()
        }

        // FIXME: should be refactored later.
        val evolutionChainId = pokemonRepository.getPokemonSpecies(
            pokemonId = pokemonId
        ).evolution_chain.url
            .replace("https://pokeapi.co/api/v2/evolution-chain/", "")
            .replace("/","")
            .toInt()

        val evolutionInfo = pokemonRepository.getPokemonEvolution(
            id = evolutionChainId
        ).chain

        val a = flattenEvolutionChain(evolutionChain = evolutionInfo).map {
            val id = it.url.replace("https://pokeapi.co/api/v2/pokemon-species/", "").replace("/", "").toInt()
            val jaName = pokemonRepository.getPokemonJaName(pokemonId = id)
            PokemonEvolutionModel(
                id = id,
                enName = it.name,
                jaName = jaName,
                imageUrl = pokemonRepository.getPokemonDetail(pokemonId = id).sprites.other?.official_artwork?.front_default ?: throw IllegalStateException()
            )
        }

        Timber.d(a.toString())
        return PokemonDetailModel(
            id = pokemonId,
            enName= enName,
            jaName = jaName,
            imageUrl = imageUri,
            types = types,
            weight = weight,
            height = height,
            evolutions = a,
        )
    }

    fun flattenEvolutionChain(evolutionChain: Chain): List<SpeciesXXX> {
        val speciesList = mutableListOf<SpeciesXXX>()
        speciesList.add(evolutionChain.species)

        for (evolvesTo in evolutionChain.evolves_to) {
            speciesList.addAll(flattenEvolutionChain(evolvesTo))
        }

        return speciesList
    }

}