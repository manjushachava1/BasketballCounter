package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Query
import com.example.myapplication.Game
import java.util.*

@Entity(tableName = "table_game")

@Dao
interface GameDao {

    @Query("SELECT * FROM table_game")
    fun getGames(): LiveData<List<Game>>

    @Query("SELECT * FROM table_game")
    fun getGame(id: UUID): LiveData<Game?>
}

