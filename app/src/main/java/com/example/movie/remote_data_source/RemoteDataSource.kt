package com.example.movie.remote_data_source

import com.example.movie.data_source.database.entity.MovieEntity
import io.reactivex.Single

interface RemoteDataSource {
    fun getPopularMovie():Single<List<MovieEntity>>
    fun getMovieById(id:Int):Single<MovieEntity>


}

class RemoteDataSourceImpl(private val movieApi: MovieApi):RemoteDataSource{
    override fun getPopularMovie(): Single<List<MovieEntity>> {
        return movieApi.getPopularMovie().map { it.result }

    }

    override fun getMovieById(id: Int): Single<MovieEntity> {
    return movieApi.getMovieById(id = id)
    }

}
