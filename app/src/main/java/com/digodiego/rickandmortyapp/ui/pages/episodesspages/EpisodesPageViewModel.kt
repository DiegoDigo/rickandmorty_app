package com.digodiego.rickandmortyapp.ui.pages.locationpages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digodiego.rickandmortyapp.data.api.ApiRickAndMorty
import com.digodiego.rickandmortyapp.model.EpisodeUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesPageViewModel @Inject constructor(
    private val api: ApiRickAndMorty
) : ViewModel() {

    val episodes = mutableStateOf<List<EpisodeUi>>(listOf());
    val loading = mutableStateOf(true);

    init {
        getEpisodes()
    }

    private fun getEpisodes() {
        viewModelScope.launch {
            episodes.value = api.getEpisodes().result.map {
                EpisodeUi(it.episode, it.airDate, it.name)
            }.toList()
            loading.value = false
        }
    }
}