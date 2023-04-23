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
import com.example.fruitshop.databinding.FragmentFruitShopBinding

class FruitShopFragment : Fragment() {



    private lateinit var binding: FragmentFruitShopBinding
    //private lateinit var fruitShopViewModel: FruitShopViewModel
    private val shopViewModel: ShopViewModel by activityViewModels()

    var fruits = mutableListOf<String>() //no quitar
    lateinit var images : List<Int> //no quitar



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //binding= DataBindingUtil.setContentView(this, R.layout.fragment_fruit_shop)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fruit_shop, container, false)

        //fruitShopViewModel = ViewModelProvider(this@MainActivity)[FruitShopViewModel::class.java]
        //fruitShopViewModel = ViewModelProvider(this).get(FruitShopViewModel::class.java)


        /////binding.viewModel = fruitShopViewModel

        var quantity_number = 0

        shopViewModel.totalFruit.observe(viewLifecycleOwner, Observer { newTotalFruit ->
            binding.totalText.text = getString(R.string.total)+" "+ String.format("%.2f",newTotalFruit) +"€"
        })

        shopViewModel.apple.observe(viewLifecycleOwner, Observer { newApple ->
            //fruitShopViewModel.saveApple(newApple)
            binding.appleText.text = getString(R.string.apple_text) + " " +newApple.toString() ////aqui hacemos lo del apple
        })

        shopViewModel.pear.observe(viewLifecycleOwner, Observer { newPear ->
            //fruitShopViewModel.savePear(newPear)
            binding.pearText.text = getString(R.string.pear_text) + " " + newPear.toString()
        })

        shopViewModel.orange.observe(viewLifecycleOwner, Observer { newOrange ->
            //fruitShopViewModel.saveOrange(newOrange)
            binding.orangeText.text = getString(R.string.orange_text) + " " + newOrange.toString()
        })

        shopViewModel.plum.observe(viewLifecycleOwner, Observer { newPlum ->
            //fruitShopViewModel.savePlum(newPlum)
            binding.plumText.text = getString(R.string.plum_text) + " " + newPlum.toString()
        })

        shopViewModel.fruit.observe(viewLifecycleOwner, Observer{ newFruit ->
            if(newFruit == getString(R.string.selected_fruit)){
                noSelectedFruit()
            }else{ //si se ha seleccionado una fruta mostramos esa fruta
                selectedFruit(quantity_number)
            }
        })

        shopViewModel.saveFruit(getString(R.string.selected_fruit))

        fruits = init_fruit()
        images = init_image()

        val adapter = ImageFruitAdapt()
        binding.spinnerFruitShop.adapter = adapter


        //vemos que hemos seleccionado con el spinner
        binding.spinnerFruitShop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                views(binding.appleText, binding.pearText, binding.orangeText, binding.plumText, binding.appleImage,
                    binding.pearImage, binding.orangeImage, binding.plumImage, binding.deleteBasket) //Muestra las views cuando cambia la orientacion
                shopViewModel.saveFruit(binding.spinnerFruitShop.selectedItem.toString())
                //usamos el observer de fruit para actualizar las vistas
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                quantity_number = progress
                binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/100"
                binding.priceFruitText.text = getString(R.string.price_fruit_text)+" "+ String.format("%.2f",shopViewModel.calculateFruit(quantity_number, this@FruitShopFragment)) +"€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario toca la SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario levanta el dedo de la SeekBar
            }
        })

        //seleccionamos el boton de añadir fruta
        binding.addFruit.setOnClickListener {
            shopViewModel.addFruit(quantity_number, this) //añadimos la fruta al bundle

            binding.seekBar.progress=0 //ponemos a 0 el seekBar
            shopViewModel.calculatePriceFruit() //calculamos el precio y lo añadimos al bundle
            views(binding.appleText, binding.pearText, binding.orangeText , binding.plumText,
                binding.appleImage, binding.pearImage, binding.orangeImage, binding.plumImage,
                binding.deleteBasket)
        }

        //si seleccionamos el boton de vaciar cesta
        binding.deleteBasket.setOnClickListener{
            shopViewModel.deleteItemFruit()
            binding.seekBar.progress=0
            views(binding.appleText, binding.pearText, binding.orangeText , binding.plumText,binding.appleImage, binding.pearImage,
                binding.orangeImage, binding.plumImage, binding.deleteBasket)
        }

        binding.basketImage.setOnClickListener {
            findNavController().navigate(R.id.action_fruitShopFragment_to_basketFragment)
        }

        return binding.root
    }

    inner class ImageFruitAdapt : BaseAdapter() {

        override fun getCount(): Int {
            return fruits.size
        }

        override fun getItem(position: Int): Any {
            return fruits[position]
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
            tv1.text = fruits[position]

            return convertview
        }
    }

    fun noSelectedFruit(){
        binding.addFruit.visibility = View.GONE //no mostramos la vista
        binding.seekBar.visibility = View.GONE
        binding.textQuantitySelected.visibility = View.GONE
        binding.priceFruitText.visibility = View.GONE
        binding.deleteBasket.visibility = View.GONE

        if((shopViewModel.totalFruit.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }
    }

    fun selectedFruit(quantity_number: Int){
        binding.addFruit.visibility = View.VISIBLE
        binding.seekBar.visibility = View.VISIBLE
        binding.textQuantitySelected.visibility = View.VISIBLE
        binding.priceFruitText.visibility = View.VISIBLE
        binding.seekBar.progress = 0 //ponemos a 0 el seekBar

        if((shopViewModel.totalFruit.value ?: 0.0) > 0.0){
            binding.deleteBasket.visibility = View.VISIBLE
        }else{
            binding.deleteBasket.visibility = View.GONE
        }

        binding.textQuantitySelected.text = getString(R.string.text_quantity_selected)+ " "+quantity_number+"/100"
        binding.priceFruitText.text = getString(R.string.price_fruit_text)+" "+ String.format("%.2f",shopViewModel.calculateFruit(quantity_number, this )) +"€"
    }

    fun active_views(fruit_text: TextView, fruit_image: ImageView){
        fruit_text.visibility = View.VISIBLE //mostramos la vista
        fruit_image.visibility = View.VISIBLE
    }

    fun desactive_views(fruit_text: TextView, fruit_image: ImageView){
        fruit_text.visibility = View.GONE //mostramos la vista
        fruit_image.visibility = View.GONE
    }

    //funcion para mostrar las views cuando cambia la orientacion
    fun views(apple_text: TextView, pear_text: TextView, orange_text: TextView,
              plum_text: TextView, apple_image: ImageView, pear_image: ImageView,
              orange_image: ImageView, plum_image: ImageView, delete_basket: Button
    ){

        if((shopViewModel.apple.value ?: 0) > 0){
            active_views(apple_text, apple_image)
        }else{
            desactive_views(apple_text, apple_image)
        }
        if((shopViewModel.pear.value ?: 0) > 0){
            active_views(pear_text, pear_image)
        }else{
            desactive_views(pear_text, pear_image)
        }
        if((shopViewModel.orange.value ?: 0) > 0){
            active_views(orange_text, orange_image)
        }else{
            desactive_views(orange_text, orange_image)
        }
        if((shopViewModel.plum.value ?: 0) > 0){
            active_views(plum_text, plum_image)
        }else{
            desactive_views(plum_text, plum_image)
        }
        if((shopViewModel.totalFruit.value ?: 0.0) > 0.0){
            delete_basket.visibility = View.VISIBLE
        }else{
            delete_basket.visibility = View.GONE
        }
    }

    // inicializa la lista del spinner //no se puede quitar
    fun init_fruit(): MutableList<String>{
        val fruits = mutableListOf<String>()
        fruits.add(getString(R.string.selected_fruit))
        fruits.add(getString(R.string.apple))
        fruits.add(getString(R.string.pear))
        fruits.add(getString(R.string.orange))
        fruits.add(getString(R.string.plum))
        return fruits
    }

    //inicializar las imagenes //no se puede quitar
    fun init_image(): List<Int> {
        return listOf(
            R.drawable.white,
            R.drawable.apple,
            R.drawable.pear,
            R.drawable.orange,
            R.drawable.plum
        )
    }
}