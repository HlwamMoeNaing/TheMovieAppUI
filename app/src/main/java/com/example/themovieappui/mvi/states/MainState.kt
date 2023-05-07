package com.example.themovieappui.mvi.states

import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.mvi.mvibase.MVIState

data class MainState(
    val isLoading:Boolean = false,
    val errorMessage:String ="",
    val nowPlayingMovie:List<MovieVo>,
    val popularMovie:List<MovieVo>,
    val topRatedMovie:List<MovieVo>,
    val genre:List<GenreVo>,
    var movieByGenre:List<MovieVo>,
    val actors:List<ActorVo>
):MVIState{
    companion object{
        fun idle():MainState = MainState(
            isLoading = false,
            errorMessage =  "",
            nowPlayingMovie = listOf(),
            popularMovie = listOf(),
            topRatedMovie = listOf(),
            genre = listOf(),
            movieByGenre = listOf(),
            actors = listOf()
        )
    }
}