package com.digodiego.rickandmortyapp.data.models.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)