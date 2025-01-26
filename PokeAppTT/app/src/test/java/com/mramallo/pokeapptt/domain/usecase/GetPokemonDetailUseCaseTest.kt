package com.mramallo.pokeapptt.domain.usecase

import com.mramallo.pokeapptt.domain.entity.Abilities
import com.mramallo.pokeapptt.domain.entity.AbilityElement
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.domain.entity.Sprites
import com.mramallo.pokeapptt.domain.entity.StatElement
import com.mramallo.pokeapptt.domain.entity.Stats
import com.mramallo.pokeapptt.domain.entity.TypeElement
import com.mramallo.pokeapptt.domain.entity.Types
import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetPokemonDetailUseCaseTest {

    @ExperimentalCoroutinesApi
    private val dispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var pokemonRepository: PokemonRepository

    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    @Before
    fun onBefore() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        getPokemonDetailUseCase = GetPokemonDetailUseCase(pokemonRepository)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPokemonDetailUseCase should call getPokemonDetail from repository`() {
        coEvery {
            pokemonRepository.getPokemonDetail("Pikachu")
        } answers {
            testPokemonDetail
        }

        runTest {
            val result = getPokemonDetailUseCase.invoke("Pikachu")
            Assert.assertEquals(testPokemonDetail, result)
        }
    }

    @Test
    fun `getPokemonDetailUseCase should success when call getPokemonDetail from repository and name is empty`() {
        coEvery {
            pokemonRepository.getPokemonDetail("")
        } answers {
            testPokemonDetail
        }

        runTest {
            val result = getPokemonDetailUseCase.invoke("")
            Assert.assertEquals(testPokemonDetail, result)
        }
    }


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