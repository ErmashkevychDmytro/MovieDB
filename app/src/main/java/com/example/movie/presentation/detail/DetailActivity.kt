package com.example.movie.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movie.MovieApp
import com.example.movie.R
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.view_model.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    var detailViewModel: DetailViewModel? = null @Inject set
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (application as MovieApp).getViewModelComponent().inject(this)

        writeMovieInfo()
    }

    private fun writeMovieInfo() {
val movieId = intent.getIntExtra("movie_id",-1)
        if(movieId !=-1){
            detailViewModel?.getMovie(movieId)

            detailViewModel?.gotMovie?.observe(this, Observer<MovieEntity>{
                movie ->
                textTitleDetail.text = movie.title
                textGenreDetail.text = movie.overview.toString()
                textvoteAverageDetail.text =movie.voteAverage
                Glide.with(this)
                    .load(IMAGE_BASE + movie.posterPath)
                    .into(imageMoviePosterDetail)
            })
        }
    }
}
