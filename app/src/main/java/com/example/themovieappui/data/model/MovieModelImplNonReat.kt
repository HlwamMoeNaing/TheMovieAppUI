package com.example.themovieappui.data.model

import android.content.Context
import android.util.Pair
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.*
import com.example.themovieappui.network.dataagent.MovieDataAgent

import com.example.themovieappui.persistance.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
//
//object MovieModelImplNonReat : BaseModel(), MovieModel {
////    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
////    private var mMovieDatabase: MovieDatabase? = null
////
////    fun initDatabase(context: Context) {
////        mMovieDatabase = MovieDatabase.getDBInstance(context)
////    }
//
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING) ?: listOf())
//
//
////        mMovieApi.getNowPlayingMovies(onSuccess = {
////            it.forEach { movie ->
////                movie.type = NOW_PLAYING
////            }
////            mMovieDatabase?.movieDao()?.insertMovies(it)
////            onSuccess(it)
////        }, onFailure = onFailure)
//
//        mMovieApi.getNowPlayingMovies(page = 1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.results?.forEach { movie ->
//                    movie.type = NOW_PLAYING
//                }
//                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//            }, {
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
////        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED) ?: listOf())
////        mMovieDataAgent.getTopRatedMovies(onSuccess = {
////            it.forEach { movie ->
////                movie.type = TOP_RATED
////            }
////            mMovieDatabase?.movieDao()?.insertMovies(it)
////
////        }, onFailure = onFailure)
//        mMovieApi.getTopRatedMovies(page = 1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.results?.forEach { movie -> movie.type = TOP_RATED }
//                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//            }, {
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVo>) -> Unit, onFailure: (String) -> Unit) {
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR) ?: listOf())
////        mMovieDataAgent.getPopularMovies(onSuccess = {
////            it.forEach { movie ->
////                movie.type = POPULAR
////            }
////            mMovieDatabase?.movieDao()?.insertMovies(it)
////
////        }, onFailure = onFailure)
//        mMovieApi.getPopularMovies(page = 1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.results?.forEach { movie -> movie.type = POPULAR }
//                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//            }, {
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//    override fun getGenre(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieApi.getGenre()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                onSuccess(it.genre ?: listOf())
//            },{
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//    override fun getMovieByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//      mMovieApi.getMovieByGenre(genreId)
//          .subscribeOn(Schedulers.io())
//          .observeOn(AndroidSchedulers.mainThread())
//          .subscribe({
//              onSuccess(it.results ?: listOf())
//          },{
//              onFailure(it.localizedMessage ?: "")
//          })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
//       mMovieApi.getActors()
//           .subscribeOn(Schedulers.io())
//           .observeOn(AndroidSchedulers.mainThread())
//           .subscribe({
//               onSuccess(it.results ?: listOf())
//           },{
//               onFailure(it.localizedMessage ?: "")
//           })
//    }
//
//    override fun getMovieDetails(
//        movieId: String,
//        onSuccess: (MovieVo) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        val movieFromdatabase = mMovieDatabase?.movieDao()?.getMovieBYId(movieId = movieId.toInt())
//        movieFromdatabase?.let {
//            onSuccess(it)
//        }
//
//
////        mMovieDataAgent.getMovieDetails(
////            movieId,
////            onSuccess = {
////                val movieFromDatabase =
////                    mMovieDatabase?.movieDao()?.getMovieBYId(movieId = movieId.toInt())
////                it.type = movieFromDatabase?.type
////                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
////                onSuccess(it)
////            }, onFailure = onFailure
////        )
//
//        mMovieApi.getMovieDetails(movieId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                val movieFromDatabaseToSync = mMovieDatabase?.movieDao()?.getMovieBYId(movieId =movieId.toInt())
//                it.type = movieFromDatabaseToSync?.type
//                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//            },{
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//    override fun getCreditByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mMovieApi.getCreditByMovie(movieId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                onSuccess(Pair(it.casts ?: listOf(),it.crew ?: listOf()))
//            },{
//                onFailure(it.localizedMessage ?: "")
//            })
//    }
//
//
//}