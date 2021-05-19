package com.example.movie.view_model

import androidx.lifecycle.MutableLiveData
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.use_case.MovieUseCase
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    val listMovie = MutableLiveData<List<MovieEntity>>()

    private fun showListMovie() {
        compositeDisposable += movieUseCase.showPopularMovie()
            .subscribe({ list ->
                listMovie.value = list
            }, {
                it.printStackTrace()
            })
    }

     fun getListPopularMovie(){
         compositeDisposable += movieUseCase.getPopularMovie()
                         .subscribe({
                     showListMovie()
                 },{
                     it.printStackTrace()

                 })
     }

}
