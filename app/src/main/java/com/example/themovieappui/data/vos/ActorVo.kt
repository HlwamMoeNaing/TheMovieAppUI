package com.example.themovieappui.data.vos

import com.example.themovieappui.data.vos.model.MovieVo
import com.google.gson.annotations.SerializedName

data class ActorVo(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for")
    val knownFor: List<MovieVo>?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("cast_id")
    val castId:Int?,
    @SerializedName("character")
    val character:String?,
    @SerializedName("credit_id")
    val creditId:String?,
    @SerializedName("order")
    val order:Int?,
    @SerializedName("department")
    val department:String?,
    @SerializedName("job")
    val job:String?

)



data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)