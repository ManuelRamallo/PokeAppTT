package com.mramallo.pokeapptt.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mramallo.pokeapptt.domain.entity.Abilities
import com.mramallo.pokeapptt.domain.entity.AbilityElement
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.entity.Sprites
import com.mramallo.pokeapptt.domain.entity.StatElement
import com.mramallo.pokeapptt.domain.entity.Stats
import com.mramallo.pokeapptt.domain.entity.TypeElement
import com.mramallo.pokeapptt.domain.entity.Types
import com.mramallo.pokeapptt.domain.usecase.GetPokemonDetailUseCase
import com.mramallo.pokeapptt.domain.usecase.GetPokemonListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.UUID


class PokemonDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @RelaxedMockK
    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    private lateinit var pokemonListViewModel: PokemonListViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        pokemonListViewModel = PokemonListViewModel(getPokemonListUseCase, getPokemonDetailUseCase)
        Dispatchers.setMain(dispatcher = dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPokemonElement when success should return detail of pokemon using uuid and name`() =
        runTest {
            val pokemonListExpectedData: PokemonDetail = testPokemonDetail

            coEvery {
                getPokemonDetailUseCase.invoke("Pikachu")
            } returns pokemonListExpectedData

            pokemonListViewModel.getPokemonElement(testPokemonResult.uuid, testPokemonResult.name)

            assertEquals(
                PokemonListViewModel.PokemonElementState(pokemonDetail = pokemonListExpectedData, loading = false),
                pokemonListViewModel.statePokemonElement[testPokemonResult.uuid]
            )

        }

    val testPokemonResult = PokemonResult(
        name = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/25/",
        uuid = UUID.randomUUID().toString()
    )

    val testPokemonDetail = PokemonDetail(
        id = 25,
        height = 4,
        weight = 60,
        name = "Pikachu",
        order = 1,
        abilities = listOf(
            Abilities(
                AbilityElement(
                    name = "static",
                    url = "https://pokeapi.co/api/v2/ability/9/"
                ),
                is_hidden = false,
                slot = 1
            )
        ),
        base_experience = 150,
        sprites = Sprites(
            back_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png",
            back_shiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png",
            front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            front_shiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png",
        ),
        stats = listOf(
            Stats(
                base_stat = 35,
                stat = StatElement(
                    name = "hp",
                    url = "https://pokeapi.co/api/v2/stat/1/"
                )
            )
        ),
        types = listOf(
            Types(
                slot = 1,
                type = TypeElement(
                    name = "electric",
                    url = "https://pokeapi.co/api/v2/type/13/"
                )
            )
        )
    )

}