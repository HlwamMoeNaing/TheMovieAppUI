package com.example.themovieappui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieappui.R
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.BannerViewHolderDelegate
import com.example.themovieappui.viewholders.BannerViewHolder

class BannerAdapter(val delegate: BannerViewHolderDelegate) :
    RecyclerView.Adapter<BannerViewHolder>() {
    private var mMovieList: List<MovieVo> = listOf()
     fun setData(movieList: List<MovieVo>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner, parent, false)
        return BannerViewHolder(layout, delegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (mMovieList.count() > 5)
            5
        else
            mMovieList.count()
    }

}