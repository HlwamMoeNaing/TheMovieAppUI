package com.example.themovieappui.mvp.views

import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.model.MovieVo

interface MovieDetailView :BaseView{
    fun showMovieDetail(movieVo: MovieVo)
    fun showCreditByMovie(cast:List<ActorVo>,crew:List<ActorVo>)
    fun navigateBack()
}