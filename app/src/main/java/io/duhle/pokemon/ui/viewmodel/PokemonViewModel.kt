package io.duhle.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.duhle.pokemon.data.model.PokemonResultItemParsed
import io.duhle.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PokemonUIState {
    object Loading: PokemonUIState

    data class Success(val pokemonItems: List<PokemonResultItemParsed>): PokemonUIState

    data class Error(val message: String): PokemonUIState
}

class PokemonViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonUIState>(PokemonUIState.Loading)
    val uiState: StateFlow<PokemonUIState> = _uiState.asStateFlow()

    private val repos = PokemonRepository()

    init {
        ngambilDoksliDariApi()
    }

    private fun ngambilDoksliDariApi() {
        viewModelScope.launch {
            _uiState.value = PokemonUIState.Loading
            try {
                _uiState.value = PokemonUIState.Success(pokemonItems = repos.getPokemonItemResultParsed())
            } catch (e: Exception) {
                _uiState.value = PokemonUIState.Error("Duh Le: gagal fetch load data = ${e.localizedMessage}")
            }
        }
    }
}