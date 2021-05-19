package com.example.movie.data_source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data_source.database.entity.GenreEntity
import io.reactivex.Single


@Dao
interface GenreDao {

    @Query("DELETE FROM genre")
    fun deleteGenre()

    @Query("SELECT * FROM genre")
    fun showListGenre(): Single<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListGenre(genrelist: List<GenreEntity>)
    }
