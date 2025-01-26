package com.mramallo.pokeapptt.data.mappers

import com.mramallo.pokeapptt.data.remote.AbilitiesDto
import com.mramallo.pokeapptt.data.remote.AbilityElementDto
import com.mramallo.pokeapptt.data.remote.PokemonDetailDto
import com.mramallo.pokeapptt.data.remote.PokemonListDto
import com.mramallo.pokeapptt.data.remote.PokemonResultDto
import com.mramallo.pokeapptt.data.remote.SpritesDto
import com.mramallo.pokeapptt.data.remote.StatElementDto
import com.mramallo.pokeapptt.data.remote.StatsDto
import com.mramallo.pokeapptt.data.remote.TypeElementDto
import com.mramallo.pokeapptt.data.remote.TypesDto
import com.mramallo.pokeapptt.domain.entity.Abilities
import com.mramallo.pokeapptt.domain.entity.AbilityElement
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.entity.Sprites
import com.mramallo.pokeapptt.domain.entity.StatElement
import com.mramallo.pokeapptt.domain.entity.Stats
import com.mramallo.pokeapptt.domain.entity.TypeElement
import com.mramallo.pokeapptt.domain.entity.Types
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PokemonMapperKtTest {


    @Test
    fun `toPokemonList() When fields are null should return empty fields`() {
        val pokemonList = PokemonListDto(
            count = null,
            next = null,
            previous = null,
            results = null
        ).toPokemonList()

        assertEquals(0, pokemonList.count)
        assertEquals("", pokemonList.next)
        assertEquals("", pokemonList.previous)
        assertEquals(listOf<PokemonResult>(), pokemonList.results)
    }

    @Test
    fun `toPokemonResult() When fields are null should return empty fields`() {
        val pokemonResult = PokemonResultDto(
            name = null,
            url = null
        ).toPokemonResult()

        assertEquals("", pokemonResult.name)
        assertEquals("", pokemonResult.url)
    }

    @Test
    fun `toPokemonDetail() When fields are null should return empty fields`() {
        val pokemonDetail = PokemonDetailDto(
            id = null,
            height = null,
            weight = null,
            name = null,
            order = null,
            abilities = null,
            base_experience = null,
            sprites = null,
            stats = null,
            types = null
        ).toPokemonDetail()

        assertEquals(0, pokemonDetail.id)
        assertEquals(0, pokemonDetail.height)
        assertEquals(0, pokemonDetail.weight)
        assertEquals("", pokemonDetail.name)
        assertEquals(0, pokemonDetail.order)
        assertEquals(listOf<Abilities>(), pokemonDetail.abilities)
        assertEquals(0, pokemonDetail.base_experience)
        assertEquals(Sprites("", "", "", ""), pokemonDetail.sprites)
        assertEquals(listOf<Stats>(), pokemonDetail.stats)
        assertEquals(listOf<Types>(), pokemonDetail.types)
    }

    @Test
    fun `toAbilities() When fields are null should return empty fields`() {
        val abilities = AbilitiesDto(
            ability = null,
            is_hidden = null,
            slot = null
        ).toAbilities()

        assertEquals(AbilityElement("", ""), abilities.ability)
        assertEquals(false, abilities.is_hidden)
        assertEquals(0, abilities.slot)
    }

    @Test
    fun `toAbilityElement() When fields are null should return empty fields`() {
        val abilityElement = AbilityElementDto(
            name = null,
            url = null
        ).toAbilityElement()

        assertEquals("", abilityElement.name)
        assertEquals("", abilityElement.url)
    }

    @Test
    fun `toSprites() When fields are null should return empty fields`() {
        val sprites = SpritesDto(
            front_default = null,
            back_default = null,
            front_shiny = null,
            back_shiny = null
        ).toSprites()

        assertEquals("", sprites.front_default)
        assertEquals("", sprites.back_default)
        assertEquals("", sprites.front_shiny)
        assertEquals("", sprites.back_shiny)
    }

    @Test
    fun `toStats() When fields are null should return empty fields`() {
        val stats = StatsDto(
            base_stat = null,
            stat = null
        ).toStats()

        assertEquals(0, stats.base_stat)
        assertEquals(StatElement(name = "", url = ""), stats.stat)
    }

    @Test
    fun `toStatElement() When fields are null should return empty fields`() {
        val statElement = StatElementDto(
            name = null,
            url = null
        ).toStatElement()

        assertEquals("", statElement.name)
        assertEquals("" , statElement.url)
    }

    @Test
    fun `toTypes() When fields are null should return empty fields`() {
        val types = TypesDto(
            slot = null,
            type = null
        ).toTypes()

        assertEquals(0, types.slot)
        assertEquals(TypeElement(name = "", url = ""), types.type)
    }

    @Test
    fun `toTypeElement() When fields are null should return empty fields`() {
        val typeElement = TypeElementDto(
            name = null,
            url = null
        ).toTypeElement()

        assertEquals("", typeElement.name)
        assertEquals("", typeElement.url)
    }
}