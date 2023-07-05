package jp.matsuura.pokemon.androidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.androidapp.domain.GetPokemonInfoUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPokemonInfo: GetPokemonInfoUseCase,
) : ViewModel() {

    val pokemon = getPokemonInfo().cachedIn(viewModelScope)
}
