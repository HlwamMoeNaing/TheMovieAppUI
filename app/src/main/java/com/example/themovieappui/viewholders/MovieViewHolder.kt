package com.example.themovieappui.viewholders

import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import com.example.themovieappui.util.URLConstant
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class MovieViewHolder(itemView:View,private val mDelegate:MovieViewHolderDelegate):RecyclerView.ViewHolder(itemView) {
    private var movieVo:MovieVo? = null
    init {
        itemView.setOnClickListener {
            movieVo?.let {
                mDelegate.onTapMovie(it.id)
            }

        }
    }
    fun bindData(movieList:MovieVo){
        this.movieVo = movieList
        Glide.with(itemView.context)
            .load("${URLConstant.IMAGE_BASE_URL}${movieList.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movieList.title
        itemView.tvMovieRating.text = movieList.voteAverage?.toString()
        itemView.rbMovieRating.rating = movieList.getRatingBaseOnFiveStar()
    }
}