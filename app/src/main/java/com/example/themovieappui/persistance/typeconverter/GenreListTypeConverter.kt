package com.example.themovieappui.persistance.typeconverter

import androidx.room.TypeConverter
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.SpokenLanguages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genreList: List<GenreVo>?): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListJsonString: String): List<GenreVo>? {
        val genreListType = object : TypeToken<List<GenreVo>?>() {}.type
        return Gson().fromJson(genreListJsonString, genreListType)
    }
}