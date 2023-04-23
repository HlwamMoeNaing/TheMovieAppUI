package com.example.themovieappui.network.dataagent

import android.util.Log
import android.util.Pair
import com.example.themovieappui.data.vos.ActorVo
import com.example.themovieappui.data.vos.GenreVo
import com.example.themovieappui.data.vos.model.MovieVo
import com.example.themovieappui.network.response.GetActorResponse
import com.example.themovieappui.network.response.GetCreditByMovieResponse
import com.example.themovieappui.network.response.GetGenreRespons
import com.example.themovieappui.network.response.MovieListResponse
import com.example.themovieappui.network.service.TheMovieApi
import com.example.themovieappui.util.URLConstant
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl : MovieDataAgent {
//
//    private var mTheMovieApi: TheMovieApi? = null
//
//    init {
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URLConstant.BASEURL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList)
//                    Log.d("@retrofit", movieList.toString())
//                } else {
//                    onFailure(response.message())
//                    Log.d("@retrofit", response.errorBody().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getTopRatedMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList)
//                    Log.d("@retrofit", movieList.toString())
//                } else {
//                    onFailure(response.message())
//                    Log.d("@retrofit", response.errorBody().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVo>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getPopularMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList)
//                    Log.d("@retrofit", movieList.toString())
//                } else {
//                    onFailure(response.message())
//                    Log.d("@retrofit", response.errorBody().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getGenre()?.enqueue(object : Callback<GetGenreRespons> {
//            override fun onResponse(
//                call: Call<GetGenreRespons>,
//                response: Response<GetGenreRespons>
//            ) {
//                if (response.isSuccessful) {
//                    onSuccess(response.body()?.genre ?: listOf())
//                } else {
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetGenreRespons>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getMovieByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVo>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMovieByGenre(genreId)?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val movieList = response.body()?.results ?: listOf()
//                    onSuccess(movieList)
//                } else {
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//
//        })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getActors()?.enqueue(object :Callback<GetActorResponse>{
//            override fun onResponse(
//                call: Call<GetActorResponse>,
//                response: Response<GetActorResponse>
//            ) {
//               if(response.isSuccessful){
//                   val actorList = response.body()?.results ?: listOf()
//                   onSuccess(actorList)
//               }else{
//                   onFailure(response.message())
//               }
//            }
//
//            override fun onFailure(call: Call<GetActorResponse>, t: Throwable) {
//               onFailure(t.message ?: "Something Wrong")
//            }
//
//        })
//    }
//
//    override fun getMovieDetails(
//        movieId: String,
//        onSuccess: (MovieVo) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMovieDetails(movieId)?.enqueue(object :Callback<MovieVo>{
//            override fun onResponse(call: Call<MovieVo>, response: Response<MovieVo>) {
//                if(response.isSuccessful){
//                  response.body()?.let {
//                      onSuccess(it)
//                  }
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieVo>, t: Throwable) {
//             onFailure(t.message ?:"Something Wrong")
//            }
//
//        })
//    }
//
//    override fun getCreditByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getCreditByMovie(movieId)?.enqueue(object :Callback<GetCreditByMovieResponse>{
//            override fun onResponse(
//                call: Call<GetCreditByMovieResponse>,
//                response: Response<GetCreditByMovieResponse>
//            ) {
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        onSuccess(Pair(it.casts ?: listOf(),it.crew ?: listOf()))
//                    }
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetCreditByMovieResponse>, t: Throwable) {
//                onFailure(t.message ?: "Something Wrong")
//            }
//
//        })
//    }
//}