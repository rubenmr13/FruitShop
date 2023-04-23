package com.example.fruitshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fruitshop.databinding.FragmentSportShopBinding

class SportShopFragment : Fragment() {


    private lateinit var binding: FragmentSportShopBinding
    private val sportShopViewModel: SportShopViewModel by activityViewModels()

    var sport = mutableListOf<String>() //no quitar
    lateinit var images : List<Int> //no quitar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sport_shop, container, false)

        var quantity_number = 0

        sportShopViewModel.totalSport.observe(viewLifecycleOwner, Observer { newTotalSport ->
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",newTotalSport) +"€"
        })

        sportShopViewModel.ballSoccer.observe(viewLifecycleOwner, Observer { newBallSoccer ->
            binding.ballSoccerText.text = getString(R.string.ball_soccer_text) + " " +newBallSoccer.toString()
        })

        sportShopViewModel.ballBasket.observe(viewLifecycleOwner, Observer { newBallBasket ->
            binding.ballBasketText.text = getString(R.string.ball_basket_text) + " " + newBallBasket.toString()
        })

        sportShopViewModel.ballTennis.observe(viewLifecycleOwner, Observer { newBallTennis ->
            binding.ballTennisText.text = getString(R.string.ball_tennis_text) + " " + newBallTennis.toString()
        })

        sportShopViewModel.ballBaseball.observe(viewLifecycleOwner, Observer { newBallBaseball ->
            binding.ballBaseballText.text = getString(R.string.ball_baseball_text) + " " + newBallBaseball.toString()
        })

        sportShopViewModel.sport.observe(viewLifecycleOwner, Observer{ newSport ->
            if(newSport == getString(R.string.selected_sport)){
                noSelectedSport()
            }else{ //si se ha seleccionado una fruta mostramos esa fruta
                selectedSport(quantity_number)
            }
        })

        sportShopViewModel.saveSport(getString(R.string.selected_sport))

        sport = initSport()
        images = initImage()

        val adapter = ImageSportAdapt()
        binding.spinnerSportShop.adapter = adapter


        //vemos que hemos seleccionado con el spinner
        binding.spinnerSportShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                views(binding.ballSoccerText, binding.ballBasketText, binding.ballTennisText, binding.ballBaseballText, binding.ballSoccerImage,
                    binding.ballBasketImage, binding.ballTennisImage, binding.ballBaseballImage, binding.deleteBasket) //Muestra las views cuando cambia la orientacion

                sportShopViewModel.saveSport(binding.spinnerSportShop.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                quantity_number = progress
                binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/50"
                binding.priceSportText.text = getString(R.string.price_sport_text)+" "+ String.format("%.2f",sportShopViewModel.calculateSport(quantity_number, this@SportShopFragment)) +"€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario toca la SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario levanta el dedo de la SeekBar
            }
        })

        //seleccionamos el boton de añadir fruta
        binding.addSport.setOnClickListener {
            sportShopViewModel.addSport(quantity_number, this) //añadimos la fruta al bundle
            binding.seekBar.progress=0 //ponemos a 0 el seekBar
            sportShopViewModel.calculatePriceSport() //calculamos el precio y lo añadimos al bundle
            views(binding.ballSoccerText, binding.ballBasketText, binding.ballTennisText, binding.ballBaseballText, binding.ballSoccerImage,
                binding.ballBasketImage, binding.ballTennisImage, binding.ballBaseballImage, binding.deleteBasket)
        }

        //si seleccionamos el boton de vaciar cesta
        binding.deleteBasket.setOnClickListener{
            sportShopViewModel.deleteItemSport()
            binding.seekBar.progress=0
            views(binding.ballSoccerText, binding.ballBasketText, binding.ballTennisText, binding.ballBaseballText, binding.ballSoccerImage,
                binding.ballBasketImage, binding.ballTennisImage, binding.ballBaseballImage, binding.deleteBasket)
        }

        binding.basketImage.setOnClickListener {
            findNavController().navigate(R.id.action_sportShopFragment_to_basketFragment)
        }

        return binding.root
    }

    inner class ImageSportAdapt : BaseAdapter() {

        override fun getCount(): Int {
            return sport.size
        }

        override fun getItem(position: Int): Any {
            return sport[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
        @SuppressLint("MissingInflatedId")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val convertview = LayoutInflater.from(context).inflate(R.layout.itemspinner, parent, false)

            val imageView = convertview.findViewById<ImageView>(R.id.imageView)
            val tv1 = convertview.findViewById<TextView>(R.id.item_type)

            imageView.setImageResource(images[position])
            tv1.text = sport[position]

            return convertview
        }
    }

    fun noSelectedSport(){
        binding.addSport.visibility = View.GONE //no mostramos la vista
        binding.seekBar.visibility = View.GONE
        binding.textQuantitySelected.visibility = View.GONE
        binding.priceSportText.visibility = View.GONE
        binding.deleteBasket.visibility = View.GONE

        if((sportShopViewModel.totalSport.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    fun selectedSport(quantity_number: Int){
        binding.addSport.visibility = View.VISIBLE
        binding.seekBar.visibility = View.VISIBLE
        binding.textQuantitySelected.visibility = View.VISIBLE
        binding.priceSportText.visibility = View.VISIBLE
        binding.seekBar.progress = 0 //ponemos a 0 el seekBar

        if((sportShopViewModel.totalSport.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }

        binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/50"
        binding.priceSportText.text = getString(R.string.price_sport_text)+" "+ String.format("%.2f",sportShopViewModel.calculateSport(quantity_number, this )) +"€"
    }

    fun active_views(sport_text: TextView, sport_image: ImageView){
        sport_text.visibility = View.VISIBLE //mostramos la vista
        sport_image.visibility = View.VISIBLE
    }

    fun desactive_views(sport_text: TextView, sport_image: ImageView){
        sport_text.visibility = View.GONE //mostramos la vista
        sport_image.visibility = View.GONE
    }

    //funcion para mostrar las views cuando cambia la orientacion
    fun views(ballSoccer_text: TextView, ballBasket_text: TextView, ballTennis_text: TextView,
              ballBaseball_text: TextView, ballSoccer_image: ImageView, ballBasket_image: ImageView,
              ballTennis_image: ImageView, ballBaseball_image: ImageView, delete_basket: Button
    ){
        if((sportShopViewModel.ballSoccer.value ?: 0) > 0){
            active_views(ballSoccer_text, ballSoccer_image)
        }else{
            desactive_views(ballSoccer_text, ballSoccer_image)
        }
        if((sportShopViewModel.ballBasket.value ?: 0) > 0){
            active_views(ballBasket_text, ballBasket_image)
        }else{
            desactive_views(ballBasket_text, ballBasket_image)
        }
        if((sportShopViewModel.ballTennis.value ?: 0) > 0){
            active_views(ballTennis_text, ballTennis_image)
        }else{
            desactive_views(ballTennis_text, ballTennis_image)
        }
        if((sportShopViewModel.ballBaseball.value ?: 0) > 0){
            active_views(ballBaseball_text, ballBaseball_image)
        }else{
            desactive_views(ballBaseball_text, ballBaseball_image)
        }
        if((sportShopViewModel.totalSport.value ?: 0.0) > 0.0){
            delete_basket.visibility = View.VISIBLE
        }else{
            delete_basket.visibility = View.GONE
        }
    }

    fun initSport(): MutableList<String>{
        val sport = mutableListOf<String>()
        sport.add(getString(R.string.selected_sport))
        sport.add(getString(R.string.ball_soccer))
        sport.add(getString(R.string.ball_basket))
        sport.add(getString(R.string.ball_tennis))
        sport.add(getString(R.string.ball_baseball))
        return sport
    }

    fun initImage(): List<Int> {
        return listOf(
            R.drawable.white,
            R.drawable.soccer,
            R.drawable.ball_basket,
            R.drawable.tennis,
            R.drawable.baseball
        )
    }
}