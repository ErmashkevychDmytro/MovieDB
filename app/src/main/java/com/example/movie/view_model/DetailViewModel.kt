package com.example.movie.view_model

import androidx.lifecycle.MutableLiveData
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.use_case.FindAndShowMovieByIdUseCase
import io.reactivex.rxkotlin.plusAssign

class DetailViewModel (
        private val findAndShowMovieByIdUseCase: FindAndShowMovieByIdUseCase
        ):BaseViewModel() {

    val gotMovie = MutableLiveData<MovieEntity>()

    fun getMovie(id: Int) {
        compositeDisposable += findAndShowMovieByIdUseCase.getMovieById(id = id)
                .subscribe({
                    showMovie(id)
                }, {
                    it.printStackTrace()

                })
    }

    private fun showMovie(id: Int) {
        compositeDisposable += findAndShowMovieByIdUseCase.showMovieInfo(id)
                .subscribe({ movie ->
                    gotMovie.value = movie
                }, {
                    it.printStackTrace()
                })
    }
}
