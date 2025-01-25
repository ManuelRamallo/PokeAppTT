package com.mramallo.pokeapptt.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    // TODO - ESTA ES MI FORMA
    // TODO - PERO ESTÁ REPITIENDO LOS ELEMENTOS
    /*var state by mutableStateOf(PokemonListState())
        private set

    fun getPokemonList() {
        //getPokemonListUseCase().flow.cachedIn(viewModelScope)
        viewModelScope.launch {
            state = PokemonListState(loading = true)
            state = PokemonListState(pokemonList = getPokemonListUseCase.invoke().flow.cachedIn(viewModelScope))
        }
    }


    data class PokemonListState(
        val pokemonList: Flow<PagingData<PokemonResult>>? = null,
        val loading: Boolean = false
    )*/

    // TODO - ESTA ES MI FORMA
    val pokemonList = getPokemonListUseCase.invoke().flow.cachedIn(viewModelScope)
}