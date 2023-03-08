package com.example.fruitshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //val fruits = listOf(getString(R.string.apple), getString(R.string.pear), getString(R.string.orange), getString(R.string.plum))
    //val fruits = listOf(R.string.apple.toString(), R.string.pear.toString(), R.string.orange.toString(), R.string.plum.toString())
    //val fruits = listOf("Apple", "Pear", "Orange", "Plum")

    /*val fruits = mutableListOf<String>()
    fruits.add(getString(R.string.apple))
    fruits.add(getString(R.string.pear))
    fruits.add(getString(R.string.orange))
    fruits.add(getString(R.string.plum))*/

    var fruits = mutableListOf<String>()
   // var fruits = init_fruit()

    //val images = listOf(R.drawable.white,R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.plum)
    lateinit var images : List<Int>

    val apple_price = 0.5
    val pear_price = 0.4
    val orange_price = 0.25
    val plum_price = 0.09

    var bundle = Bundle()

    @SuppressLint("WrongViewCast", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add_fruit = findViewById<Button>(R.id.add) //boton para añadir la fruta
        val seekBar = findViewById<SeekBar>(R.id.seekBar) //number text para introducir el numero de frutas
        val text_quantity_selected = findViewById<TextView>(R.id.text_quantity_selected) //para mostrar el numero seleccionado
        val price_fruit_text = findViewById<TextView>(R.id.price_fruit_text) //precio de la fruta
        val apple_text = findViewById<TextView>(R.id.apple_text)// vamos a usar para mostrar el texto de la manzana
        val pear_text = findViewById<TextView>(R.id.pear_text)// vamos a usar para mostrar el texto de la pera
        val orange_text = findViewById<TextView>(R.id.orange_text)// vamos a usar para mostrar el texto de la pera
        val plum_text = findViewById<TextView>(R.id.plum_text)// vamos a usar para mostrar el texto de la pera
        val apple_image = findViewById<ImageView>(R.id.apple_image)
        val pear_image = findViewById<ImageView>(R.id.pear_image)
        val orange_image = findViewById<ImageView>(R.id.orange_image)
        val plum_image = findViewById<ImageView>(R.id.plum_image)
        val total_text= findViewById<TextView>(R.id.total)
        val delete_basket = findViewById<Button>(R.id.delete_basket) //boton de borrar la lista

        var quantity_number = 0

        var fruit: String = getString(R.string.selected_fruit)//variable que va a contener la fruta seleccionada en el spinner

        //val bundle = Bundle()

        fruits = init_fruit()
        images = init_image()
        //delete_bundle(bundle)
        //print_delete(bundle, total_text)

        ///view_empty_basket(bundle, apple_text, pear_text, orange_text , plum_text,apple_image, pear_image, orange_image, plum_image)
        ///views_orientataion(bundle, apple_text, pear_text, orange_text , plum_text,apple_image, pear_image,
        ///    orange_image, plum_image, total_text)
        ///print_delete(bundle, total_text)



       // val fruits = listOf(R.string.apple.toString(), R.string.pear.toString(), R.string.orange.toString(), R.string.plum.toString())
       // val images = listOf(R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.plum)
        val selected_item = findViewById<Spinner>(R.id.spinner_fruit_shop)
        val adapter = ImageFruitAdapt()
        selected_item.adapter = adapter


        //Log.d(getString(R.string.apple), "VALOR")
        // val adapter = CustomArrayAdapter(this, fruits, images)
        //selected_item.adapter = adapter


        ///vamos que hemos seleccionado con el spinner
        selected_item.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                views(bundle, apple_text, pear_text, orange_text , plum_text, apple_image, pear_image, orange_image, plum_image, total_text, delete_basket) //Muestra las views cuando cambia la orientacion

                //val fruit = selected_item.selectedItem.toString()
                 fruit = selected_item.selectedItem.toString()


                //mostra o no mostra la vista para elegir la cantidad
                if(fruit == getString(R.string.selected_fruit)){
                    add_fruit.visibility = View.GONE //no mostramos la vista
                    seekBar.visibility = View.GONE
                    text_quantity_selected.visibility = View.GONE
                    price_fruit_text.visibility = View.GONE
                    delete_basket.visibility = View.GONE

                }else{ //si se ha seleccionado una fruta mostramos esa fruta
                    add_fruit.visibility = View.VISIBLE
                    seekBar.visibility = View.VISIBLE
                    text_quantity_selected.visibility = View.VISIBLE
                    price_fruit_text.visibility = View.VISIBLE
                    seekBar.progress = 0 //ponemos a 0 el seekBar

                    text_quantity_selected.setText(getString(R.string.text_quantity_selected)+ " "+quantity_number+"/100")
                    price_fruit_text.setText(getString(R.string.price_fruit_text)+" "+ String.format("%.2f",calculate_fruit(fruit, quantity_number)) +"€")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Este método se llama cada vez que el valor de la SeekBar cambia
                // progress contiene el valor actual de la SeekBar
                quantity_number = progress
                text_quantity_selected.setText(getString(R.string.text_quantity_selected)+ " "+quantity_number+"/100")
                price_fruit_text.setText(getString(R.string.price_fruit_text)+" "+ String.format("%.2f",calculate_fruit(fruit, quantity_number)) +"€")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario toca la SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Este método se llama cuando el usuario levanta el dedo de la SeekBar
            }
        })

        add_fruit.setOnClickListener {

            add_fruit(fruit, quantity_number, bundle) //añadimos la fruta al bundle
            seekBar.progress=0 //ponemos a 0 el seekBar
            calculate_price(bundle) //calculamos el precio y lo añadimos al bundle

            views(bundle, apple_text, pear_text, orange_text , plum_text,apple_image, pear_image, orange_image, plum_image, total_text, delete_basket)

            /*if (fruit == getString(R.string.apple)) {
                view_add_basket(apple_text, apple_image)
                apple_text.setText(getString(R.string.apple_text) + " " + bundle.getInt(fruit).toString())

            }else if(fruit == getString(R.string.pear)){
                view_add_basket(pear_text, pear_image)
                pear_text.setText(getString(R.string.pear_text) + " " + bundle.getInt(fruit).toString())
            }else if(fruit == getString(R.string.orange)){
                view_add_basket(orange_text, orange_image)
                orange_text.setText(getString(R.string.orange_text) + " " + bundle.getInt(fruit).toString())
            }else{
                view_add_basket(plum_text, plum_image)
                plum_text.setText(getString(R.string.plum_text) + " " + bundle.getInt(fruit).toString())
            }
            total_text.setText(getString(R.string.total) + ": " + String.format("%.2f",bundle.getDouble(getString(R.string.total))) +"€")*/
        }
    //si seleccionamos el boton de vaciar cesta
        delete_basket.setOnClickListener{
            delete_bundle(bundle, seekBar)
            //print_delete(bundle, total_text)
            /*view_empty_basket(bundle, apple_text, pear_text, orange_text , plum_text,apple_image, pear_image,
                orange_image, plum_image)*/
            views(bundle, apple_text, pear_text, orange_text , plum_text,apple_image, pear_image,
                orange_image, plum_image, total_text, delete_basket)
        }
    }

    fun calculate_fruit(fruit: String, quantity_number: Int): Double{
        var total: Double
        if(fruit == getString(R.string.apple)){
            total = quantity_number * apple_price
        }else if(fruit == getString(R.string.pear)){
            total = quantity_number * pear_price
        }else if(fruit == getString(R.string.orange)){
            total = quantity_number * orange_price
        }else{
            total = quantity_number * plum_price
        }
        return total
    }

    //funcion para saber si esta la cesta vacia
    fun empty_basket(bundle: Bundle): Int {
        if((bundle.getInt(getString(R.string.apple)) == 0) and (bundle.getInt(getString(R.string.pear)) == 0) and
            (bundle.getInt(getString(R.string.orange)) == 0) and (bundle.getInt(getString(R.string.plum)) == 0) and
            (bundle.getInt(getString(R.string.total)) == 0)){
            return 1
        }else{
            return 0
        }
    }


    fun view_empty_basket(bundle:Bundle, apple_text:TextView, pear_text:TextView, orange_text:TextView , plum_text:TextView,
                          apple_image:ImageView, pear_image:ImageView, orange_image:ImageView, plum_image:ImageView){
        if(empty_basket(bundle) == 1){
            apple_text.visibility = View.GONE //no mostramos la vista
            pear_text.visibility = View.GONE
            orange_text.visibility = View.GONE
            plum_text.visibility = View.GONE
            apple_image.visibility = View.GONE
            pear_image.visibility = View.GONE
            orange_image.visibility = View.GONE
            plum_image.visibility = View.GONE
        }
    }

    fun active_views(fruit_text:TextView, fruit_image:ImageView){
        fruit_text.visibility = View.VISIBLE //mostramos la vista
        fruit_image.visibility = View.VISIBLE
    }

    fun desactive_views(fruit_text:TextView, fruit_image:ImageView){
        fruit_text.visibility = View.GONE //mostramos la vista
        fruit_image.visibility = View.GONE
    }


    //funcion para mostrar las views cuando cambia la orientacion
    @SuppressLint("SetTextI18n")
    fun views(bundle: Bundle, apple_text:TextView, pear_text:TextView, orange_text:TextView,
               plum_text:TextView, apple_image:ImageView, pear_image:ImageView,
               orange_image:ImageView, plum_image:ImageView, total_text: TextView,
              delete_basket: Button){

        if(bundle.getInt(getString(R.string.apple)) > 0){
            active_views(apple_text, apple_image)
            /*apple_text.visibility = View.VISIBLE
            apple_image.visibility = View.VISIBLE*/
            apple_text.setText(getString(R.string.apple_text) + " " + bundle.getInt(getString(R.string.apple)).toString())
        }else{
            desactive_views(apple_text, apple_image)
            /*apple_text.visibility = View.GONE
            apple_image.visibility = View.GONE*/
        }
        if(bundle.getInt(getString(R.string.pear)) != 0){
            active_views(pear_text, pear_image)
            /*pear_image.visibility = View.VISIBLE
            pear_text.visibility = View.VISIBLE*/
            pear_text.setText(getString(R.string.pear_text) + " " + bundle.getInt(getString(R.string.pear)).toString())
        }else{
            desactive_views(pear_text, pear_image)
            /*pear_image.visibility = View.GONE
            pear_text.visibility = View.GONE*/
        }
        if(bundle.getInt(getString(R.string.orange)) != 0){
            active_views(orange_text, orange_image)
            /*orange_text.visibility = View.VISIBLE
            orange_image.visibility = View.VISIBLE*/
            orange_text.setText(getString(R.string.orange_text) + " " + bundle.getInt(getString(R.string.orange)).toString())
        }else{
            desactive_views(orange_text, orange_image)
            /*orange_text.visibility = View.GONE
            orange_image.visibility = View.GONE*/
        }
        if(bundle.getInt(getString(R.string.plum)) != 0){
            active_views(plum_text, plum_image)
            /*plum_text.visibility = View.VISIBLE
            plum_image.visibility = View.VISIBLE*/
            plum_text.setText(getString(R.string.plum_text) + " " + bundle.getInt(getString(R.string.plum)).toString())
        }else{
            desactive_views(plum_text, plum_image)
            /*plum_text.visibility = View.GONE
            plum_image.visibility = View.GONE*/
        }
        if(bundle.getDouble(getString(R.string.total)).toInt() > 0){
            delete_basket.visibility = View.VISIBLE
        }else{
            delete_basket.visibility = View.GONE
        }

        //if(bundle.getDouble(getString(R.string.total)) != 0.0){
        total_text.setText(getString(R.string.total) + ": " + String.format("%.2f",bundle.getDouble(getString(R.string.total))) +"€")
        //}
    }

    fun view_add_basket(fruit_text:TextView, fruit_image:ImageView){
        fruit_text.visibility = View.VISIBLE
        fruit_image.visibility = View.VISIBLE
    }

    fun init_fruit(): MutableList<String>{
        val fruits = mutableListOf<String>()
        fruits.add(getString(R.string.selected_fruit))
        fruits.add(getString(R.string.apple))
        fruits.add(getString(R.string.pear))
        fruits.add(getString(R.string.orange))
        fruits.add(getString(R.string.plum))
        return fruits
    }

    //inicializar las imagenes
    fun init_image(): List<Int> {
        return listOf(
            R.drawable.white,
            R.drawable.apple,
            R.drawable.pear,
            R.drawable.orange,
            R.drawable.plum
        )
    }
    /*
    fun init_image(): List<Int>{
        val image = listOf(R.drawable.white,R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.plum)
        return image
    }*/

    //funcion para inicializar el bundle
    fun delete_bundle(bundle: Bundle, seekBar: SeekBar){
        bundle.putInt(getString(R.string.apple), 0)
        bundle.putInt(getString(R.string.pear), 0)
        bundle.putInt(getString(R.string.orange), 0)
        bundle.putInt(getString(R.string.plum), 0)
        bundle.putDouble(getString(R.string.total), 0.0)
        seekBar.progress=0 //ponemos a 0 el seekBar
    }


    fun add_fruit(fruit: String, quantity_number: Int, bundle: Bundle){
         var quanty = bundle.getInt(fruit) + quantity_number
         bundle.putInt(fruit, quanty)
     }

    fun calculate_price(bundle: Bundle){
        var total = ((bundle.getInt(getString(R.string.apple)) * apple_price)+(bundle.getInt(getString(R.string.pear)) * pear_price)+
                (bundle.getInt(getString(R.string.orange)) * orange_price)+(bundle.getInt(getString(R.string.plum)) * plum_price))
        bundle.putDouble(getString(R.string.total), total)
    }

    /*
     fun print_delete(bundle:Bundle, apple_text:TextView, pear_text:TextView, orange_text:TextView, plum_text:TextView, total_text:TextView){
        apple_text.setText(getString(R.string.apple_text) + " " + bundle.getInt(getString(R.string.apple)).toString())
        pear_text.setText(getString(R.string.pear_text) + " " + bundle.getInt(getString(R.string.pear)).toString())
        orange_text.setText(getString(R.string.orange_text) + " " + bundle.getInt(getString(R.string.orange)).toString())
        plum_text.setText(getString(R.string.plum_text) + " " + bundle.getInt(getString(R.string.plum)).toString())
        total_text.setText(getString(R.string.total) + ": " + bundle.getDouble(getString(R.string.total)).toString() +"€")
    }
     */
    @SuppressLint("SetTextI18n")
    fun print_delete(bundle:Bundle,total_text:TextView){
        total_text.setText(getString(R.string.total) + ": " + bundle.getDouble(getString(R.string.total)).toString() +"€")
    }



    override fun onSaveInstanceState(outState: Bundle){
        outState.putBundle("bundle", bundle)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bundle = savedInstanceState.getBundle("bundle")!!
    }



    /*
    Si la cesta esta vacia, miro el bundle y no hay ningun elemento muestro solo el mensaje de cesta vacia
    si no esta vacia muestro la manzana foto y tambien un boton diciendo si quiero quitar producto


    si la cesta no esta vacia que aparezca un boton de vaciar cesta
     */

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
        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = LayoutInflater.from(this@MainActivity)
            var convertview = convertView
            convertview = inflater.inflate(R.layout.itemspinner, parent, false)

            val imageView = convertview.findViewById<ImageView>(R.id.imageView)
            val tv1 = convertview.findViewById<TextView>(R.id.fruit_type)

            imageView.setImageResource(images[position])
            tv1.text = fruits[position]

            return convertview
        }
    }
}









