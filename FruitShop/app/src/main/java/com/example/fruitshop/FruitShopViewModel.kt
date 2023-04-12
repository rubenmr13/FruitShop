package com.example.fruitshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FruitShopViewModel: ViewModel() {

    val APPLE_PRICE = 0.5
    val PEAR_PRICE = 0.4
    val ORANGE_PRICE = 0.25
    val PLUM_PRICE = 0.09

    //lateinit var fruit : String //guarda la fruta seleccionada en el spinner


    private val _fruit = MutableLiveData<String>()
    val fruit: LiveData<String>
        get() = _fruit

    private val _totalFruit = MutableLiveData<Double>()
    val totalFruit: LiveData<Double>
        get() = _totalFruit

    private val _apple = MutableLiveData<Int>()
    val apple: LiveData<Int>
        get() = _apple

    private val _pear = MutableLiveData<Int>()
    val pear: LiveData<Int>
        get() = _pear

    private val _orange = MutableLiveData<Int>()
    val orange: LiveData<Int>
        get() = _orange

    private val _plum = MutableLiveData<Int>()
    val plum: LiveData<Int>
        get() = _plum


    fun deleteItemFruit(){
        _apple.value = 0
        _pear.value = 0
        _orange.value = 0
        _plum.value = 0
        _totalFruit.value = 0.0
         //ponemos a 0 el seekBar
    }

    fun addFruit(quantity_number: Int, context: FruitShopFragment){
        val number: Int

        if(_fruit.value == context.getString(R.string.apple)){
            number = _apple.value ?: 0
            _apple.value = number + quantity_number
        }else if(_fruit.value == (context.getString(R.string.pear))){
            number = _pear.value ?: 0
            _pear.value = number + quantity_number
        }else if(_fruit.value == context.getString(R.string.orange)){
            number = _orange.value ?: 0
            _orange.value = number + quantity_number
        }else if(_fruit.value == context.getString(R.string.plum)){
            number = _plum.value ?: 0
            _plum.value = number + quantity_number
        }
    }

    fun calculatePriceFruit(){
        val appleCount = _apple.value?: 0
        val pearCount = _pear.value?: 0
        val orangeCount = _orange.value?: 0
        val plumCount = _plum.value?: 0

        val total = appleCount * APPLE_PRICE + pearCount * PEAR_PRICE +
                orangeCount * ORANGE_PRICE + plumCount * PLUM_PRICE
        _totalFruit.value = total
    }

    fun saveFruit(fruits: String){
        _fruit.value = fruits
    }

    fun calculateFruit(quantity_number: Int, context: FruitShopFragment): Double{
        val total: Double
        if(_fruit.value == context.getString(R.string.apple)){
            total = quantity_number * APPLE_PRICE
        }else if(_fruit.value == context.getString(R.string.pear)){
            total = quantity_number * PEAR_PRICE
        }else if(_fruit.value == context.getString(R.string.orange)){
            total = quantity_number * ORANGE_PRICE
        }else{
            total = quantity_number * PLUM_PRICE
        }
        return total
    }




    /////////////////////////// fish market

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


    /////// BUTCHER SHOP

    val COW_PRICE = 3.0
    val CHICKEN_PRICE = 4.5
    val PIG_PRICE = 1.0
    val MINCE_PRICE = 4.0


    private val _meat = MutableLiveData<String>()
    val meat: LiveData<String>
        get() = _meat

    private val _totalMeat = MutableLiveData<Double>()
    val totalMeat: LiveData<Double>
        get() = _totalMeat

    private val _cow = MutableLiveData<Int>()
    val cow: LiveData<Int>
        get() = _cow

    private val _chicken = MutableLiveData<Int>()
    val chicken: LiveData<Int>
        get() = _chicken

    private val _pig = MutableLiveData<Int>()
    val pig: LiveData<Int>
        get() = _pig

    private val _mince = MutableLiveData<Int>()
    val mince: LiveData<Int>
        get() = _mince


    fun addMeat(quantity_number: Int, context: ButcherShopFragment){
        val number: Int

        if(_meat.value == context.getString(R.string.cow)){
            number = _cow.value ?: 0
            _cow.value = number + quantity_number
        }else if(_meat.value == (context.getString(R.string.chicken))){
            number = _chicken.value ?: 0
            _chicken.value = number + quantity_number
        }else if(_meat.value == context.getString(R.string.pig)){
            number = _pig.value ?: 0
            _pig.value = number + quantity_number
        }else if(_meat.value == context.getString(R.string.mince)){
            number = _mince.value ?: 0
            _mince.value = number + quantity_number
        }
    }


    fun deleteItemMeat(){
        _cow.value = 0
        _chicken.value = 0
        _pig.value = 0
        _mince.value = 0
        _totalMeat.value = 0.0
    }


    fun calculatePriceMeat(){
        val cowCount = _cow.value?: 0
        val chickenCount = _chicken.value?: 0
        val pigCount = _pig.value?: 0
        val minceCount = _mince.value?: 0

        val total = cowCount * COW_PRICE + chickenCount * CHICKEN_PRICE +
                pigCount * PIG_PRICE + minceCount * MINCE_PRICE
        _totalMeat.value = total
    }

    fun calculateMeat(quantity_number: Int, context: ButcherShopFragment): Double{
        val total: Double
        if(_meat.value == context.getString(R.string.cow)){
            total = quantity_number * COW_PRICE
        }else if(_meat.value == context.getString(R.string.chicken)){
            total = quantity_number * CHICKEN_PRICE
        }else if(_meat.value == context.getString(R.string.pig)){
            total = quantity_number * PIG_PRICE
        }else{
            total = quantity_number * MINCE_PRICE
        }
        return total
    }

    fun saveMeat(meat: String){
        _meat.value = meat
    }


    ////// BASKET
    private val _totalFinal = MutableLiveData<Double>()
    val totalFinal: LiveData<Double>
        get() = _totalFinal

    fun calculatePriceFinal(){
        val totalFruits = _totalFruit.value ?: 0.0
        val totalFish = _totalFish.value ?: 0.0
        val totalMeat = _totalMeat.value ?: 0.0
        val total = totalFruits + totalFish + totalMeat

        _totalFinal.value = total
    }


}