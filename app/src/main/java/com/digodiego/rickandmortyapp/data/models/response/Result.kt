package com.digodiego.rickandmortyapp.data.models.response


import com.google.gson.annotations.SerializedName

data class Result<T>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val result: T
)