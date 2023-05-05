package com.example.movieapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val model: MoviesContract.Model) : ViewModel(), MoviesContract.ViewModel {
    private val mutableLiveData: MutableLiveData<MovieData> = MutableLiveData()
    override fun getValue(): LiveData<MovieData> = mutableLiveData

    override fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MovieData(MovieStatus.PRESSED_BUTTON, result.data)
                }
                is CoroutineResult.Failure -> {
                    // TODO
                }
            }
        }
    }

    data class MovieData(
        val status: MovieStatus,
        val movies: List<Movie>,
    )

    enum class MovieStatus {
        PRESSED_BUTTON,
    }
}
