package com.example.android_projet

import android.util.Log
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    var selectedRadioValue: Int = 0
        @JvmName("setSelectedRadioValueKotlin")
        set(value) {
            field = value
        }
    var isEuropeOn: Boolean = false
    var isAsiaOn: Boolean = false
    var isAfricaOn: Boolean = false
    var isOceaniaOn: Boolean = false
    var isNorthAmericaOn: Boolean = false
    var isSouthAmericaOn: Boolean = false
    var score : Int = 0

    fun incrementScore(increment: Boolean) {
        if (increment) {
            score++
            Log.d("score", "score = $score")
        } else {
            score=0
        }
    }
    fun incrementScore2(increment: Boolean) {
        if (increment) {
            score=score+2
            Log.d("score", "score = $score")
        } else {
            score=0
        }
    }
    fun incrementScore3(increment: Boolean) {
        if (increment) {
            score=score+3
            Log.d("score", "score = $score")
        } else {
            score=0
        }
    }



}