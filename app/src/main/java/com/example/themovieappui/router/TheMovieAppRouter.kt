package com.example.themovieappui.router

import android.app.Activity
import com.example.themovieappui.activities.MovieDetailActivity
import com.example.themovieappui.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailActivity(movieId:Int){
    startActivity(MovieDetailActivity.newIntent(this,movieId))
}

fun Activity.navigateToSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}