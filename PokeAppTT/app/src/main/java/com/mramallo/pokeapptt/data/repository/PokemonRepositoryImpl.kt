package com.mramallo.pokeapptt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mramallo.pokeapptt.data.pagingSource.PokemonPagingSource
import com.mramallo.pokeapptt.data.remote.api.PokemonApi
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
): PokemonRepository {

    override fun getPokemonList(): Pager<Int, PokemonResult> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 5
            ),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonApi = pokemonApi)
            }
        )
    }
}