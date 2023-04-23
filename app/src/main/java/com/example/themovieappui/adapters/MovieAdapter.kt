package com.example.themovieappui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieappui.R
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import com.example.themovieappui.viewholders.MovieViewHolder

class MovieAdapter(private val mDelegate:MovieViewHolderDelegate):RecyclerView.Adapter<MovieViewHolder>() {
    private var mMovieList:List<MovieVo> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieVos:List<MovieVo>){
        mMovieList = movieVos
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false)
        return MovieViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(movieList = mMovieList[position])
        }


    }

    override fun getItemCount()= mMovieList.count()
}