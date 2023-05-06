package com.example.themovieappui.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieappui.data.vos.model.MovieVo
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieVo?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movieVo: MovieVo?)

    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<MovieVo>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieBYId(movieId:Int):LiveData<MovieVo?>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieBYIdOneTime(movieId:Int):MovieVo?

    @Query("SELECT * FROM movies WHERE type=:type")
    fun getMovieByType(type:String):LiveData<List<MovieVo>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieByTypeFlowable(type:String):Flowable<List<MovieVo>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieByIdFlowable(movieId:Int):Flowable<MovieVo?>
}