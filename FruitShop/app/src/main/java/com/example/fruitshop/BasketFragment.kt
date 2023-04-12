package com.example.fruitshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.fruitshop.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    //private lateinit var fruitShopViewModel: FruitShopViewModel

    private val fruitShopViewModel: FruitShopViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        //binding = FragmentBasketBinding.inflate(inflater, container, false)
        //fruitShopViewModel = ViewModelProvider(this).get(FruitShopViewModel::class.java)

        binding.viewModel = fruitShopViewModel

        fruitViews(binding.appleText, binding.pearText, binding.orangeText, binding.plumText, binding.appleImage,
            binding.pearImage, binding.orangeImage, binding.plumImage)

        fishViews(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
            binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage)

        meatViews(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
            binding.chickenImage, binding.pigImage, binding.minceImage)


        totalView()


        binding.deleteBasket.setOnClickListener{
            fruitShopViewModel.deleteItemFruit()
            fruitViews(binding.appleText, binding.pearText, binding.orangeText , binding.plumText,binding.appleImage, binding.pearImage,
                binding.orangeImage, binding.plumImage)
            fruitShopViewModel.deleteItemFish()
            fishViews(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage)
            fruitShopViewModel.deleteItemMeat()
            meatViews(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                binding.chickenImage, binding.pigImage, binding.minceImage)
            totalView()
        }



        return binding.root
    }


    fun activeViews(item_text: TextView, item_image: ImageView){
        item_text.visibility = View.VISIBLE //mostramos la vista
        item_image.visibility = View.VISIBLE
    }

    fun desactiveViews(item_text: TextView, item_image: ImageView){
        item_text.visibility = View.GONE //mostramos la vista
        item_image.visibility = View.GONE
    }


    //funcion para mostrar las views cuando cambia la orientacion
    @SuppressLint("SetTextI18n")
    fun fruitViews(apple_text: TextView, pear_text: TextView, orange_text: TextView,
                   plum_text: TextView, apple_image: ImageView, pear_image: ImageView,
                   orange_image: ImageView, plum_image: ImageView){
        if((fruitShopViewModel.apple.value ?: 0) > 0){
            activeViews(apple_text, apple_image)
            binding.appleText.text = getString(R.string.apple_text) + " " + fruitShopViewModel.apple.value.toString()
            ///apple_text.setText(getString(R.string.apple_text) + " " +fruitShopViewModel.apple.toString()) ////aqui hacemos lo del apple
        }else{
            desactiveViews(apple_text, apple_image)
        }
        if((fruitShopViewModel.pear.value ?: 0) > 0){
            activeViews(pear_text, pear_image)
            binding.pearText.text = getString(R.string.pear_text) + " " + fruitShopViewModel.pear.value.toString()
        }else{
            desactiveViews(pear_text, pear_image)
        }
        if((fruitShopViewModel.orange.value ?: 0) > 0){
            activeViews(orange_text, orange_image)
            binding.orangeText.text = getString(R.string.orange_text) + " " + fruitShopViewModel.orange.value.toString()
        }else{
            desactiveViews(orange_text, orange_image)
        }
        if((fruitShopViewModel.plum.value ?: 0) > 0){
            activeViews(plum_text, plum_image)
            binding.plumText.text = getString(R.string.plum_text) + " " + fruitShopViewModel.plum.value.toString()
        }else{
            desactiveViews(plum_text, plum_image)
        }
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    fun fishViews(salmon_text: TextView, gilt_head_bream_text: TextView, sea_bass_text: TextView,
                  red_mullet_text: TextView, salmon_image: ImageView, gilt_head_bream_image: ImageView,
                  sea_bass_image: ImageView, red_mullet_image: ImageView){
        if((fruitShopViewModel.salmon.value ?: 0) > 0){
            activeViews(salmon_text, salmon_image)
            binding.salmonText.text = getString(R.string.salmon_text) + " " + fruitShopViewModel.salmon.value.toString()
        }else{
            desactiveViews(salmon_text, salmon_image)
        }
        if((fruitShopViewModel.gilt_head_bream.value ?: 0) > 0){
            activeViews(gilt_head_bream_text, gilt_head_bream_image)
            binding.giltHeadBreamText.text = getString(R.string.gilt_head_bream_text) + " " + fruitShopViewModel.gilt_head_bream.value.toString()
        }else{
            desactiveViews(gilt_head_bream_text, gilt_head_bream_image)
        }
        if((fruitShopViewModel.sea_bass.value ?: 0) > 0){
            activeViews(sea_bass_text, sea_bass_image)
            binding.seaBassText.text = getString(R.string.sea_bass_text) + " " + fruitShopViewModel.sea_bass.value.toString()
        }else{
            desactiveViews(sea_bass_text, sea_bass_image)
        }
        if((fruitShopViewModel.red_mullet.value ?: 0) > 0){
            activeViews(red_mullet_text, red_mullet_image)
            binding.redMulletText.text = getString(R.string.red_mullet_text) + " " + fruitShopViewModel.red_mullet.value.toString()
        }else{
            desactiveViews(red_mullet_text, red_mullet_image)
        }
    }

    fun meatViews(cow_text: TextView, chicken_text: TextView, pig_text: TextView,
              mince_text: TextView, cow_image: ImageView, chicken_image: ImageView,
              pig_image: ImageView, mince_image: ImageView){
        if((fruitShopViewModel.cow.value ?: 0) > 0){
            activeViews(cow_text, cow_image)
            binding.cowText.text = getString(R.string.cow_text) + " " + fruitShopViewModel.cow.value.toString()
        }else{
            desactiveViews(cow_text, cow_image)
        }
        if((fruitShopViewModel.chicken.value ?: 0) > 0){
            activeViews(chicken_text, chicken_image)
            binding.chickenText.text = getString(R.string.chicken_text) + " " + fruitShopViewModel.chicken.value.toString()
        }else{
            desactiveViews(chicken_text, chicken_image)
        }
        if((fruitShopViewModel.pig.value ?: 0) > 0){
            activeViews(pig_text, pig_image)
            binding.pigText.text = getString(R.string.pig_text) + " " + fruitShopViewModel.pig.value.toString()
        }else{
            desactiveViews(pig_text, pig_image)
        }
        if((fruitShopViewModel.mince.value ?: 0) > 0){
            activeViews(mince_text, mince_image)
            binding.minceText.text = getString(R.string.mince_text) + " " + fruitShopViewModel.mince.value.toString()
        }else{
            desactiveViews(mince_text, mince_image)
        }
    }

    fun totalView(){
        if((fruitShopViewModel.totalFinal.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
            fruitShopViewModel.calculatePriceFinal()
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",fruitShopViewModel.totalFinal.value) +"€"
        }else{
            binding.deleteBasket.visibility = View.GONE
            fruitShopViewModel.calculatePriceFinal()
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",fruitShopViewModel.totalFinal.value) +"€"
        }
    }

}

