package com.example.themovieappui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.BannerViewHolderDelegate
import com.example.themovieappui.util.URLConstant
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(view:View,private val mDelegate: BannerViewHolderDelegate):RecyclerView.ViewHolder(view) {
    private var movieVo:MovieVo? = null
    init {
        itemView.setOnClickListener {
            movieVo?.let {
                mDelegate.onTapMovieFromBanner(it.id)
            }

        }
    }

    fun bindData(movieVo: MovieVo){
        this.movieVo = movieVo
        Glide.with(itemView.context)
            .load("${URLConstant.IMAGE_BASE_URL}${movieVo.posterPath}")
            .into(itemView.ivBannerImage)

    }
}
