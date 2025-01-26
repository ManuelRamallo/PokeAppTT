package com.mramallo.pokeapptt.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.usecase.GetPokemonDetailUseCase
import com.mramallo.pokeapptt.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
): ViewModel() {

    var statePokemonList by mutableStateOf(PokemonListState())
        private set

    /*
    * This mapOf is necessary because without it and without assigning a UUID to each element,
    * all images would be of the same pokemon and would not load correctly.*/
    var statePokemonElement = mutableStateMapOf<String, PokemonElementState>()
        private set

    fun getPokemonList() {
        viewModelScope.launch {
            statePokemonList = PokemonListState(loading = true)
            //delay(2000) // This is not funcional, is only for the demo TODO - ACTIVAR ESTO
            statePokemonList = PokemonListState(pokemonList = getPokemonListUseCase.invoke().flow.cachedIn(viewModelScope))
        }
    }

    fun getPokemonElement(uuid: String, name: String) {
        viewModelScope.launch {
            statePokemonElement[uuid] = PokemonElementState(loading = true)
            delay(1000) // This is not funcional, is only for the demo
            statePokemonElement[uuid] = PokemonElementState(pokemonDetail = getPokemonDetailUseCase.invoke(name))
        }
    }


    data class PokemonListState(
        val pokemonList: Flow<PagingData<PokemonResult>>? = null,
        val loading: Boolean = false
    )

    data class PokemonElementState(
        val pokemonDetail: PokemonDetail? = null,
        val loading: Boolean = false
    )
}