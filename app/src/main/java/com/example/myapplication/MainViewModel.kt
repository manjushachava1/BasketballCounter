package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    var initialCountA = 0
        private set
    var initialCountB = 0
        private set
    val currentCountA: Int
        get() {
            initialCountA += 1
            return initialCountA
        }
    val currentCountB: Int
        get() {
            initialCountB += 1
            return initialCountB
        }
}