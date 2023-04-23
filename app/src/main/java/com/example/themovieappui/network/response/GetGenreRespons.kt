package com.example.themovieappui.network.response

import com.example.themovieappui.data.vos.GenreVo
import com.google.gson.annotations.SerializedName

data class GetGenreRespons(
    @SerializedName("genres")
    val genre: List<GenreVo>
)