package com.example.themovieappui.persistance.typeconverter

import androidx.room.TypeConverter
import com.example.themovieappui.data.vos.model.BelongsToCollection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class BelongsToCollectionTypeConverter {
    @TypeConverter
    fun toString(belongToCollection: BelongsToCollection?): String {
        return Gson().toJson(belongToCollection)
    }

    @TypeConverter
    fun toBelongToCollection(belongToCollectionJsonString: String): BelongsToCollection? {
        val belongsToCollectionType = object : TypeToken<BelongsToCollection?>() {}.type
        return Gson().fromJson(belongToCollectionJsonString, belongsToCollectionType)
    }
}