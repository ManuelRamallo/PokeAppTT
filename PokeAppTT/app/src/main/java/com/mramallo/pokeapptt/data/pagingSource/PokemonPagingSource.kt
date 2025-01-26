package com.mramallo.pokeapptt.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mramallo.pokeapptt.data.mappers.toPokemonList
import com.mramallo.pokeapptt.data.remote.api.PokemonApi
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import kotlinx.coroutines.delay

class PokemonPagingSource(
    private val pokemonApi: PokemonApi,
) : PagingSource<Int, PokemonResult>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            delay(1000) // This is not funcional, is only for the demo
            val pokemonList =
                pokemonApi.getPokemonList(offset = page, limit = pageSize).toPokemonList()
            LoadResult.Page(
                data = pokemonList.results,
                prevKey = if (page == 0) null else page.minus(5),
                nextKey = if (pokemonList.results.size < pageSize) null else page.plus(5)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}