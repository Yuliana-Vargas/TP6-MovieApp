package com.example.movieapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey var id: Int,
    var title: String,
    var posterPath: String,
    var voteAverage: Double,
)
