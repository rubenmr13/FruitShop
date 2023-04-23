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
import androidx.lifecycle.Observer
import com.example.fruitshop.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding

    private val shopViewModel: ShopViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)


        binding.viewModel = shopViewModel

        shopViewModel.totalFruit.observe(viewLifecycleOwner, Observer {
            fruitViews(binding.appleText, binding.pearText, binding.orangeText , binding.plumText,binding.appleImage, binding.pearImage,
                binding.orangeImage, binding.plumImage)
        })

        shopViewModel.totalFish.observe(viewLifecycleOwner, Observer {
            fishViews(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage)
        })

        shopViewModel.totalMeat.observe(viewLifecycleOwner, Observer {
            meatViews(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                binding.chickenImage, binding.pigImage, binding.minceImage)
        })

        shopViewModel.totalSport.observe(viewLifecycleOwner, Observer {
            viewsSport(binding.ballSoccerText, binding.ballBasketText, binding.ballTennisText, binding.ballBaseballText, binding.ballSoccerImage,
                binding.ballBasketImage, binding.ballTennisImage, binding.ballBaseballImage)
        })


        fruitViews(binding.appleText, binding.pearText, binding.orangeText, binding.plumText, binding.appleImage,
            binding.pearImage, binding.orangeImage, binding.plumImage)

        fishViews(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
            binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage)

        meatViews(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
            binding.chickenImage, binding.pigImage, binding.minceImage)

        viewsSport(binding.ballSoccerText, binding.ballBasketText, binding.ballTennisText, binding.ballBaseballText, binding.ballSoccerImage,
            binding.ballBasketImage, binding.ballTennisImage, binding.ballBaseballImage)

        totalView()
        viewDelete()
        viewBuy()



        binding.deleteBasket.setOnClickListener{
            shopViewModel.deleteItemFruit()

            shopViewModel.deleteItemFish()

            shopViewModel.deleteItemMeat()

            shopViewModel.deleteItemSport()

            totalView()
            viewDelete()
            viewBuy()
        }

        binding.buyAll.setOnClickListener {
            shopViewModel.deleteItemFruit()

            shopViewModel.deleteItemFish()

            shopViewModel.deleteItemMeat()

            shopViewModel.deleteItemSport()

            totalView()
            viewDelete()
            viewBuy()
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
        if((shopViewModel.apple.value ?: 0) > 0){
            activeViews(apple_text, apple_image)
            binding.appleText.text = getString(R.string.apple_text) + " " + shopViewModel.apple.value.toString()
        }else{
            desactiveViews(apple_text, apple_image)
        }
        if((shopViewModel.pear.value ?: 0) > 0){
            activeViews(pear_text, pear_image)
            binding.pearText.text = getString(R.string.pear_text) + " " + shopViewModel.pear.value.toString()
        }else{
            desactiveViews(pear_text, pear_image)
        }
        if((shopViewModel.orange.value ?: 0) > 0){
            activeViews(orange_text, orange_image)
            binding.orangeText.text = getString(R.string.orange_text) + " " + shopViewModel.orange.value.toString()
        }else{
            desactiveViews(orange_text, orange_image)
        }
        if((shopViewModel.plum.value ?: 0) > 0){
            activeViews(plum_text, plum_image)
            binding.plumText.text = getString(R.string.plum_text) + " " + shopViewModel.plum.value.toString()
        }else{
            desactiveViews(plum_text, plum_image)
        }
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    fun fishViews(salmon_text: TextView, gilt_head_bream_text: TextView, sea_bass_text: TextView,
                  red_mullet_text: TextView, salmon_image: ImageView, gilt_head_bream_image: ImageView,
                  sea_bass_image: ImageView, red_mullet_image: ImageView){
        if((shopViewModel.salmon.value ?: 0) > 0){
            activeViews(salmon_text, salmon_image)
            binding.salmonText.text = getString(R.string.salmon_text) + " " + shopViewModel.salmon.value.toString()
        }else{
            desactiveViews(salmon_text, salmon_image)
        }
        if((shopViewModel.gilt_head_bream.value ?: 0) > 0){
            activeViews(gilt_head_bream_text, gilt_head_bream_image)
            binding.giltHeadBreamText.text = getString(R.string.gilt_head_bream_text) + " " + shopViewModel.gilt_head_bream.value.toString()
        }else{
            desactiveViews(gilt_head_bream_text, gilt_head_bream_image)
        }
        if((shopViewModel.sea_bass.value ?: 0) > 0){
            activeViews(sea_bass_text, sea_bass_image)
            binding.seaBassText.text = getString(R.string.sea_bass_text) + " " + shopViewModel.sea_bass.value.toString()
        }else{
            desactiveViews(sea_bass_text, sea_bass_image)
        }
        if((shopViewModel.red_mullet.value ?: 0) > 0){
            activeViews(red_mullet_text, red_mullet_image)
            binding.redMulletText.text = getString(R.string.red_mullet_text) + " " + shopViewModel.red_mullet.value.toString()
        }else{
            desactiveViews(red_mullet_text, red_mullet_image)
        }
    }

    @SuppressLint("SetTextI18n")
    fun meatViews(cow_text: TextView, chicken_text: TextView, pig_text: TextView,
                  mince_text: TextView, cow_image: ImageView, chicken_image: ImageView,
                  pig_image: ImageView, mince_image: ImageView){
        if((shopViewModel.cow.value ?: 0) > 0){
            activeViews(cow_text, cow_image)
            binding.cowText.text = getString(R.string.cow_text) + " " + shopViewModel.cow.value.toString()
        }else{
            desactiveViews(cow_text, cow_image)
        }
        if((shopViewModel.chicken.value ?: 0) > 0){
            activeViews(chicken_text, chicken_image)
            binding.chickenText.text = getString(R.string.chicken_text) + " " + shopViewModel.chicken.value.toString()
        }else{
            desactiveViews(chicken_text, chicken_image)
        }
        if((shopViewModel.pig.value ?: 0) > 0){
            activeViews(pig_text, pig_image)
            binding.pigText.text = getString(R.string.pig_text) + " " + shopViewModel.pig.value.toString()
        }else{
            desactiveViews(pig_text, pig_image)
        }
        if((shopViewModel.mince.value ?: 0) > 0){
            activeViews(mince_text, mince_image)
            binding.minceText.text = getString(R.string.mince_text) + " " + shopViewModel.mince.value.toString()
        }else{
            desactiveViews(mince_text, mince_image)
        }
    }

    @SuppressLint("SetTextI18n")
    fun viewsSport(ballSoccer_text: TextView, ballBasket_text: TextView, ballTennis_text: TextView,
                   ballBaseball_text: TextView, ballSoccer_image: ImageView, ballBasket_image: ImageView,
                   ballTennis_image: ImageView, ballBaseball_image: ImageView){
        if((shopViewModel.ballSoccer.value ?: 0) > 0){
            activeViews(ballSoccer_text, ballSoccer_image)
            binding.ballSoccerText.text = getString(R.string.ball_soccer_text) + " " + shopViewModel.ballSoccer.value.toString()

        }else{
            desactiveViews(ballSoccer_text, ballSoccer_image)
        }
        if((shopViewModel.ballBasket.value ?: 0) > 0){
            activeViews(ballBasket_text, ballBasket_image)
            binding.ballBasketText.text = getString(R.string.ball_basket_text) + " " + shopViewModel.ballBasket.value.toString()
        }else{
            desactiveViews(ballBasket_text, ballBasket_image)
        }
        if((shopViewModel.ballTennis.value ?: 0) > 0){
            activeViews(ballTennis_text, ballTennis_image)
            binding.ballTennisText.text = getString(R.string.ball_tennis_text) + " " + shopViewModel.ballTennis.value.toString()
        }else{
            desactiveViews(ballTennis_text, ballTennis_image)
        }
        if((shopViewModel.ballBaseball.value ?: 0) > 0){
            activeViews(ballBaseball_text, ballBaseball_image)
            binding.ballBaseballText.text = getString(R.string.ball_baseball_text) + " " + shopViewModel.ballBaseball.value.toString()
        }else{
            desactiveViews(ballBaseball_text, ballBaseball_image)
        }
    }

    fun viewDelete(){
        if((shopViewModel.totalFinal.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    fun viewBuy(){
        if((shopViewModel.totalFinal.value ?: 0.0) > 0.0){
            binding.buyAll.visibility = View.VISIBLE
        }else{
            binding.buyAll.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun totalView(){
        if((shopViewModel.totalFinal.value ?: 0.0) > 0.0){
            shopViewModel.calculatePriceFinal()
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",shopViewModel.totalFinal.value) +"€"
        }else{
            shopViewModel.calculatePriceFinal()
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",shopViewModel.totalFinal.value) +"€"
        }
    }

}

