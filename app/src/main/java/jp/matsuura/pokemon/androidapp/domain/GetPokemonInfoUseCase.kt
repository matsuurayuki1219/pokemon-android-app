package jp.matsuura.pokemon.androidapp.domain

import jp.matsuura.pokemon.androidapp.data.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
){
    suspend operator fun invoke() {

    }
}