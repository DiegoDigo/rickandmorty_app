package com.digodiego.rickandmortyapp.ui.params

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailParam(
    val id : Int,
    val name : String,
): Parcelable