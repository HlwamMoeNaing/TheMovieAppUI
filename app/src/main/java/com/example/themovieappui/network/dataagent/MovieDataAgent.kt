package com.example.themovieappui.network.dataagent

import android.util.Pair
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieVo

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTopRatedMovies(
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularMovies(
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getActors(
        onSuccess: (List<ActorVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVo) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit
    )
}