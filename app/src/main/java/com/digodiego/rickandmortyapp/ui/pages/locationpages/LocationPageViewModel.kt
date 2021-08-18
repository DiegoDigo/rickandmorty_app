package com.digodiego.rickandmortyapp.ui.pages.locationpages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digodiego.rickandmortyapp.data.api.ApiRickAndMorty
import com.digodiego.rickandmortyapp.model.LocationUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationPageViewModel @Inject constructor(
    private val api: ApiRickAndMorty
) : ViewModel() {

    val episodes = mutableStateOf<List<LocationUi>>(listOf());
    val loading = mutableStateOf(true);

    init {
        getEpisodes()
    }

    private fun getEpisodes() {
        viewModelScope.launch {
            episodes.value = api.getLocations().result.map {
                LocationUi(it.name, it.type, it.dimension)
            }.toList()
            loading.value = false
        }
    }
}