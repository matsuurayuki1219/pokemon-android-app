package jp.matsuura.pokemon.androidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.androidapp.domain.GetPokemonInfoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonInfo: GetPokemonInfoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeScreenState.initValue()
    )
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: Channel<HomeScreenEvent> = Channel()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            kotlin.runCatching {
                getPokemonInfo()
            }.onSuccess {
                _uiState.value = _uiState.value.copy(pokemonList = it)
            }.onFailure {
                if (it is IOException) {
                    _uiEvent.send(HomeScreenEvent.NetworkError)
                } else {
                    _uiEvent.send(HomeScreenEvent.UnknownError(error = it))
                }
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    fun onCardItemClicked(pokemonId: String) {
        viewModelScope.launch {
            _uiEvent.send(
                HomeScreenEvent.NavigateToDetail(
                    pokemonId = pokemonId.toInt(),
                )
            )
        }
    }

}