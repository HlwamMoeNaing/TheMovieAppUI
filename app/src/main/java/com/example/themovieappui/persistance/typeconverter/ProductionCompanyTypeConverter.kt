package com.example.themovieappui.persistance.typeconverter

import androidx.room.TypeConverter
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.ProductionCompanies
import com.example.themovieappui.data.vos.model.ProductionCountries
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompany: List<ProductionCompanies>?): String {
        return Gson().toJson(productionCompany)
    }

    @TypeConverter
    fun toProductionCompany(productionCompanyJsonString: String): List<ProductionCompanies>? {
        val productionCompanyType = object : TypeToken<List<ProductionCompanies>?>() {}.type
        return Gson().fromJson(productionCompanyJsonString, productionCompanyType)
    }
}