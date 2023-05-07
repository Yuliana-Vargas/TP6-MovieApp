package com.example.movieapp.database.mapper

import com.example.movieapp.database.entity.MovieEntity
import com.example.movieapp.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    poster_path = poster_path,
    vote_average = vote_average,
)

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> = map { entity ->
    Movie(
        id = entity.id,
        title = entity.title,
        poster_path = entity.poster_path,
        vote_average = entity.vote_average,
    )
}
