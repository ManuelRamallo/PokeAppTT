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
import com.mramallo.pokeapptt.domain.entity.PokemonDetail
import com.mramallo.pokeapptt.domain.entity.PokemonList
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.domain.entity.Sprites
import com.mramallo.pokeapptt.domain.entity.StatElement
import com.mramallo.pokeapptt.domain.entity.Stats
import com.mramallo.pokeapptt.domain.entity.TypeElement
import com.mramallo.pokeapptt.domain.entity.Types


fun PokemonListDto.toPokemonList(): PokemonList {
    return PokemonList(
        count = count ?: 0,
        next = next ?: "",
        previous = previous ?: "",
        results = results?.map { it.toPokemonResult() } ?: listOf()
    )
}

fun PokemonResultDto.toPokemonResult(): PokemonResult {
    return PokemonResult(
        name = name ?: "",
        url = url ?: ""
    )
}

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail{
    return PokemonDetail(
        id = id ?: 0,
        height = height ?: 0,
        weight = weight ?: 0,
        name = name ?: "",
        order = order ?: 0,
        abilities = abilities?.map { it.toAbilities() } ?: listOf(),
        base_experience = base_experience ?: 0,
        sprites = sprites?.toSprites() ?: Sprites("", "", "", ""),
        stats = stats?.map { it.toStats() } ?: listOf(),
        types = types?.map { it.toTypes() } ?: listOf()
    )
}

fun AbilitiesDto.toAbilities(): Abilities {
    return Abilities(
        ability = ability?.toAbilityElement() ?: AbilityElement(name = "", url = ""),
        is_hidden = is_hidden ?: false,
        slot = slot ?: 0
    )
}

fun AbilityElementDto.toAbilityElement(): AbilityElement {
    return AbilityElement(
        name = name ?: "",
        url = url ?: ""
    )
}

fun SpritesDto.toSprites(): Sprites {
    return Sprites(
        front_default = front_default ?: "",
        back_default = back_default ?: "",
        front_shiny = front_shiny ?: "",
        back_shiny = back_shiny ?: ""
    )
}

fun StatsDto.toStats(): Stats {
    return Stats(
        base_stat = base_stat ?: 0,
        stat = stat?.toStatElement() ?: StatElement(name = "", url = "")
    )
}

fun StatElementDto.toStatElement(): StatElement {
    return StatElement(
        name = name ?: "",
        url = url ?: ""
    )
}

fun TypesDto.toTypes(): Types {
    return Types(
        slot = slot ?: 0,
        type = type?.toTypeElement() ?: TypeElement(name = "", url = "")
    )
}

fun TypeElementDto.toTypeElement(): TypeElement {
    return TypeElement(
        name = name ?: "",
        url = url ?: ""
    )
}