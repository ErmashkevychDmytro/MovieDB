package com.example.movie.remote_data_source


import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.remote_data_source.pojo.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    companion object {
        const val API_KEY = "b581d6ee89ab3cabae62530b708351b2"

    }

    // Movies

    @GET("movie/popular")
    fun getPopularMovie(
            @Query("api_key") apiKey: String = API_KEY
    ): Single<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieById(
            @Query("api_key") apiKey: String = API_KEY,
            @Path("movie_id") id: Int

    ): Single<MovieEntity>
}


