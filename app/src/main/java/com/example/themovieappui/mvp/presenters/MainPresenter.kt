package com.example.themovieappui.mvp.presenters

import com.example.themovieappui.delegate.BannerViewHolderDelegate
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import com.example.themovieappui.delegate.ShowCaseViewHolderDelegate
import com.example.themovieappui.mvp.views.MainView

interface MainPresenter:IBasePresenter,BannerViewHolderDelegate,ShowCaseViewHolderDelegate,MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition:Int)
}