package jp.matsuura.pokemon.androidapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.androidapp.domain.GetPokemonInfoUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonInfo: GetPokemonInfoUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                getPokemonInfo()
            }.onSuccess {
                Timber.i(it.toString())
            }.onFailure {
                Timber.e(it)
            }
        }
    }

}