package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var aCount = 0
    private var bCount = 0
    private var mShowCountA: TextView? = null
    private var mShowCountB: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        mShowCountA = findViewById(R.id.teamScoreA)
        mShowCountB = findViewById(R.id.teamScoreB)

        val nodeA = mShowCountA
        val nodeB = mShowCountB

        if (savedInstanceState != null) {
            aCount = savedInstanceState.getInt("countA")
            if (nodeA != null) {
                nodeA.text = aCount.toString()
            }
            bCount = savedInstanceState.getInt("countB")
            if (nodeB != null) {
                nodeB.text = bCount.toString()
            }
        }

        val saveButton = findViewById<Button>(R.id.save)
        saveButton.setOnClickListener{
            val intent = Intent(this, SaveScores::class.java)
            intent.putExtra("teamAScore",aCount.toString())
            intent.putExtra("teamBScore",bCount.toString())
            Log.d("TAG", "${aCount.toString()}: Team A score")
            Log.d("TAG", "${bCount.toString()}: Team B score")
            Log.d("TAG", "Sending scores to next activity")
            startActivity(intent)
        }

    }

    fun addScore(view: View) {
        Log.d("TAG", "addScore() called")
        when (view.id) {
            (R.id.threePointsA) -> {
                aCount += 3; mShowCountA?.text = aCount.toString(); Log.d(
                    "TAG",
                    aCount.toString()
                )
            }
            (R.id.twoPointsA) -> {
                aCount += 2; mShowCountA?.text = aCount.toString(); Log.d(
                    "TAG",
                    aCount.toString()
                )
            }
            (R.id.freeThrowA) -> {
                aCount += 1; mShowCountA?.text = aCount.toString(); Log.d(
                    "TAG",
                    aCount.toString()
                )
            }

            (R.id.threePointsB) -> {
                bCount += 3; mShowCountB?.text = bCount.toString(); Log.d(
                    "TAG",
                    bCount.toString()
                )
            }
            (R.id.twoPointsB) -> {
                bCount += 2; mShowCountB?.text = bCount.toString(); Log.d(
                    "TAG",
                    bCount.toString()
                )
            }
            (R.id.freeThrowB) -> {
                bCount += 1; mShowCountB?.text = bCount.toString(); Log.d(
                    "TAG",
                    bCount.toString()
                )
            }
            (R.id.reset) -> {
                aCount = 0
                bCount = 0
                mShowCountA?.text = aCount.toString()
                mShowCountB?.text = bCount.toString()
                Log.d("reset", aCount.toString())
                Log.d("reset", bCount.toString())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("countA", aCount)
        outState.putInt("countB", bCount)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("countA")
        savedInstanceState.getInt("countB")

    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy() called")
    }

}
