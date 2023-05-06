package com.example.themovieappui.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.themovieappui.mvp.views.MovieDetailView

interface MovieDetailPresenter:IBasePresenter {
    fun initView(view:MovieDetailView)
    fun onUiReadyInMovieDetail(owner: LifecycleOwner,movieId:Int)
    fun onTapBack()
}