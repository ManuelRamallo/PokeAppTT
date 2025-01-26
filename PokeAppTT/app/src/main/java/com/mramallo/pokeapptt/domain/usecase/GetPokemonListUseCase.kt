package com.mramallo.pokeapptt.domain.usecase

import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke() = pokemonRepository.getPokemonList()

}