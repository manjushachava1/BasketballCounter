package com.example.myapplication

import androidx.lifecycle.ViewModel

class GameListViewModel : ViewModel(){

    private val gameRepository = GameRepository.get()
    val gameListLiveData = gameRepository.getGames()

}