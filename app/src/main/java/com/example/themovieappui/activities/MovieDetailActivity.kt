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
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.mvvm.MovieDetailViewModel
import com.example.themovieappui.util.URLConstant
import com.example.themovieappui.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_holder_best_actors.view.*


class MovieDetailActivity : AppCompatActivity() {
    lateinit var actorViewPod: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod
//    private val mMovieModel: MovieModel = MovieModelImpl

    private lateinit var mViewModel:MovieDetailViewModel


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

        setUpViewPod()
        setUpListener()
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
          setUpViewModel(it)
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        mViewModel.movieDetailLiveData?.observe(this){
            it?.let { movie->bindData(movie) }
        }
        mViewModel.castLiveData.observe(this,actorViewPod::setData)
        mViewModel.crewLiveData.observe(this,creatorViewPod::setData)
    }

private fun setUpViewModel(movieId: Int){
    mViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
    mViewModel.getInitialData(movieId)
}

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }



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
}