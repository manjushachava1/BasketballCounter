package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SaveScores : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_scores)

        Log.d("TAG", "Entered next activity")

        val intent = intent
        val teamAScore=intent.getStringExtra("teamAScore")
        val teamBScore=intent.getStringExtra("teamBScore")

        val resultA = findViewById<TextView>(R.id.scoreA)
        val resultB = findViewById<TextView>(R.id.scoreB)

        resultA.text = "$teamAScore"
        resultB.text = "$teamBScore"

        val toast = Toast.makeText(applicationContext, "Game Score Saved!", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM,0,0)
        toast.show()
        Log.d("TAG", "Showed 'Game Score Saved!' toast")

    }

    override fun onBackPressed() {
        Toast.makeText(this, "Home Screen!", Toast.LENGTH_SHORT).show()
        super.onBackPressed()
        Log.d("TAG", "Back to Main Activity")
        Log.d("TAG", "Showed 'Back to Main Activity' toast")
    }
}