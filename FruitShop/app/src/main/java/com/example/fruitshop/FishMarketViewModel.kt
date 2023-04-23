package com.example.fruitshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FishMarketViewModel: ViewModel() {


    val SALMON_PRICE = 7.0
    val GILT_HEAD_BREAM_PRICE = 6.0
    val SEA_BASS_PRICE = 5.0
    val RED_MULLET_PRICE = 2.0


    private val _fish = MutableLiveData<String>()
    val fish: LiveData<String>
        get() = _fish

    private val _totalFish = MutableLiveData<Double>()
    val totalFish: LiveData<Double>
        get() = _totalFish

    private val _salmon = MutableLiveData<Int>()
    val salmon: LiveData<Int>
        get() = _salmon

    private val _gilt_head_bream = MutableLiveData<Int>()
    val gilt_head_bream: LiveData<Int>
        get() = _gilt_head_bream

    private val _sea_bass = MutableLiveData<Int>()
    val sea_bass: LiveData<Int>
        get() = _sea_bass

    private val _red_mullet = MutableLiveData<Int>()
    val red_mullet: LiveData<Int>
        get() = _red_mullet



    fun addFish(quantity_number: Int, context: FishMarketFragment){
        val number: Int

        if(_fish.value == context.getString(R.string.salmon)){
            number = _salmon.value ?: 0
            _salmon.value = number + quantity_number
        }else if(_fish.value == (context.getString(R.string.gilt_head_bream))){
            number = _gilt_head_bream.value ?: 0
            _gilt_head_bream.value = number + quantity_number
        }else if(_fish.value == context.getString(R.string.sea_bass)){
            number = _sea_bass.value ?: 0
            _sea_bass.value = number + quantity_number
        }else if(_fish.value == context.getString(R.string.red_mullet)){
            number = _red_mullet.value ?: 0
            _red_mullet.value = number + quantity_number
        }
    }

    fun deleteItemFish(){
        _salmon.value = 0
        _gilt_head_bream.value = 0
        _sea_bass.value = 0
        _red_mullet.value = 0
        _totalFish.value = 0.0
        //ponemos a 0 el seekBar
    }


    fun calculatePriceFish(){
        val salmonCount = _salmon.value?: 0
        val gilt_head_breamCount = _gilt_head_bream.value?: 0
        val sea_bassCount = _sea_bass.value?: 0
        val red_mulletCount = _red_mullet.value?: 0

        val total = salmonCount * SALMON_PRICE + gilt_head_breamCount * GILT_HEAD_BREAM_PRICE +
                sea_bassCount * SEA_BASS_PRICE + red_mulletCount * RED_MULLET_PRICE
        _totalFish.value = total
    }

    fun calculateFish(quantity_number: Int, context: FishMarketFragment): Double{
        val total: Double
        if(_fish.value == context.getString(R.string.salmon)){
            total = quantity_number * SALMON_PRICE
        }else if(_fish.value == context.getString(R.string.gilt_head_bream)){
            total = quantity_number * GILT_HEAD_BREAM_PRICE
        }else if(_fish.value == context.getString(R.string.sea_bass)){
            total = quantity_number * SEA_BASS_PRICE
        }else{
            total = quantity_number * RED_MULLET_PRICE
        }
        return total
    }

    fun saveFish(fish: String){
        _fish.value = fish
    }

    fun getTotalFish(): Double {
        val totalFish = _totalFish.value?:0.0
        return totalFish
    }


}