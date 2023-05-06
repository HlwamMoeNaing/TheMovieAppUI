package com.example.themovieappui.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.interactor.MovieInteractor
import com.example.themovieappui.interactor.MovieInteractorImpl
import com.example.themovieappui.mvp.views.MainView


class MainPresenterImpl : ViewModel(), MainPresenter {
    //view
    var mView: MainView? = null

    //model
    private val mMovieInteractor: MovieInteractor = MovieInteractorImpl

    //states
    private var mGenres: List<GenreVo>? = listOf()


    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //NowPlaying
        mMovieInteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        //Popular
        mMovieInteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }
        //TopRate
        mMovieInteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        //Genre and get movies for first Genre
        mMovieInteractor.getGenre(onSuccess = {
            mGenres = it
            mView?.showGenre(it)
            it.firstOrNull()?.id?.let { firstGenreId ->
                onTapGenre(firstGenreId)
            }
        }, onFailure = {
            mView?.showError(it)
        }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieInteractor.getMovieByGenre(genreId = genreId.toString(), onSuccess = {
                mView?.showMovieByGenre(it)
            }, onFailure = {
                mView?.showError(it)
            })
        }
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }
}