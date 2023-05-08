package com.example.themovieappui.mvi.mvibase

interface MVIVIew<S:MVIState> {
    fun render(state:S)
}