package com.example.themovieappui.persistance.typeconverter

import androidx.room.TypeConverter
import com.example.themovieappui.data.vos.model.ProductionCompanies
import com.example.themovieappui.data.vos.model.ProductionCountries
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCoutriesTypeConverter {
    @TypeConverter
    fun toString(productionCountries: List<ProductionCountries>?): String {
        return Gson().toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountry(productionCountryJsonString: String): List<ProductionCountries>? {
        val productionCountryType = object : TypeToken<List<ProductionCountries>?>() {}.type
        return Gson().fromJson(productionCountryJsonString, productionCountryType)
    }
}