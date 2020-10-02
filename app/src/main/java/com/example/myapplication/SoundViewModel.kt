package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi


class SoundViewModel (private val beatBox: BeatBox) {

    var sound: Sound? = null

    val title: String?
        get() = sound?.name

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }
}



