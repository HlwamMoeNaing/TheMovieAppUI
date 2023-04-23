package com.example.themovieappui.mvp.views

import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieVo

interface MainView :BaseView{
    fun showNowPlayingMovies(nowPlayingMovies:List<MovieVo>)
    fun showPopularMovies(popularMovies:List<MovieVo>)
    fun showTopRatedMovies(topRateMovies:List<MovieVo>)
    fun showGenre(genreList:List<GenreVo>)
    fun showMovieByGenre(movieByGenre:List<MovieVo>)
    fun showActor(actors:List<ActorVo>)
    fun navigateToMovieDetailScreen(movieId:Int)
}