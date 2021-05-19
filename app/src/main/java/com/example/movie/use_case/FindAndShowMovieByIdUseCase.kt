package com.example.movie.use_case

import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single

interface FindAndShowMovieByIdUseCase {

    fun getMovieById(id: Int):Completable
    fun deleteMovie():Completable
    fun showMovieInfo(id: Int): Single<MovieEntity>
}
class FindAndShowMovieByIdUseCaseImpl(
        private val repository: MovieRepository

):FindAndShowMovieByIdUseCase{
    override fun getMovieById(id: Int): Completable {
        return repository.getMovieById(id)


    }

    override fun deleteMovie(): Completable {
        return repository.deleteMovie()


    }

    override fun showMovieInfo(id: Int): Single<MovieEntity> {
        return repository.showMovieInfo(id)


    }

}