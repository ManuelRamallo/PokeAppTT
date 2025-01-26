package com.mramallo.pokeapptt.presentation.ui.utils

import androidx.compose.ui.graphics.Color
import com.mramallo.pokeapptt.presentation.ui.theme.Bug
import com.mramallo.pokeapptt.presentation.ui.theme.Dark
import com.mramallo.pokeapptt.presentation.ui.theme.Dragon
import com.mramallo.pokeapptt.presentation.ui.theme.Electric
import com.mramallo.pokeapptt.presentation.ui.theme.Fairy
import com.mramallo.pokeapptt.presentation.ui.theme.Fighting
import com.mramallo.pokeapptt.presentation.ui.theme.Fire
import com.mramallo.pokeapptt.presentation.ui.theme.Flying
import com.mramallo.pokeapptt.presentation.ui.theme.Ghost
import com.mramallo.pokeapptt.presentation.ui.theme.Grass
import com.mramallo.pokeapptt.presentation.ui.theme.Ground
import com.mramallo.pokeapptt.presentation.ui.theme.Ice
import com.mramallo.pokeapptt.presentation.ui.theme.Normal
import com.mramallo.pokeapptt.presentation.ui.theme.Poison
import com.mramallo.pokeapptt.presentation.ui.theme.Psychic
import com.mramallo.pokeapptt.presentation.ui.theme.Rock
import com.mramallo.pokeapptt.presentation.ui.theme.Shadow
import com.mramallo.pokeapptt.presentation.ui.theme.Steel
import com.mramallo.pokeapptt.presentation.ui.theme.Stellar
import com.mramallo.pokeapptt.presentation.ui.theme.Unknown
import com.mramallo.pokeapptt.presentation.ui.theme.Water

enum class TypesPokemon(val type: String) {
    NORMAL("normal"),
    FIGHTING("fighting"),
    FLYING("flying"),
    POISON("poison"),
    GROUND("ground"),
    ROCK("rock"),
    BUG("bug"),
    GHOST("ghost"),
    STEEL("steel"),
    FIRE("fire"),
    WATER("water"),
    GRASS("grass"),
    ELECTRIC("electric"),
    PSYCHIC("psychic"),
    ICE("ice"),
    DRAGON("dragon"),
    DARK("dark"),
    FAIRY("fairy"),
    STELLAR("stellar"),
    UNKNOWN("unknown"),
    SHADOW("shadow"),
}


fun getColorByTypePokemon(type: String): Color {
    return when(type) {
        TypesPokemon.NORMAL.type -> { Normal }
        TypesPokemon.FIGHTING.type -> { Fighting }
        TypesPokemon.FLYING.type -> { Flying }
        TypesPokemon.POISON.type -> { Poison }
        TypesPokemon.GROUND.type -> { Ground }
        TypesPokemon.ROCK.type -> { Rock }
        TypesPokemon.BUG.type -> { Bug }
        TypesPokemon.GHOST.type -> { Ghost }
        TypesPokemon.STEEL.type -> { Steel }
        TypesPokemon.FIRE.type -> { Fire }
        TypesPokemon.WATER.type -> { Water }
        TypesPokemon.GRASS.type -> { Grass }
        TypesPokemon.ELECTRIC.type -> { Electric }
        TypesPokemon.PSYCHIC.type -> { Psychic }
        TypesPokemon.ICE.type -> { Ice }
        TypesPokemon.DRAGON.type -> { Dragon }
        TypesPokemon.DARK.type -> { Dark }
        TypesPokemon.FAIRY.type -> { Fairy }
        TypesPokemon.STELLAR.type -> { Stellar }
        TypesPokemon.UNKNOWN.type -> { Unknown }
        TypesPokemon.SHADOW.type -> { Shadow }
        else -> { Unknown }
    }
}
