package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mramallo.pokeapptt.R
import com.mramallo.pokeapptt.presentation.ui.components.DisplayError
import com.mramallo.pokeapptt.presentation.ui.components.DisplayNoContent
import com.mramallo.pokeapptt.presentation.ui.components.DisplayPokemonListElement
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    pokemonListViewModel: PokemonListViewModel = hiltViewModel(),
    onDetailClick: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        pokemonListViewModel.getPokemonList()
    }

    val listState: LazyListState = rememberLazyListState()
    val pokemonList = pokemonListViewModel.statePokemonList.pokemonList?.collectAsLazyPagingItems()
    var query by rememberSaveable { mutableStateOf("") }
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(top = 24.dp),
        topBar = {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    // Cancel debouncejob if is active
                    debounceJob?.cancel()

                    // Add time for write all text
                    debounceJob = coroutineScope.launch {
                        delay(500)
                        pokemonListViewModel.getPokemonList(query)

                    }
                },
                singleLine = true,
                placeholder = {
                    Text(stringResource(R.string.placeholder_search))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Icon Search"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 8.dp)
        ) {
            if (pokemonListViewModel.statePokemonList.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (pokemonList != null) {
                    if (pokemonList.loadState.refresh is LoadState.NotLoading) {
                        if (pokemonList.itemCount == 0) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                DisplayNoContent()
                            }
                        }
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (pokemonList.loadState.prepend is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        if (pokemonList.loadState.prepend is LoadState.Error && !pokemonList.loadState.hasError) {
                            item {
                                DisplayError(pokemonList)
                            }
                        }

                        if (pokemonList.loadState.refresh is LoadState.NotLoading) {
                            if (pokemonList.itemCount != 0) {
                                items(
                                    count = pokemonList.itemCount,
                                    key = pokemonList.itemKey { it.uuid },
                                    contentType = pokemonList.itemContentType { it.name }
                                ) { index ->
                                    DisplayPokemonListElement(
                                        pokemonListViewModel = pokemonListViewModel,
                                        pokemon = pokemonList[index],
                                        onDetailClick = onDetailClick
                                    )
                                }
                            }
                        }

                        if (pokemonList.loadState.append is LoadState.Error && !pokemonList.loadState.hasError) {
                            item {
                                DisplayError(pokemonList)
                            }
                        }
                    }

                    if (pokemonList.loadState.hasError) {
                        DisplayError(pokemonList)
                    }

                } else {
                    DisplayNoContent()
                }
            }
        }
    }
}