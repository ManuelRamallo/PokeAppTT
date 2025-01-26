package com.mramallo.pokeapptt.domain.usecase

import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(name: String) = pokemonRepository.getPokemonDetail(name)

}