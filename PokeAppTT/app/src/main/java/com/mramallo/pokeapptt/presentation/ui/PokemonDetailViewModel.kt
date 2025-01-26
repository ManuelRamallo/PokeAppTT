package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
): ViewModel() {

    /*
    * This mapOf is necessary because without it and without assigning a UUID to each element,
    * all images would be of the same pokemon and would not load correctly.*/
    var statePokemonDetail by mutableStateOf(PokemonDetailState())
        private set

    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            statePokemonDetail = PokemonDetailState(loading = true)
            delay(1000) // This is not funcional, is only for the demo
            statePokemonDetail = PokemonDetailState(pokemonDetail = getPokemonDetailUseCase.invoke(name))
        }
    }

    data class PokemonDetailState(
        val pokemonDetail: PokemonDetail? = null,
        val loading: Boolean = false
    )
}