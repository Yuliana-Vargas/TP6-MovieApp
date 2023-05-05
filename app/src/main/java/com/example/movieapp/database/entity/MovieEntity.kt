package com.example.movieapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey var id: Int,
    var title: String,
    var poster_path: String,
    var vote_average: Double,
)
