package com.example.movieapp.mvvm.model

import com.example.movieapp.database.MovieDataBase
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.service.MovieService
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult

class MoviesModel(
    private val service: MovieService,
    private val database: MovieDataBase,
) : MoviesContract.Model {
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                if (movies.data.results.isNotEmpty()) {
                    database.insertMovies(movies.data.results)
                    CoroutineResult.Success(database.getAllMovies())
                } else {
                    CoroutineResult.Failure(Exception())
                }
            }

            is CoroutineResult.Failure -> {
                if (database.getAllMovies().isNotEmpty()) {
                    CoroutineResult.Success(database.getAllMovies())
                } else {
                    CoroutineResult.Failure(Exception())
                }
            }
        }
    }
}
