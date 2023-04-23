package com.example.themovieappui.network.response

import com.example.themovieappui.data.vos.ActorVo
import com.google.gson.annotations.SerializedName

data class GetActorResponse(
    @SerializedName("results")
    val results:List<ActorVo>?
)