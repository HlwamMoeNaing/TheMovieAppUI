package com.example.themovieappui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieappui.R
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.viewholders.ActorViewHolder

class ActorAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private var mActorList: List<ActorVo> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ActorVo>) {
        mActorList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_best_actors, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if (mActorList.isNotEmpty()) {
            holder.bindData(mActorList[position])
        }

    }

    override fun getItemCount() = mActorList.count()
}