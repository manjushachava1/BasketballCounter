package com.example.myapplication

import java.util.*


data class Game(val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var date: Date = Date(),
                var scoreA: Int = (0..100).random(),
                var scoreB: Int = (0..100).random(),
                var teamA: String = "Team: " + UUID.randomUUID().toString().substring(0,5),
                var teamB: String = "Team: " + UUID.randomUUID().toString().substring(0,5))

