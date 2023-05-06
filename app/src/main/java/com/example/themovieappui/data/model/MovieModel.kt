package com.example.themovieappui.data.vos.model

import android.util.Pair
import androidx.lifecycle.LiveData
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.network.response.MovieListResponse
import io.reactivex.rxjava3.core.Observable

interface MovieModel {

    fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVo>>?

    fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVo>>?

    fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVo>>?

    fun getGenre(
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
        onFailure: (String) -> Unit
    ): LiveData<MovieVo?>?

    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMovie(query: String): Observable<List<MovieVo>>

    // reactive stream only
    fun getNowPlayingMoviesObservable():Observable<List<MovieVo>>?
    fun getPopularMoviesObservable():Observable<List<MovieVo>>?
    fun getTopRatedMoviesObservable():Observable<List<MovieVo>>?

    fun getGenresObservable():Observable<List<GenreVo>>?
    fun getActorObservable():Observable<List<ActorVo>>?
    fun getMovieByGenreObservable(genreId: String):Observable<List<MovieVo>>?
    fun getMovieByIdObservable(movieId: Int):Observable<MovieVo?>?
    fun getCreditsByMovieObservable(movieId: Int):Observable<Pair<List<ActorVo>,List<ActorVo>>>

}