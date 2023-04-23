package com.example.themovieappui.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieappui.adapters.MovieAdapter
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var movieAdapter: MovieAdapter
    lateinit var mDelegate:MovieViewHolderDelegate

    override fun onFinishInflate() {
        //setUpRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate:MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpRecyclerView()
    }
    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }
    fun setNewData(movieList:List<MovieVo>){
        movieAdapter.setNewData(movieList)
    }
    private fun setUpRecyclerView(){
        movieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = movieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
}