package com.example.movie.repository

import com.example.movie.data_source.DataSource
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.remote_data_source.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovie(): Completable
    fun deleteMovie(): Completable
    fun showPopularMovie(): Single<List<MovieEntity>>
    fun getMovieById(id: Int): Completable
    fun showMovieInfo(id: Int): Single<MovieEntity>


}

class MovieRepositoryImpl(

        private val dataSource: DataSource,
        private val remoteDataSource: RemoteDataSource

) : MovieRepository {
    override fun getPopularMovie(): Completable {
        return remoteDataSource.getPopularMovie()
                .flatMapCompletable { list ->
                    saveListMovie(itemsMovie = list, isCached = true)
                }
    }

    override fun deleteMovie(): Completable {
        return dataSource.deleteMovie()
    }

    override fun showPopularMovie(): Single<List<MovieEntity>> {
        return dataSource.showPopularMovie()

    }


    override fun getMovieById(id: Int): Completable {
        return remoteDataSource.getMovieById(id)
                .flatMapCompletable { movie ->
                    saveMovieInfo(movieEntity = movie, isCached = true)
                }
    }

    override fun showMovieInfo(id: Int): Single<MovieEntity> {
        return dataSource.showMovieInfo(id)


    }

    private fun saveListMovie(
            itemsMovie: List<MovieEntity>, isCached: Boolean
    ): Completable {
        if (isCached) {
            return dataSource.insertListMovie(itemsMovie)
        } else {
            return dataSource.insertAndClearList(itemsMovie)
        }

    }

    private fun saveMovieInfo(
            movieEntity: MovieEntity, isCached: Boolean
    ): Completable {
        if (isCached) {
            return dataSource.insertMovieInfo(movieEntity)
        } else {
            return dataSource.insertAndClearMovieInfo(movieEntity)
        }
    }
}

