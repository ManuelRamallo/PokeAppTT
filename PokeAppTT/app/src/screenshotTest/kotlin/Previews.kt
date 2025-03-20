import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mramallo.pokeapptt.domain.entity.PokemonResult
import com.mramallo.pokeapptt.presentation.ui.components.DisplayPokemonListElement

@Preview
@Composable
fun DisplayPokemonListElementPreview() {
    DisplayPokemonListElement(
        pokemon = PokemonResult(
            name = "Pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        ),
        onDetailClick = {}
    )
}