package com.example.movieapp.service

import com.example.movieapp.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieClient {
    @GET("/3/movie/popular")
    fun getData(): Call<MovieList>
}
