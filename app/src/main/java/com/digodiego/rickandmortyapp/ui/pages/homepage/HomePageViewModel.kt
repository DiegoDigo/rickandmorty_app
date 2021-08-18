package com.digodiego.rickandmortyapp.ui.pages.homepage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digodiego.rickandmortyapp.data.api.ApiRickAndMorty
import com.digodiego.rickandmortyapp.model.CharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val api: ApiRickAndMorty
) : ViewModel() {

    val characters = mutableStateOf<List<CharacterUI>>(listOf())

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            characters.value = api.getCharacters().result.map {
                CharacterUI(it.id, it.name, it.origin.name, it.image)
            }.toList()
        }
    }

}