package jp.matsuura.pokemon.androidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.androidapp.domain.GetPokemonInfoUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
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

    private val _uiEvent = MutableSharedFlow<HomeScreenEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            kotlin.runCatching {
                getPokemonInfo()
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    pokemonList = it,
                )
            }.onFailure {
                Timber.e(it)
                if (it is IOException) {
                    _uiEvent.emit(HomeScreenEvent.NetworkError)
                } else {
                    _uiEvent.emit(HomeScreenEvent.UnknownError(error = it))
                }
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

}