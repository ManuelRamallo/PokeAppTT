package com.mramallo.pokeapptt.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mramallo.pokeapptt.presentation.ui.components.DisplayError
import com.mramallo.pokeapptt.presentation.ui.components.DisplayNoContent

@Composable
fun HomeScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onDetailClick: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getPokemonList()
    }

    val listState: LazyListState = rememberLazyListState()
    val pokemonList = viewModel.state.pokemonList?.collectAsLazyPagingItems()
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(top = 24.dp),
        topBar = {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).padding(vertical = 8.dp)) {
            if (viewModel.state.loading) {
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
                                    Column {
                                        Text(
                                            text = pokemonList[index]?.name ?: "",
                                            modifier = Modifier.clickable {
                                                onDetailClick()
                                            }
                                        )
                                    }
                                }
                            }
                        }


                        if (pokemonList.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
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