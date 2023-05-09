package com.example.movieapp.database.mapper

import com.example.movieapp.database.entity.MovieEntity
import com.example.movieapp.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
)

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> = map { entity ->
    Movie(
        id = entity.id,
        title = entity.title,
        posterPath = entity.posterPath,
        voteAverage = entity.voteAverage,
    )
}
