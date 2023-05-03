package com.example.movieapp.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.movieapp.mvvm.viewmodel.MainViewModel.MainData

interface MainContract {
    interface MainViewModel {
        fun getValue(): LiveData<MainData>
        fun onPressedButton()
    }
}
