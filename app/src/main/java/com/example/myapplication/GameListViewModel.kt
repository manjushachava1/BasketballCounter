package com.example.myapplication

import androidx.lifecycle.ViewModel

class GameListViewModel : ViewModel(){

    val games = mutableListOf<Game>()

    init {
        for (i in 0 until 100) {
            val game = Game()
            game.title = "Game #$i"
            games += game
        }
    }
}