package com.example.themovieappui.data.model

import android.util.Pair
import androidx.lifecycle.LiveData
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {
    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
        //Network
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie ->
                    movie.type = NOW_PLAYING
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVo>>? {
        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)
    }

    override fun getGenre(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getGenre()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genre ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVo>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getMovieByGenre(genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVo?>? {
        mMovieApi.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieBYIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieBYId(movieId.toInt())
    }


    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditByMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.casts ?: listOf(), it.crew ?: listOf()))
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun searchMovie(query: String): Observable<List<MovieVo>>{
      return mMovieApi.searchMovie(query=query)
          .map { it.results ?: listOf() }
          .onErrorResumeNext { Observable.just(listOf()) }
          .subscribeOn(Schedulers.io())
    }
}