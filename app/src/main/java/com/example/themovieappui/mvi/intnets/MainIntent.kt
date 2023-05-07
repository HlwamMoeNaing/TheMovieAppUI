package com.example.themovieappui.mvi.intnets

import com.example.themovieappui.mvi.mvibase.MVIIntent

sealed class MainIntent:MVIIntent {
    class LoadMovieByGenreIntent(val genrePosition:Int):MainIntent()
    object LoadAllHomePageData:MainIntent()
}