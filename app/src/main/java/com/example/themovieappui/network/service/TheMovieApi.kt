package com.example.themovieappui.network.service

import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.network.response.GetActorResponse
import com.example.themovieappui.network.response.GetCreditByMovieResponse
import com.example.themovieappui.network.response.GetGenreRespons
import com.example.themovieappui.network.response.MovieListResponse
import com.example.themovieappui.util.URLConstant
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface TheMovieApi {
    @GET(URLConstant.API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(URLConstant.API_TOP_RATE)
    fun getTopRatedMovies(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(URLConstant.API_POPULAR)
    fun getPopularMovies(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(URLConstant.GET_GENRE)
    fun getGenre(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY
    ): Observable<GetGenreRespons>

    @GET(URLConstant.GET_MOVIE_BY_GENRE)
    fun getMovieByGenre(
        @Query(URLConstant.PARAM_GENRE_ID) genreId: String,
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY
    ): Observable<MovieListResponse>

    @GET(URLConstant.API_GET_ACTOR)
    fun getActors(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ): Observable<GetActorResponse>

    @GET("${URLConstant.API_GET_MOVIE_DETAIL}/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId:String,
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ):Observable<MovieVo>

    @GET("${URLConstant.API_GET_CREDIT_BY_MOVIE}/{movie_id}/credits")
    fun getCreditByMovie(
        @Path("movie_id")movieId: String,
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_PAGE) page: Int = 1
    ):Observable<GetCreditByMovieResponse>


    @GET(URLConstant.API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(URLConstant.PARAM_API_KEY) apiKey: String = URLConstant.MOVIE_API_KEY,
        @Query(URLConstant.PARAM_QUERY) query: String
    ):Observable<MovieListResponse>
}