package com.digodiego.rickandmortyapp.ui.pages.datailpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digodiego.rickandmortyapp.data.api.ApiRickAndMorty
import com.digodiego.rickandmortyapp.model.CharacterDetailUi
import com.digodiego.rickandmortyapp.model.CharacterUI
import com.digodiego.rickandmortyapp.model.EpisodeUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val api: ApiRickAndMorty
) : ViewModel() {

    val character = mutableStateOf<CharacterDetailUi?>(null)
    val loading = mutableStateOf(true)


    fun getCharacter(id: Int) {

        viewModelScope.launch {
            val response = api.getCharacter(id)

            val episodes = response.episode.map { episode ->
                val ep = api.getEpisodeDynamic(episode)
                EpisodeUi(
                    ep.episode,
                    ep.airDate,
                    ep.name
                )
            }
            character.value = CharacterDetailUi(
                response.id,
                response.name,
                response.status,
                response.species,
                response.gender,
                response.image,
                episodes
            )
            loading.value = false
        }

    }

}