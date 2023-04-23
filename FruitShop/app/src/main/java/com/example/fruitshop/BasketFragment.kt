package com.example.fruitshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.fruitshop.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding

    private val fruitShopViewModel: FruitShopViewModel by activityViewModels()
    private val butcherShopViewModel: ButcherShopViewModel by activityViewModels()
    private val fishMarketViewModel: FishMarketViewModel by activityViewModels()
    private val sportShopViewModel: SportShopViewModel by activityViewModels()
    private val basketViewModel: BasketViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)


        fruitShopViewModel.totalFruit.observe(viewLifecycleOwner, Observer {
            fruitViews(binding.appleText, binding.pearText, binding.orangeText , binding.plumText,binding.appleImage, binding.pearImage,
                binding.orangeImage, binding.plumImage)
        })

        fishMarketViewModel.totalFish.observe(viewLifecycleOwner, Observer {
            fishViews(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage)
        })

        butcherShopViewModel.totalMeat.observe(viewLifecycleOwner, Observer {
            meatViews(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                binding.chickenImage, binding.pigImage, binding.minceImage)
        })

        sportShopViewModel.totalSport.observe(viewLifecycleOwner, Observer {
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
            fruitShopViewModel.deleteItemFruit()
            fishMarketViewModel.deleteItemFish()
            butcherShopViewModel.deleteItemMeat()
            sportShopViewModel.deleteItemSport()
            totalView()
            viewDelete()
            viewBuy()
        }

        binding.buyAll.setOnClickListener {
            fruitShopViewModel.deleteItemFruit()
            fishMarketViewModel.deleteItemFish()
            butcherShopViewModel.deleteItemMeat()
            sportShopViewModel.deleteItemSport()
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
        item_text.visibility = View.GONE //no mostramos la vista
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
        if((fishMarketViewModel.salmon.value ?: 0) > 0){
            activeViews(salmon_text, salmon_image)
            binding.salmonText.text = getString(R.string.salmon_text) + " " + fishMarketViewModel.salmon.value.toString()
        }else{
            desactiveViews(salmon_text, salmon_image)
        }
        if((fishMarketViewModel.gilt_head_bream.value ?: 0) > 0){
            activeViews(gilt_head_bream_text, gilt_head_bream_image)
            binding.giltHeadBreamText.text = getString(R.string.gilt_head_bream_text) + " " + fishMarketViewModel.gilt_head_bream.value.toString()
        }else{
            desactiveViews(gilt_head_bream_text, gilt_head_bream_image)
        }
        if((fishMarketViewModel.sea_bass.value ?: 0) > 0){
            activeViews(sea_bass_text, sea_bass_image)
            binding.seaBassText.text = getString(R.string.sea_bass_text) + " " + fishMarketViewModel.sea_bass.value.toString()
        }else{
            desactiveViews(sea_bass_text, sea_bass_image)
        }
        if((fishMarketViewModel.red_mullet.value ?: 0) > 0){
            activeViews(red_mullet_text, red_mullet_image)
            binding.redMulletText.text = getString(R.string.red_mullet_text) + " " + fishMarketViewModel.red_mullet.value.toString()
        }else{
            desactiveViews(red_mullet_text, red_mullet_image)
        }
    }

    @SuppressLint("SetTextI18n")
    fun meatViews(cow_text: TextView, chicken_text: TextView, pig_text: TextView,
                  mince_text: TextView, cow_image: ImageView, chicken_image: ImageView,
                  pig_image: ImageView, mince_image: ImageView){
        if((butcherShopViewModel.cow.value ?: 0) > 0){
            activeViews(cow_text, cow_image)
            binding.cowText.text = getString(R.string.cow_text) + " " + butcherShopViewModel.cow.value.toString()
        }else{
            desactiveViews(cow_text, cow_image)
        }
        if((butcherShopViewModel.chicken.value ?: 0) > 0){
            activeViews(chicken_text, chicken_image)
            binding.chickenText.text = getString(R.string.chicken_text) + " " + butcherShopViewModel.chicken.value.toString()
        }else{
            desactiveViews(chicken_text, chicken_image)
        }
        if((butcherShopViewModel.pig.value ?: 0) > 0){
            activeViews(pig_text, pig_image)
            binding.pigText.text = getString(R.string.pig_text) + " " + butcherShopViewModel.pig.value.toString()
        }else{
            desactiveViews(pig_text, pig_image)
        }
        if((butcherShopViewModel.mince.value ?: 0) > 0){
            activeViews(mince_text, mince_image)
            binding.minceText.text = getString(R.string.mince_text) + " " + butcherShopViewModel.mince.value.toString()
        }else{
            desactiveViews(mince_text, mince_image)
        }
    }

    @SuppressLint("SetTextI18n")
    fun viewsSport(ballSoccer_text: TextView, ballBasket_text: TextView, ballTennis_text: TextView,
                   ballBaseball_text: TextView, ballSoccer_image: ImageView, ballBasket_image: ImageView,
                   ballTennis_image: ImageView, ballBaseball_image: ImageView){
        if((sportShopViewModel.ballSoccer.value ?: 0) > 0){
            activeViews(ballSoccer_text, ballSoccer_image)
            binding.ballSoccerText.text = getString(R.string.ball_soccer_text) + " " + sportShopViewModel.ballSoccer.value.toString()

        }else{
            desactiveViews(ballSoccer_text, ballSoccer_image)
        }
        if((sportShopViewModel.ballBasket.value ?: 0) > 0){
            activeViews(ballBasket_text, ballBasket_image)
            binding.ballBasketText.text = getString(R.string.ball_basket_text) + " " + sportShopViewModel.ballBasket.value.toString()
        }else{
            desactiveViews(ballBasket_text, ballBasket_image)
        }
        if((sportShopViewModel.ballTennis.value ?: 0) > 0){
            activeViews(ballTennis_text, ballTennis_image)
            binding.ballTennisText.text = getString(R.string.ball_tennis_text) + " " + sportShopViewModel.ballTennis.value.toString()
        }else{
            desactiveViews(ballTennis_text, ballTennis_image)
        }
        if((sportShopViewModel.ballBaseball.value ?: 0) > 0){
            activeViews(ballBaseball_text, ballBaseball_image)
            binding.ballBaseballText.text = getString(R.string.ball_baseball_text) + " " + sportShopViewModel.ballBaseball.value.toString()
        }else{
            desactiveViews(ballBaseball_text, ballBaseball_image)
        }
    }

    fun viewDelete(){
        if((basketViewModel.totalFinal.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    fun viewBuy(){
        if((basketViewModel.totalFinal.value ?: 0.0) > 0.0){
            binding.buyAll.visibility = View.VISIBLE
        }else{
            binding.buyAll.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun totalView(){
        if((basketViewModel.totalFinal.value ?: 0.0) > 0.0){
            basketViewModel.calculatePriceFinal(fruitShopViewModel.getTotalFruit(), fishMarketViewModel.getTotalFish(), butcherShopViewModel.getTotalMeat(), sportShopViewModel.getTotalSport())
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",basketViewModel.totalFinal.value) +"€"
        }else{
            basketViewModel.calculatePriceFinal(fruitShopViewModel.getTotalFruit(), fishMarketViewModel.getTotalFish(), butcherShopViewModel.getTotalMeat(), sportShopViewModel.getTotalSport())
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",basketViewModel.totalFinal.value) +"€"
        }
    }


}

