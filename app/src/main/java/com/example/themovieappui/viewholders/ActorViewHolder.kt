package com.example.themovieappui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.util.URLConstant
import kotlinx.android.synthetic.main.view_holder_best_actors.view.*

class ActorViewHolder(view:View):RecyclerView.ViewHolder(view) {
    fun bindData(actorVo: ActorVo){
        Glide.with(itemView.context)
            .load("${URLConstant.IMAGE_BASE_URL}${actorVo.profilePath}")
            .into(itemView.ivBestActor)
        itemView.tvActorName.text = actorVo.name
    }
}