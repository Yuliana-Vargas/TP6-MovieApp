package com.example.movieapp.service.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("vote_average")
    var vote_average: Double,
)
