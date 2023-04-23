package com.example.fruitshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasketViewModel: ViewModel() {

    private val _totalFinal = MutableLiveData<Double>()
    val totalFinal: LiveData<Double>
        get() = _totalFinal

    fun calculatePriceFinal(totalFruits:Double, totalFish:Double, totalMeat:Double, totalSport:Double){
        val total = totalFruits + totalFish + totalMeat + totalSport
        _totalFinal.value = total
    }

}