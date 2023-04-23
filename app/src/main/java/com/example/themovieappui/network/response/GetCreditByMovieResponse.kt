package com.example.themovieappui.network.response

import androidx.room.ColumnInfo
import com.example.themovieappui.data.vos.ActorVo
import com.google.gson.annotations.SerializedName

class GetCreditByMovieResponse(
    @SerializedName("cast")
    @ColumnInfo(name = "cast")
    val casts: List<ActorVo>?,

    @SerializedName("crew")
    @ColumnInfo(name = "crew")
    val crew: List<ActorVo>?
)