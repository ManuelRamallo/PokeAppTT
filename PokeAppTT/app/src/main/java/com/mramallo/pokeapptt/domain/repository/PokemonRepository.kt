package com.mramallo.pokeapptt.domain.repository

import androidx.paging.Pager
import com.mramallo.pokeapptt.domain.entity.PokemonResult

interface PokemonRepository {

    fun getPokemonList(): Pager<Int, PokemonResult>


}