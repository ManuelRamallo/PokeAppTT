package com.mramallo.pokeapptt.domain.usecase

import com.mramallo.pokeapptt.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    // TODO - ES POSIBLE QUE HAY QUE METER EL QUERY DEL NOMBRE AQUÍ
    operator fun invoke() = pokemonRepository.getPokemonList()

}