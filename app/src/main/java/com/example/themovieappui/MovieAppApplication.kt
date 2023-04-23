package com.example.themovieappui

import android.app.Application
import android.util.Log
import com.example.themovieappui.data.model.BaseModel
import com.example.themovieappui.data.model.MovieModelImpl


class MovieAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(this)
        Log.d("NWE MVP","Hello NEW MVP")
    }
}