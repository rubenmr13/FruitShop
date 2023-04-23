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
import com.example.fruitshop.databinding.FragmentButcherShopBinding

class ButcherShopFragment : Fragment() {

    private lateinit var binding: FragmentButcherShopBinding
    private val butcherShopViewModel: ButcherShopViewModel by activityViewModels()

    var meat = mutableListOf<String>()
    lateinit var images : List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_butcher_shop, container, false)

        var quantity_number = 0

        butcherShopViewModel.totalMeat.observe(viewLifecycleOwner, Observer { newTotalMeat ->
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",newTotalMeat) +"€"
        })

        butcherShopViewModel.cow.observe(viewLifecycleOwner, Observer { newCow ->
            binding.cowText.text = getString(R.string.cow_text) + " " +newCow.toString() ////aqui hacemos lo del apple
        })

        butcherShopViewModel.chicken.observe(viewLifecycleOwner, Observer { newChicken ->
            binding.chickenText.text = getString(R.string.chicken_text) + " " + newChicken.toString()
        })

        butcherShopViewModel.pig.observe(viewLifecycleOwner, Observer { newPig ->
            binding.pigText.text = getString(R.string.pig_text) + " " + newPig.toString()
        })

        butcherShopViewModel.mince.observe(viewLifecycleOwner, Observer { newMince ->
            binding.minceText.text = getString(R.string.mince_text) + " " + newMince.toString()
        })

        butcherShopViewModel.meat.observe(viewLifecycleOwner, Observer{ newMeat ->
            if(newMeat == getString(R.string.selected_meat)){
                noSelectedMeat()
            }else{ //si se ha seleccionado una fruta mostramos esa fruta
                selectedMeat(quantity_number)
            }
        })

        butcherShopViewModel.saveMeat(getString(R.string.selected_meat))

        meat = initMeat()
        images = initImage()

        val adapter = ImageMeatAdapt()
        binding.spinnerButcherShop.adapter = adapter


        //vemos que hemos seleccionado con el spinner
        binding.spinnerButcherShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                views(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                    binding.chickenImage, binding.pigImage, binding.minceImage, binding.deleteBasket) //Muestra las views cuando cambia la orientacion

                butcherShopViewModel.saveMeat(binding.spinnerButcherShop.selectedItem.toString())
                //usamos el observer de fruit para actualizar las vistas
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                quantity_number = progress
                binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/10"
                binding.priceMeatText.text = getString(R.string.price_meat_text)+" "+ String.format("%.2f",butcherShopViewModel.calculateMeat(quantity_number, this@ButcherShopFragment)) +"€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario toca la SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario levanta el dedo de la SeekBar
            }
        })

        binding.addMeat.setOnClickListener {
            butcherShopViewModel.addMeat(quantity_number, this@ButcherShopFragment) //añadimos la fruta al bundle
            binding.seekBar.progress=0 //ponemos a 0 el seekBar
            butcherShopViewModel.calculatePriceMeat() //calculamos el precio y lo añadimos al bundle
            views(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                binding.chickenImage, binding.pigImage, binding.minceImage, binding.deleteBasket)
        }

        binding.deleteBasket.setOnClickListener{
            butcherShopViewModel.deleteItemMeat()
            binding.seekBar.progress=0
            views(binding.cowText, binding.chickenText, binding.pigText, binding.minceText, binding.cowImage,
                binding.chickenImage, binding.pigImage, binding.minceImage, binding.deleteBasket)
        }

        binding.basketImage.setOnClickListener {
            findNavController().navigate(R.id.action_butcherShopFragment_to_basketFragment)
        }

        return binding.root
    }


    inner class ImageMeatAdapt : BaseAdapter() {

        override fun getCount(): Int {
            return meat.size
        }

        override fun getItem(position: Int): Any {
            return meat[position]
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
            tv1.text = meat[position]

            return convertview
        }
    }

    fun noSelectedMeat(){
        binding.addMeat.visibility = View.GONE //no mostramos la vista
        binding.seekBar.visibility = View.GONE
        binding.textQuantitySelected.visibility = View.GONE
        binding.priceMeatText.visibility = View.GONE
        binding.deleteBasket.visibility = View.GONE

        if((butcherShopViewModel.totalMeat.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun selectedMeat(quantity_number: Int){
        binding.addMeat.visibility = View.VISIBLE
        binding.seekBar.visibility = View.VISIBLE
        binding.textQuantitySelected.visibility = View.VISIBLE
        binding.priceMeatText.visibility = View.VISIBLE
        binding.seekBar.progress = 0 //ponemos a 0 el seekBar

        if((butcherShopViewModel.totalMeat.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }

        binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/10"
        binding.priceMeatText.text = getString(R.string.price_meat_text)+" "+ String.format("%.2f",butcherShopViewModel.calculateMeat(quantity_number, this )) +"€"
    }

    fun active_views(meat_text: TextView, meat_image: ImageView){
        meat_text.visibility = View.VISIBLE //mostramos la vista
        meat_image.visibility = View.VISIBLE
    }

    fun desactive_views(meat_text: TextView, meat_image: ImageView){
        meat_text.visibility = View.GONE //mostramos la vista
        meat_image.visibility = View.GONE
    }

    //funcion para mostrar las views cuando cambia la orientacion
    fun views(cow_text: TextView, chicken_text: TextView, pig_text: TextView,
              mince_text: TextView, cow_image: ImageView, chicken_image: ImageView,
              pig_image: ImageView, mince_image: ImageView, delete_basket: Button
    ){
        if((butcherShopViewModel.cow.value ?: 0) > 0){
            active_views(cow_text, cow_image)
        }else{
            desactive_views(cow_text, cow_image)
        }
        if((butcherShopViewModel.chicken.value ?: 0) > 0){
            active_views(chicken_text, chicken_image)
        }else{
            desactive_views(chicken_text, chicken_image)
        }
        if((butcherShopViewModel.pig.value ?: 0) > 0){
            active_views(pig_text, pig_image)
        }else{
            desactive_views(pig_text, pig_image)
        }
        if((butcherShopViewModel.mince.value ?: 0) > 0){
            active_views(mince_text, mince_image)
        }else{
            desactive_views(mince_text, mince_image)
        }
        if((butcherShopViewModel.totalMeat.value ?: 0.0) > 0.0){
            delete_basket.visibility = View.VISIBLE
        }else{
            delete_basket.visibility = View.GONE
        }
    }

    // inicializa la lista del spinner //no se puede quitar
    fun initMeat(): MutableList<String>{
        val meat = mutableListOf<String>()
        meat.add(getString(R.string.selected_meat))
        meat.add(getString(R.string.cow))
        meat.add(getString(R.string.chicken))
        meat.add(getString(R.string.pig))
        meat.add(getString(R.string.mince))
        return meat
    }

    //inicializar las imagenes //no se puede quitar
    fun initImage(): List<Int> {
        return listOf(
            R.drawable.white,
            R.drawable.cow,
            R.drawable.chicken,
            R.drawable.pig,
            R.drawable.mince
        )
    }
}