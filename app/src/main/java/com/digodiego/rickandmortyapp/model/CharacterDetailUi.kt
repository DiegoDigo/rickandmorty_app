package com.digodiego.rickandmortyapp.model


data class CharacterDetailUi(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlImg: String,
    val episodes: List<EpisodeUi>
)
