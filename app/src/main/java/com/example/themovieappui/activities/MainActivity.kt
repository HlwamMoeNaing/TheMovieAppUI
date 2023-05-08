package com.example.themovieappui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieappui.R

import com.example.themovieappui.adapters.ActorAdapter
import com.example.themovieappui.adapters.BannerAdapter
import com.example.themovieappui.adapters.ShowCaseAdapter
import com.example.themovieappui.data.model.MovieModelImpl
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieModel
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.delegate.BannerViewHolderDelegate
import com.example.themovieappui.delegate.MovieViewHolderDelegate
import com.example.themovieappui.delegate.ShowCaseViewHolderDelegate
import com.example.themovieappui.mvi.intnets.MainIntent
import com.example.themovieappui.mvi.mvibase.MVIVIew
import com.example.themovieappui.mvi.states.MainState
import com.example.themovieappui.mvi.viewmodel.MainViewModel
import com.example.themovieappui.mvp.presenters.MainPresenter
import com.example.themovieappui.mvp.presenters.MainPresenterImpl
import com.example.themovieappui.mvp.views.MainView
import com.example.themovieappui.viewpods.ActorListViewPod
import com.example.themovieappui.viewpods.MovieListViewPod
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, MVIVIew<MainState>, MovieViewHolderDelegate,
    BannerViewHolderDelegate, ShowCaseViewHolderDelegate {
    lateinit var mAdapter: BannerAdapter
    lateinit var mShowCaseAdapter: ShowCaseAdapter
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod
    //private lateinit var mPresenter: MainPresenter

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setUpPresenter()

        setupViewModel()
        setUpToolBar()
        setUpViewPod()
        setUpAdapter()
        setUpListener()
        setUpShowCaseRecyclerView()

        // mPresenter.onUiReady(this)

        // set initial intent
        setInitialIntents()
        observeState()

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
                //mPresenter.onTapGenre(tab?.position ?: 0)
                mViewModel.processIntent(
                    MainIntent.LoadMovieByGenreIntent(tab?.position ?: 0), this@MainActivity
                )
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
            Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show()
            startActivity(MovieSearchActivity.newIntent(this))

            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun showErrorToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVo>) {
        mAdapter.setData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVo>) {
        mBestPopularMovieListViewPod.setNewData(popularMovies)
    }

    override fun showTopRatedMovies(topRateMovies: List<MovieVo>) {
        mShowCaseAdapter.setNewData(topRateMovies)
    }

    override fun showGenre(genreList: List<GenreVo>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMovieByGenre(movieByGenre: List<MovieVo>) {
        mMovieByGenreViewPod.setNewData(movieByGenre)
    }

    override fun showActor(actors: List<ActorVo>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailScreen(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun showError(errorString: String) {

    }


    //new function for mvi
    private fun observeState() {
        mViewModel.state.observe(this, this::render)
    }

    fun setupViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    fun setInitialIntents() {
        mViewModel.processIntent(MainIntent.LoadAllHomePageData, this)
    }

    override fun render(state: MainState) {
        if (state.errorMessage.isNotEmpty()) {
            showErrorToast(state.errorMessage)
        }
            Log.d("@size",state.nowPlayingMovie.size.toString())
        mAdapter.setData(state.nowPlayingMovie)
        mBestPopularMovieListViewPod.setNewData(state.popularMovie)
        mShowCaseAdapter.setNewData(state.topRatedMovie)
        setUpGenreTabLayout(state.genre)
        mMovieByGenreViewPod.setNewData(state.movieByGenre)
        mActorListViewPod.setData(state.actors)
    }

    override fun onTapMovie(movieId: Int) {

    }

    override fun onTapMovieFromBanner(movieId: Int) {

    }

    override fun onTapMovieFromShowcase(movieId: Int) {

    }
}