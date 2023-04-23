package com.example.themovieappui.network.response

import com.example.themovieappui.data.vos.DateVO
import com.example.themovieappui.data.vos.model.MovieVo
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("dates")
    val dates: DateVO?,
    @SerializedName("results")
    val results: List<MovieVo>?
)