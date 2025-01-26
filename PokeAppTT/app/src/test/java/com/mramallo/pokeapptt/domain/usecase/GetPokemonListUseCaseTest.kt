package com.mramallo.pokeapptt.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.UUID


class GetPokemonListUseCaseTest {

    @ExperimentalCoroutinesApi
    private val dispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var pokemonRepository: PokemonRepository

    private lateinit var getPokemonListUseCase: GetPokemonListUseCase


    @Before
    fun onBefore() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        getPokemonListUseCase = GetPokemonListUseCase(pokemonRepository)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should call getPokemonList from repository`() {
        val fakeResults = listOf(testPokemonResult)
        val fakePagingSource = FakePagingSource(fakeResults)

        coEvery {
            pokemonRepository.getPokemonList().flow
        } returns Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { fakePagingSource }
        ).flow

        val result = getPokemonListUseCase.invoke().flow

        runBlocking {
            val collectedData = mutableListOf<PagingData<PokemonResult>>()

            result.collect { data ->
                collectedData.add(data)
            }

            assertEquals(fakeResults, collectedData)
        }

    }


    val testPokemonResult = PokemonResult(
        name = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/25/",
        uuid = UUID.randomUUID().toString()
    )

    // FakePagingSource para simular datos de prueba
    class FakePagingSource(
        private val data: List<PokemonResult>
    ) : PagingSource<Int, PokemonResult>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
            return LoadResult.Page(
                data = data,
                prevKey = 1,
                nextKey = 1
            )
        }

        override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? = null
    }

}

