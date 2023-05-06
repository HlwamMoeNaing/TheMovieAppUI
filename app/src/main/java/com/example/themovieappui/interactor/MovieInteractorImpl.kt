package com.example.themovieappui.interactor

import android.util.Pair
import androidx.lifecycle.LiveData
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.data.vos.model.MovieVo
import io.reactivex.rxjava3.core.Observable

object MovieInteractorImpl:MovieInteractor {
    private val mMovieModel:MovieModel = MovieModelImpl


    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
       return mMovieModel.getTopRatedMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
       return mMovieModel.getPopularMovies(onFailure)
    }

    override fun getGenre(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getGenre(onSuccess,onFailure)
    }

    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getMovieByGenre(genreId,onSuccess,onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
       return mMovieModel.getActors(onSuccess,onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVo?>? {
        return mMovieModel.getMovieDetails(movieId,onFailure)
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       return mMovieModel.getCreditByMovie(movieId,onSuccess,onFailure)
    }

    override fun searchMovie(query: String): Observable<List<MovieVo>> {
        return mMovieModel.searchMovie(query)
    }
}