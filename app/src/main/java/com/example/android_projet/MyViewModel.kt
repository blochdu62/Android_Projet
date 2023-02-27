package com.example.android_projet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    //var selectedRadioValue = 0
    var selectedRadioValue: Int = 0
        @JvmName("setSelectedRadioValueKotlin")
        set(value) {
            field = value
        }
    var isEuropeOn: Boolean = false
    var isAsieOn: Boolean = false
    var isAfriqueOn: Boolean = false
    var isOceanieOn: Boolean = false
    var isAmeriqueNordOn: Boolean = false
    var isAmeriqueSudOn: Boolean = false
    var score : Int = 0
    var namePlayer : String ="Guess"

    fun incrementScore(increment: Boolean) {
        if (increment) {
            score++
            Log.d("score", "score = $score")
        } else {
            score=0
        }
    }



    fun setSelectedRadioValue(value: Int) {
        selectedRadioValue = value

    }
    fun setnamePlayer(value: String) {
        namePlayer = value

    }


    fun setIsEuropeOn(value: Boolean) {
        isEuropeOn = value
    }

    fun setIsAsieOn(value: Boolean) {
        isAsieOn = value
    }

    fun setIsAfriqueOn(value: Boolean) {
        isAfriqueOn = value
    }

    fun setIsOceanieOn(value: Boolean) {
        isOceanieOn = value
    }

    fun setIsAmeriqueNordOn(value: Boolean) {
        isAmeriqueNordOn = value
    }

    fun setIsAmeriqueSudOn(value: Boolean) {
        isAmeriqueSudOn = value
    }
}