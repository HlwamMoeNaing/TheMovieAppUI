package com.example.themovieappui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.model.MovieVo

class MovieDetailViewModel:ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var movieDetailLiveData:LiveData<MovieVo?>? = null
    val castLiveData = MutableLiveData<List<ActorVo>>()
    val crewLiveData = MutableLiveData<List<ActorVo>>()
    val mErrorLiveData =MutableLiveData<String>()

    fun getInitialData(movieId:Int){
        movieDetailLiveData = mMovieModel.getMovieDetails(movieId = movieId.toString()){
            mErrorLiveData.postValue(it)
        }

        mMovieModel.getCreditByMovie(movieId.toString(), onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue(it.second ?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
        })
    }
}