package com.example.themovieappui.mvi.processors

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.mvi.states.MainState
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MainProcessor {
    private val mMovieMode: MovieModel = MovieModelImpl
    fun loadAllHomePageData(
        previousState: MainState
    ): LiveData<MainState> {
        return Observable.zip(
            mMovieMode.getNowPlayingMoviesObservable(),
            mMovieMode.getPopularMoviesObservable(),
            mMovieMode.getTopRatedMoviesObservable(),
            mMovieMode.getGenresObservable(),
            mMovieMode.getActorObservable()
        ) { nowPlayingMovie, popularMovie, topRateMovie, genre, actor ->
            return@zip previousState.copy(
                isLoading = false,
                errorMessage = "",
                nowPlayingMovie = nowPlayingMovie,
                popularMovie = popularMovie,
                topRatedMovie = topRateMovie,
                genre = genre,
                movieByGenre = previousState.movieByGenre,
                actors = actor
            )

        }.toFlowable(BackpressureStrategy.BUFFER).toLiveData()
    }



    fun loadMovieByGenre(
        previousState: MainState,
        genreId:Int
    ):LiveData<MainState>{
        return mMovieMode.getMovieByGenreObservable(genreId = genreId.toString())?.map {
            previousState.copy(
                isLoading = false,
                errorMessage ="",
                nowPlayingMovie = previousState.nowPlayingMovie,
                popularMovie = previousState.popularMovie,
                topRatedMovie = previousState.topRatedMovie,
                genre = previousState.genre,
                movieByGenre = it,
                actors = previousState.actors
            )
        }?.subscribeOn(Schedulers.io())?.toFlowable(BackpressureStrategy.BUFFER)?.toLiveData()!!
    }
}