package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.runtime.mutableStateMapOf
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
    var state = mutableStateMapOf<String, PokemonDetailState>()

    fun getPokemonDetail(uuid: String, name: String) {
        viewModelScope.launch {
            state[uuid] = PokemonDetailState(loading = true)
            delay(1000) // This is not funcional, is only for the demo
            state[uuid] = PokemonDetailState(pokemonDetail = getPokemonDetailUseCase.invoke(name))
        }
    }

    data class PokemonDetailState(
        val pokemonDetail: PokemonDetail? = null,
        val loading: Boolean = false
    )
}