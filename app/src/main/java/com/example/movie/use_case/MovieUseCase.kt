package com.example.movie.use_case

import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface MovieUseCase {
    fun getPopularMovie(): Completable
    fun deleteMovie(): Completable
    fun showPopularMovie(): Single<List<MovieEntity>>

}

class MovieUseCaseImpl(
    private val repository: MovieRepository
) : MovieUseCase {
    override fun getPopularMovie(): Completable =
        repository.getPopularMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun deleteMovie(): Completable =
        repository.deleteMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun showPopularMovie(): Single<List<MovieEntity>> =
        repository.showPopularMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}