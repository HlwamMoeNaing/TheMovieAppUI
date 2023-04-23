package com.example.themovieappui.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieappui.adapters.ActorAdapter
import com.example.themovieappui.data.vos.ActorVo
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mActorAdapter: ActorAdapter
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpActorViewPod(backgroundColorRef:Int,titleText:String,moreTitleText:String){
        setUpActorRecycler()
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorRef))
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecycler() {
        mActorAdapter = ActorAdapter()
        rvBestActor.adapter = mActorAdapter
        rvBestActor.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setData(actors:List<ActorVo>){
        mActorAdapter.setData(actors)
    }

}