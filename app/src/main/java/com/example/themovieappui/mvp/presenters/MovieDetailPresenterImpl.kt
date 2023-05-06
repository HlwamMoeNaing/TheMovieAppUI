package com.example.themovieappui.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.interactor.MovieInteractorImpl
import com.example.themovieappui.mvp.views.MovieDetailView

class MovieDetailPresenterImpl:ViewModel(),MovieDetailPresenter {
    //Model
    private val mMovieDetailInteractor = MovieInteractorImpl

    //View
    private var mView:MovieDetailView? = null

    override fun initView(view: MovieDetailView) {
       mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
       //MovieDetail
        mMovieDetailInteractor.getMovieDetails(movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetail(it)
            }
        }

        //Credit
        mMovieDetailInteractor.getCreditByMovie(movieId = movieId.toString(), onSuccess = {
            mView?.showCreditByMovie(cast = it.first, crew = it.second)
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapBack() {
       mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}