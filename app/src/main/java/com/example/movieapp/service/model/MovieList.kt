package com.example.movieapp.service.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<Movie>,
)
