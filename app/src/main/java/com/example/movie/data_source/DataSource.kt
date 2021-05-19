package com.example.movie.data_source

import com.example.movie.data_source.database.MovieDataBase
import com.example.movie.data_source.database.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface DataSource {
    fun deleteMovie(): Completable
    fun showPopularMovie(): Single<List<MovieEntity>>
    fun insertListMovie(movieList: List<MovieEntity>): Completable
    fun showMovieInfo(id : Int):Single<MovieEntity>
    fun insertMovieInfo(movie:MovieEntity):Completable
    fun insertAndClearList(movieList: List<MovieEntity>):Completable
    fun insertAndClearMovieInfo(movie:MovieEntity):Completable

}

class DataSourceImpl(private val movieDataBase: MovieDataBase) : DataSource {
    override fun deleteMovie(): Completable = Completable.fromAction {
        movieDataBase.MovieDao().deleteMovie()

    }

    override fun showPopularMovie(): Single<List<MovieEntity>> =
        movieDataBase.MovieDao().showPopularListMovie()

    override fun insertListMovie(movieList: List<MovieEntity>): Completable =
        Completable.fromAction {
            movieDataBase.MovieDao().insertListMovie(movieList)
        }

    override fun showMovieInfo(id: Int): Single<MovieEntity> =
            movieDataBase.MovieDao().showMovieInfo(id = id)

    override fun insertMovieInfo(movie: MovieEntity): Completable =
        Completable.fromAction {
            movieDataBase.MovieDao().insertMovieInfo(movie)
    }

    override fun insertAndClearList(movieList: List<MovieEntity>): Completable =
            Completable.fromAction{
                movieDataBase.MovieDao().insertAndClearList(movieList)
    }

    override fun insertAndClearMovieInfo(movie: MovieEntity): Completable =
        Completable.fromAction{
            movieDataBase.MovieDao().insertAndClearMovieInfo(movie)

    }

}