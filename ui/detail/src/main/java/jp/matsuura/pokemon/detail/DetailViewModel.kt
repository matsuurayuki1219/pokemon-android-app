package jp.matsuura.pokemon.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.domain.GetPokemonDetailInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val pokemonId: Int = requireNotNull(savedStateHandle["pokemonId"])

    private val _uiState = MutableStateFlow(DetailScreenState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<DetailScreenEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getPokemonDetailInfo()
    }

    private fun getPokemonDetailInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                getPokemonDetailUseCase(pokemonId = pokemonId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isProgressVisible = false,
                    pokemonInfo = it,
                )
            }.onFailure {
                Timber.d(it)
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                if (it is IOException) {
                    _uiEvent.emit(DetailScreenEvent.NetworkError)
                } else {
                    _uiEvent.emit(DetailScreenEvent.UnknownError(error = it))
                }
            }
        }
    }


}