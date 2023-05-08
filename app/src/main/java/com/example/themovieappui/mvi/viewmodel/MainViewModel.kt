package com.example.themovieappui.mvi.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovieappui.mvi.intnets.MainIntent
import com.example.themovieappui.mvi.mvibase.MVIViewModel
import com.example.themovieappui.mvi.processors.MainProcessor
import com.example.themovieappui.mvi.states.MainState

class MainViewModel(override var state: MutableLiveData<MainState> = MutableLiveData(MainState.idle())) :
    MVIViewModel<MainState, MainIntent>, ViewModel() {

    private val mProcessor = MainProcessor


    override fun processIntent(intent: MainIntent, lifecycleOwner: LifecycleOwner) {
        when (intent) {
            MainIntent.LoadAllHomePageData -> {
                state.value?.let {
                    mProcessor.loadAllHomePageData(previousState = it)
                        .observe(lifecycleOwner) { newState ->
                            state.postValue(newState)
                            if (newState.movieByGenre.isEmpty()) {
                                processIntent(MainIntent.LoadMovieByGenreIntent(0), lifecycleOwner)
                            }

                        }
                }
            }


            is MainIntent.LoadMovieByGenreIntent -> {
                state.value?.let {
                    val genreId = it.genre.getOrNull(intent.genrePosition)?.id ?: 0
                    mProcessor.loadMovieByGenre(
                        genreId = genreId,
                        previousState = it
                    ).observe(lifecycleOwner, state::postValue)

                }
            }
        }
    }
}