package com.example.themovieappui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieappui.R
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.ShowCaseViewHolderDelegate
import com.example.themovieappui.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(val mDelegate: ShowCaseViewHolderDelegate) :
    RecyclerView.Adapter<ShowCaseViewHolder>() {
    private var mMovieList: List<MovieVo> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVo>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_show_cases, parent, false)
        return ShowCaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount() = 5
}