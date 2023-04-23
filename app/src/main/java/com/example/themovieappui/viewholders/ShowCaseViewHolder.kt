package com.example.themovieappui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.ShowCaseViewHolderDelegate
import com.example.themovieappui.util.URLConstant
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_holder_show_cases.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class ShowCaseViewHolder(view: View, private val mDelegate: ShowCaseViewHolderDelegate) :
    RecyclerView.ViewHolder(view) {
    private var movieVo: MovieVo? = null

    init {
        itemView.setOnClickListener {
            movieVo?.let {
                mDelegate.onTapMovieFromShowcase(it.id)
            }

        }
    }

    fun bindData(movieList: MovieVo) {
        this.movieVo = movieList
        Glide.with(itemView.context)
            .load("${URLConstant.IMAGE_BASE_URL}${movieList.posterPath}")
            .into(itemView.ivShowCase)

        itemView.tvShowCaseMovieDate.text = movieList.releaseDate
        itemView.tvShowCaseMovieName.text = movieList.title

    }


}