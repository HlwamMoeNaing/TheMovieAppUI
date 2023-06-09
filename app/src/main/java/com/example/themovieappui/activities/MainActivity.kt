package com.example.themovieappui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieappui.R

import com.example.themovieappui.adapters.ActorAdapter
import com.example.themovieappui.adapters.BannerAdapter
import com.example.themovieappui.adapters.ShowCaseAdapter
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.delegate.BannerViewHolderDelegate
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import com.example.themovieappui.delegate.ShowCaseViewHolderDelegate
import com.example.themovieappui.viewpods.ActorListViewPod
import com.example.themovieappui.viewpods.MovieListViewPod
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowCaseViewHolderDelegate,
    MovieViewHolderDelegate {
    lateinit var mAdapter: BannerAdapter
    lateinit var mShowCaseAdapter: ShowCaseAdapter
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod
    private val mMovieModel: MovieModel = MovieModelImpl
    private var mGenres: List<GenreVo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolBar()
        setUpViewPod()
        setUpAdapter()
        //setUpGenreTabLayout()
        setUpListener()
        setUpShowCaseRecyclerView()
        requestData()
    }

    private fun requestData() {
        mMovieModel.getNowPlayingMovies {
            showErrorToast(it)
        }?.observe(this, Observer {
            mAdapter.setData(it)
        })

//        mMovieModel.getPopularMovies(
//            onSuccess = {
//                mBestPopularMovieListViewPod.setNewData(it)
//            },
//            onFailure = {
//                showErrorToast(it)
//            }
//
//        )
        mMovieModel.getPopularMovies {
            showErrorToast(it)
        }?.observe(this, Observer {
            mBestPopularMovieListViewPod.setNewData(it)
        })


//        mMovieModel.getTopRatedMovies(
//            onSuccess = {
//                mShowCaseAdapter.setNewData(it)
//            },
//            onFailure = {
//                showErrorToast(it)
//            }
//        )

        mMovieModel.getTopRatedMovies {
            showErrorToast(it)
        }?.observe(this, Observer {
            mShowCaseAdapter.setNewData(it)
        })

        mMovieModel.getGenre(
            onSuccess = {
                mGenres = it
                setUpGenreTabLayout(it)

                // getMovie by genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }
            },
            onFailure = {
                showErrorToast(it)
            }
        )
        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)


                //  Log.d("testPrint", it[2].profilePath)
            },
            onFailure = {
                showErrorToast(it)
            }
        )
    }

    private fun getMoviesByGenre(genreId: Int) {
        mMovieModel.getMovieByGenre(genreId = genreId.toString(),
            onSuccess = {
                Log.d("@genres", it.toString())
                mMovieByGenreViewPod.setNewData(it)
            },
            onFailure = {
                showErrorToast(it)
            })
    }


    private fun setUpViewPod() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = VPActorList as ActorListViewPod

        mActorListViewPod.mActorAdapter = ActorAdapter()
        mActorListViewPod.setUpActorViewPod(
            backgroundColorRef = R.color.colorPrimary,
            titleText = getString(R.string.ll_actor),
            moreTitleText = getString(R.string.ll_more_actors)
        )
        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(this)
    }

    private fun setUpListener() {
        // Genre TabLayout Listener
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_LONG).show()
                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpAdapter() {
        mAdapter = BannerAdapter(this)
        bannerViewPager.adapter = mAdapter
        dotsIndicatorBanner.attachTo(bannerViewPager)

    }

    private fun setUpShowCaseRecyclerView() {
        mShowCaseAdapter = ShowCaseAdapter(this)
        rvShowCases.adapter = mShowCaseAdapter
        rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_two)
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVo>) {
//        dummyGenreList.forEach {
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it
//            tabLayoutGenre.addTab(tab)
//        }
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.menuSearch) {
            Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
           startActivity( MovieSearchActivity.newIntent(this))

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    private fun showErrorToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
}