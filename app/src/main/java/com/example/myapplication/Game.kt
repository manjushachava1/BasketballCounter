package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Game(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var teamA: String = "Team: " + UUID.randomUUID().toString().substring(0, 5),
    var teamB: String = "Team: " + UUID.randomUUID().toString().substring(0, 5),
    var date: Date = Date(),
    var scoreA: Int = (0..100).random(),
    var scoreB: Int = (0..100).random()
)

