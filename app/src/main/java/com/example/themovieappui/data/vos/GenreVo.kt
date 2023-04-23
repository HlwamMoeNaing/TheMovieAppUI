package com.example.themovieappui.data.vos

import com.google.gson.annotations.SerializedName

//data class GenreVo(
//    val genres: List<Genre>
//)

data class GenreVo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)