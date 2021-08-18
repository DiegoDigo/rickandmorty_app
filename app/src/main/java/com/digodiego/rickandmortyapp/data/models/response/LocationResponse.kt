package com.digodiego.rickandmortyapp.data.models.response


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("created")
    val created: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("residents")
    val residents: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)