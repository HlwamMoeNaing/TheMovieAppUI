package com.example.themovieappui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.themovieappui.R
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.mvp.presenters.IBasePresenter
import com.example.themovieappui.mvp.presenters.MovieDetailPresenter
import com.example.themovieappui.mvp.presenters.MovieDetailPresenterImpl
import com.example.themovieappui.mvp.views.MovieDetailView
import com.example.themovieappui.util.URLConstant
import com.example.themovieappui.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_holder_best_actors.view.*


class MovieDetailActivity : AppCompatActivity(),MovieDetailView {
    lateinit var actorViewPod: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod
//    private val mMovieModel: MovieModel = MovieModelImpl

    private lateinit var mPresenter: MovieDetailPresenter


    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpPresenter()
        setUpViewPod()
        setUpListener()
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
           mPresenter.onUiReadyInMovieDetail(this,movieId)
        }
    }

    fun setUpPresenter(){
        mPresenter =ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

//    private fun requestData(movieId: Int) {
////        mMovieModel.getMovieDetails(
////            movieId = movieId.toString(),
////            onSuccess = {
////                bindData(it)
////            },
////            onFailure = {
////                showErrorToast(it)
////
////            }
////        )
//
//        mMovieModel.getMovieDetails(movieId.toString()) {
//            showErrorToast(it)
//        }?.observe(this, Observer {
//            it?.let { movieDetail -> bindData(movieDetail) }
//        })
//
//        mMovieModel.getCreditByMovie(
//
//            movieId = movieId.toString(),
//            onSuccess = {
//                Log.d("@mCrew", it.second.toString())
//                Log.d("@mCast", it.first.toString())
//                actorViewPod.setData(it.first)
//                creatorViewPod.setData(it.second)
//
//            },
//            onFailure = {
//                showErrorToast(it)
//            }
//        )
//
//    }

    private fun bindData(movieVo: MovieVo) {
        var releaseDate = ""
        releaseDate = if (!movieVo.releaseDate.isNullOrEmpty() || movieVo.releaseDate != "") {
            movieVo.releaseDate?.substring(0, 4) ?: ""
        } else {
            "Not Valid Date"
        }
        Glide.with(this)
            .load("${URLConstant.IMAGE_BASE_URL}${movieVo.posterPath}")
            .into(ivMovieDetail)
        tvMovieName.text = movieVo.title ?: ""
        tvReleaseYear.text = releaseDate
        tvRating.text = movieVo.voteAverage?.toString() ?: ""
        movieVo.voteCount?.let {
            tvNumberOfVote.text = "$it VOTES"
        }
        rbRatingDetail.rating = movieVo.getRatingBaseOnFiveStar()
        bindGenres(movieVo, movieVo.genres ?: listOf())
        tvOverview.text = movieVo.overview ?: ""
        tvOriginalTitle.text = movieVo.originalTitle ?: ""
        typeLabel.text = movieVo.getGenresAsCommaSeparatedString()
        tvProductionLabel.text = movieVo.getProductionCountryAsCommaSeparatedString()
        tvPremier.text = releaseDate
        tvDescription.text = movieVo.overview ?: ""


    }

    private fun bindGenres(
        movieVo: MovieVo,
        genre: List<GenreVo>
    ) {
        Log.d("@testGenre", genre.toString())
        movieVo.genres?.count()?.let {
            tvFirstGenre.text = genre.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genre.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genre.getOrNull(2)?.name ?: ""
            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }

    }

    private fun setUpViewPod() {
        actorViewPod = vpActors as ActorListViewPod
        actorViewPod.setUpActorViewPod(
            backgroundColorRef = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorViewPod = vpCreators as ActorListViewPod
        creatorViewPod.setUpActorViewPod(
            backgroundColorRef = R.color.colorPrimary,
            titleText = getString(R.string.ll_creator),
            moreTitleText = getString(R.string.ll_more_creators)
        )
    }

    private fun showErrorToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

    override fun showMovieDetail(movieVo: MovieVo) {
       bindData(movieVo)
    }

    override fun showCreditByMovie(cast: List<ActorVo>, crew: List<ActorVo>) {
     actorViewPod.setData(cast)
        creatorViewPod.setData(crew)
    }

    override fun navigateBack() {
      finish()
    }

    override fun showError(errorString: String) {
        showErrorToast(errorString)
    }
}