package com.digodiego.rickandmortyapp.data.api

import com.digodiego.rickandmortyapp.data.models.response.Character
import com.digodiego.rickandmortyapp.data.models.response.Episode
import com.digodiego.rickandmortyapp.data.models.response.LocationResponse
import com.digodiego.rickandmortyapp.data.models.response.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiRickAndMorty {

    @GET("character")
    suspend fun getCharacters(): Result<List<Character>>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

    @GET
    suspend fun getEpisodeDynamic(@Url url: String): Episode

    @GET("episode")
    suspend fun getEpisodes(): Result<List<Episode>>

    @GET("location")
    suspend fun getLocations(): Result<List<LocationResponse>>
}