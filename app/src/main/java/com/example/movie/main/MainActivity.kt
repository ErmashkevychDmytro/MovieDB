package com.example.movie.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.MovieApp
import com.example.movie.R
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.main.adapter.ItemMovieListener
import com.example.movie.main.adapter.RecyclerViewMainAdapter
import com.example.movie.presentation.detail.DetailActivity
import com.example.movie.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ItemMovieListener {
    var mainViewModel: MainViewModel? = null @Inject set
    val adapterListMovie = RecyclerViewMainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MovieApp).getViewModelComponent().inject(this)

        recyclerviewListMovie.adapter = adapterListMovie
        recyclerviewListMovie.layoutManager = LinearLayoutManager(this)

        showList()
    }

    private fun showList(){

        mainViewModel?.getListPopularMovie()

        mainViewModel?.listMovie?.observe ( this, Observer<List<MovieEntity>>{
           adapterListMovie.setData(it)
        })
    }

    override fun onClickMovie(movie: MovieEntity) {
        val nextScreen = Intent(this,DetailActivity::class.java)
            .putExtra("movie_id", "${movie.id}")
        startActivity(nextScreen)
    }


}