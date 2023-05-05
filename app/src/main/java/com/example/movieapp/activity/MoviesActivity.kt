package com.example.movieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.database.MovieDataBaseImpl
import com.example.movieapp.database.MoviesRoomDataBase
import com.example.movieapp.databinding.ActivityMoviesBinding
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvm.model.MoviesModel
import com.example.movieapp.mvvm.viewmodel.MoviesViewModel
import com.example.movieapp.mvvm.viewmodel.factory.ViewModelFactory
import com.example.movieapp.service.MovieClient
import com.example.movieapp.service.MovieRequestGenerator
import com.example.movieapp.service.MovieServiceImpl

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var viewModel: MoviesContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBase: MoviesRoomDataBase by lazy {
            Room
                .databaseBuilder(this, MoviesRoomDataBase::class.java, "Movie-DataBase")
                .build()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MoviesModel(
                        MovieServiceImpl(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDataBaseImpl(dataBase.movieDao()),
                    ),
                ),
            ),
        )[MoviesViewModel::class.java]

        binding.moviesActivityBackButton.setOnClickListener { viewModel.onBackButtonPressed() }
        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MoviesViewModel.MovieData) {
        when (data.status) {
            MoviesViewModel.MovieStatus.SHOW_BUTTON_PRESSED -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
            MoviesViewModel.MovieStatus.BACK_BUTTON_PRESSED -> {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}
