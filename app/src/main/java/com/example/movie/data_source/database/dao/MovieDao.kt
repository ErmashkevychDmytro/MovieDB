package com.example.movie.data_source.database.dao

import androidx.room.*
import com.example.movie.data_source.database.entity.ActorEntity
import com.example.movie.data_source.database.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("DELETE FROM movie")
    fun deleteMovie()

    @Query("SELECT * FROM movie")
    fun showPopularListMovie():Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListMovie(movielist: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE id LIKE :id")
    fun showMovieInfo(id : Int):Single<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieInfo(movie:MovieEntity)

    @Transaction
    fun insertAndClearList(
            movielist: List<MovieEntity>
    ){
        deleteMovie()
        insertListMovie(movielist)
    }
    @Transaction
    fun insertAndClearMovieInfo(
            movie:MovieEntity
    ){
        deleteMovie()
        insertMovieInfo(movie)
    }


    //Actor
    @Query("DELETE FROM actor")
    fun deleteActor()

    @Query("SELECT * FROM actor")
    fun showMovieCast():Single<List<ActorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActor(actorList: List<ActorEntity>)

}