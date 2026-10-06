package io.duhle.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.duhle.pokemon.data.model.PokemonDetail
import io.duhle.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PokemonDetailUIState {
    object Loading: PokemonDetailUIState

    data class Success(val pokemonDetail: PokemonDetail): PokemonDetailUIState

    data class Error(val message: String): PokemonDetailUIState
}

class PokemonDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonDetailUIState>(PokemonDetailUIState.Loading)
    val uiState: StateFlow<PokemonDetailUIState> = _uiState.asStateFlow()

    private val repos = PokemonRepository()

    fun loadDoksliPokemonItem(id: Int) {
        viewModelScope.launch {
            _uiState.value = PokemonDetailUIState.Loading
            try {
                _uiState.value = PokemonDetailUIState.Success(pokemonDetail = repos.getPokemonDetail(id))
            } catch (e: Exception) {
                _uiState.value = PokemonDetailUIState.Error("Duh Le: gagal fetch load data = ${e.localizedMessage}")
            }
        }
    }

}