package com.example.themovieappui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieVo

class MainViewModel:ViewModel() {
    // Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMovieLiveData:LiveData<List<MovieVo>>? = null
    var popularMovieLiveData:LiveData<List<MovieVo>>? = null
    var topRatedMovieLiveData:LiveData<List<MovieVo>>? = null
    val genresLiveData = MutableLiveData<List<GenreVo>>()
    val movieBYgenreLiveData = MutableLiveData<List<MovieVo>>()
    val actorsLiveData = MutableLiveData<List<ActorVo>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(){
        nowPlayingMovieLiveData =  mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMovieLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenre(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    fun getMovieByGenre(genrePosition:Int){
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMovieByGenre(it.toString(), onSuccess = {movieByGenre->
                movieBYgenreLiveData.postValue(movieByGenre)

            }, onFailure = {errorMessage->
                mErrorLiveData.postValue(errorMessage)
            })
        }
    }

}