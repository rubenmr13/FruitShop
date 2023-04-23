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
import com.example.fruitshop.databinding.FragmentFishMarketBinding

class FishMarketFragment : Fragment() {

    private lateinit var binding: FragmentFishMarketBinding
    private val fishMarketViewModel: FishMarketViewModel by activityViewModels()

    var fish = mutableListOf<String>()
    lateinit var images : List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fish_market, container, false)


        var quantity_number = 0

        fishMarketViewModel.totalFish.observe(viewLifecycleOwner, Observer { newTotalFish ->
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",newTotalFish) +"€"
        })

        fishMarketViewModel.salmon.observe(viewLifecycleOwner, Observer { newSalmon ->
            binding.salmonText.text = getString(R.string.salmon_text) + " " +newSalmon.toString() ////aqui hacemos lo del apple
        })

        fishMarketViewModel.gilt_head_bream.observe(viewLifecycleOwner, Observer { newGilt_head_bream ->
            binding.giltHeadBreamText.text = getString(R.string.gilt_head_bream_text) + " " + newGilt_head_bream.toString()
        })

        fishMarketViewModel.sea_bass.observe(viewLifecycleOwner, Observer { newSea_bass ->
            binding.seaBassText.text = getString(R.string.sea_bass_text) + " " + newSea_bass.toString()
        })

        fishMarketViewModel.red_mullet.observe(viewLifecycleOwner, Observer { newRed_mullet ->
            binding.redMulletText.text = getString(R.string.red_mullet_text) + " " + newRed_mullet.toString()
        })

        fishMarketViewModel.fish.observe(viewLifecycleOwner, Observer{ newFish ->
            if(newFish == getString(R.string.selected_fish)){
                noSelectedFish()
            }else{ //si se ha seleccionado una fruta mostramos esa fruta
                selectedFish(quantity_number)
            }
        })

        fishMarketViewModel.saveFish(getString(R.string.selected_fish))

        fish = initFish()
        images = initImage()

        val adapter = ImageFishAdapt()
        binding.spinnerFishShop.adapter = adapter

        binding.spinnerFishShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                views(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                    binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage, binding.deleteBasket) //Muestra las views cuando cambia la orientacion
                fishMarketViewModel.saveFish(binding.spinnerFishShop.selectedItem.toString())
                //usamos el observer de fruit para actualizar las vistas
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                quantity_number = progress
                binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/10"
                binding.priceFishText.text = getString(R.string.price_fish_text)+" "+ String.format("%.2f",fishMarketViewModel.calculateFish(quantity_number, this@FishMarketFragment)) +"€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario toca la SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario levanta el dedo de la SeekBar
            }
        })

        binding.addFish.setOnClickListener {
            fishMarketViewModel.addFish(quantity_number, this) //añadimos la fruta al bundle
            binding.seekBar.progress=0 //ponemos a 0 el seekBar
            fishMarketViewModel.calculatePriceFish() //calculamos el precio y lo añadimos al bundle
            views(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage, binding.deleteBasket)
        }

        binding.deleteBasket.setOnClickListener{
            fishMarketViewModel.deleteItemFish()
            binding.seekBar.progress=0
            views(binding.salmonText, binding.giltHeadBreamText, binding.seaBassText, binding.redMulletText, binding.salmonImage,
                binding.giltHeadBreamImage, binding.seaBassImage, binding.redMulletImage, binding.deleteBasket)
        }

        binding.basketImage.setOnClickListener {
            findNavController().navigate(R.id.action_fishMarketFragment_to_basketFragment)
        }
        return binding.root
    }

    inner class ImageFishAdapt : BaseAdapter() {

        override fun getCount(): Int {
            return fish.size
        }

        override fun getItem(position: Int): Any {
            return fish[position]
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
            tv1.text = fish[position]

            return convertview
        }
    }

    fun noSelectedFish(){
        binding.addFish.visibility = View.GONE //no mostramos la vista
        binding.seekBar.visibility = View.GONE
        binding.textQuantitySelected.visibility = View.GONE
        binding.priceFishText.visibility = View.GONE
        binding.deleteBasket.visibility = View.GONE

        if((fishMarketViewModel.totalFish.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    fun selectedFish(quantity_number: Int){
        binding.addFish.visibility = View.VISIBLE
        binding.seekBar.visibility = View.VISIBLE
        binding.textQuantitySelected.visibility = View.VISIBLE
        binding.priceFishText.visibility = View.VISIBLE
        binding.seekBar.progress = 0 //ponemos a 0 el seekBar

        if((fishMarketViewModel.totalFish.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }

        binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/10"
        binding.priceFishText.text = getString(R.string.price_fish_text)+" "+ String.format("%.2f",fishMarketViewModel.calculateFish(quantity_number, this )) +"€"
    }

    fun active_views(fish_text: TextView, fish_image: ImageView){
        fish_text.visibility = View.VISIBLE //mostramos la vista
        fish_image.visibility = View.VISIBLE
    }

    fun desactive_views(fish_text: TextView, fish_image: ImageView){
        fish_text.visibility = View.GONE //mostramos la vista
        fish_image.visibility = View.GONE
    }

    //funcion para mostrar las views cuando cambia la orientacion
    fun views(salmon_text: TextView, gilt_head_bream_text: TextView, sea_bass_text: TextView,
              red_mullet_text: TextView, salmon_image: ImageView, gilt_head_bream_image: ImageView,
              sea_bass_image: ImageView, red_mullet_image: ImageView, delete_basket: Button){
        if((fishMarketViewModel.salmon.value ?: 0) > 0){
            active_views(salmon_text, salmon_image)
        }else{
            desactive_views(salmon_text, salmon_image)
        }
        if((fishMarketViewModel.gilt_head_bream.value ?: 0) > 0){
            active_views(gilt_head_bream_text, gilt_head_bream_image)
        }else{
            desactive_views(gilt_head_bream_text, gilt_head_bream_image)
        }
        if((fishMarketViewModel.sea_bass.value ?: 0) > 0){
            active_views(sea_bass_text, sea_bass_image)
        }else{
            desactive_views(sea_bass_text, sea_bass_image)
        }
        if((fishMarketViewModel.red_mullet.value ?: 0) > 0){
            active_views(red_mullet_text, red_mullet_image)
        }else{
            desactive_views(red_mullet_text, red_mullet_image)
        }
        if((fishMarketViewModel.totalFish.value ?: 0.0) > 0.0){
            delete_basket.visibility = View.VISIBLE
        }else{
            delete_basket.visibility = View.GONE
        }
    }


    fun initFish(): MutableList<String>{
        val fish = mutableListOf<String>()
        fish.add(getString(R.string.selected_fish))
        fish.add(getString(R.string.salmon))
        fish.add(getString(R.string.gilt_head_bream))
        fish.add(getString(R.string.sea_bass))
        fish.add(getString(R.string.red_mullet))
        return fish
    }

    fun initImage(): List<Int> {
        return listOf(
            R.drawable.white,
            R.drawable.salmon,
            R.drawable.dorada,
            R.drawable.lubina,
            R.drawable.salmonete
        )
    }
}