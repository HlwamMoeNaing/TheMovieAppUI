package com.example.themovieappui.persistance.typeconverter

import androidx.room.TypeConverter
import com.example.themovieappui.data.vos.model.SpokenLanguages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLauguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages: List<SpokenLanguages>?): String {
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLaugugeJsonString: String): List<SpokenLanguages>? {
        val spokenLanguagesType = object : TypeToken<List<SpokenLanguages>?>() {}.type
        return Gson().fromJson(spokenLaugugeJsonString, spokenLanguagesType)
    }
}