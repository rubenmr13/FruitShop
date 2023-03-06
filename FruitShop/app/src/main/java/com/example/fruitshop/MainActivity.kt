package com.example.fruitshop

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

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

    val apple_price = 0.3
    val pear_price = 0.2
    val orange_price = 0.09
    val plum_price = 0.03


    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add_fruit = findViewById<Button>(R.id.add) //boton para añadir la fruta
        val quantity_number = findViewById<EditText>(R.id.quantity_number) //number text para introducir el numero de frutas
        val apple_text = findViewById<TextView>(R.id.apple_text)// vamos a usar para mostrar el texto de la manzana
        val pear_text = findViewById<TextView>(R.id.pear_text)// vamos a usar para mostrar el texto de la pera
        val orange_text = findViewById<TextView>(R.id.orange_text)// vamos a usar para mostrar el texto de la pera
        val plum_text = findViewById<TextView>(R.id.plum_text)// vamos a usar para mostrar el texto de la pera
        val total_text= findViewById<TextView>(R.id.total)
        val delete_basket = findViewById<Button>(R.id.delete_basket) //boton de borrar la lista


        val bundle = Bundle()

        fruits = init_fruit()
        images = init_image()
        delete_bundle(bundle)
        print_delete(bundle, apple_text, pear_text, orange_text, plum_text, total_text)


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

                val fruit = selected_item.selectedItem.toString()

                //mostra o no mostra la vista para elegir la cantidad
                if(fruit == getString(R.string.selected_fruit)){
                    add_fruit.visibility = View.GONE //no mostramos la vista
                    quantity_number.visibility = View.GONE
                }else{ //si se ha seleccionado una fruta mostramos esa fruta
                    add_fruit.visibility = View.VISIBLE
                    quantity_number.visibility = View.VISIBLE
                }

                add_fruit.setOnClickListener {
                    add_fruit(fruit, quantity_number, bundle) //añadimos la fruta al bundle
                    calculate_price(bundle) //calculamos el precio y lo añadimos al bundle
                    //para el texto de la manzana
                    //apple_text.setText("")
                    if (fruit == getString(R.string.apple)) {
                        apple_text.setText(getString(R.string.apple_text) + " " + bundle.getInt(fruit).toString())
                    }else if(fruit == getString(R.string.pear)){
                        pear_text.setText(getString(R.string.pear_text) + " " + bundle.getInt(fruit).toString())
                    }else if(fruit == getString(R.string.orange)){
                        orange_text.setText(getString(R.string.orange_text) + " " + bundle.getInt(fruit).toString())
                    }else{
                        plum_text.setText(getString(R.string.plum_text) + " " + bundle.getInt(fruit).toString())
                    }
                    total_text.setText(getString(R.string.total) + ": " + bundle.getDouble(getString(R.string.total)).toString() +"€")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        })

        delete_basket.setOnClickListener{
            delete_bundle(bundle)
            print_delete(bundle, apple_text, pear_text, orange_text, plum_text, total_text)
        }
    }

    //funcion para saber si esta la cesta vacia
    fun empty_basket(bundle: Bundle): Int{
        if((bundle.getInt(getString(R.string.apple)) == 0) and (bundle.getInt(getString(R.string.pear)) == 0) and
            (bundle.getInt(getString(R.string.orange)) == 0) and (bundle.getInt(getString(R.string.plum)) == 0) and
            (bundle.getInt(getString(R.string.total)) == 0)){
            return 1
        }else{
            return 0
        }
    }

    /*fun viex_empty_basket(){

    }*/

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
    fun init_image(): List<Int>{
        val image = listOf(R.drawable.white,R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.plum)
        return image
    }

    //funcion para inicializar el bundle
    fun delete_bundle(bundle: Bundle){
        bundle.putInt(getString(R.string.apple), 0)
        bundle.putInt(getString(R.string.pear), 0)
        bundle.putInt(getString(R.string.orange), 0)
        bundle.putInt(getString(R.string.plum), 0)
        bundle.putDouble(getString(R.string.total), 0.0)
    }

    //funcion para añadir el la fruta al bundle

 /*   fun add_fruit(fruit: String, quantity_number: EditText, bundle: Bundle): String{
        var resultado : String
        var quanty = bundle.getInt(fruit) + quantity_number.text.toString().toInt()
        bundle.putInt(fruit, quanty)
        //resultado = "El resultado es " + bundle.getInt(fruit).toString()

        return resultado
    }*/
     fun add_fruit(fruit: String, quantity_number: EditText, bundle: Bundle){
         var quanty = bundle.getInt(fruit) + quantity_number.text.toString().toInt()
         bundle.putInt(fruit, quanty)
     }

    fun calculate_price(bundle: Bundle){
        var total : Double
        total = ((bundle.getInt(getString(R.string.apple)) * apple_price)+(bundle.getInt(getString(R.string.pear)) * pear_price)+
                (bundle.getInt(getString(R.string.orange)) * orange_price)+(bundle.getInt(getString(R.string.plum)) * plum_price)).toDouble()
        bundle.putDouble(getString(R.string.total), total)
    }

    @SuppressLint("SetTextI18n")
    fun print_delete(bundle:Bundle, apple_text:TextView, pear_text:TextView, orange_text:TextView, plum_text:TextView, total_text:TextView){
        apple_text.setText(getString(R.string.apple_text) + " " + bundle.getInt(getString(R.string.apple)).toString())
        pear_text.setText(getString(R.string.pear_text) + " " + bundle.getInt(getString(R.string.pear)).toString())
        orange_text.setText(getString(R.string.orange_text) + " " + bundle.getInt(getString(R.string.orange)).toString())
        plum_text.setText(getString(R.string.plum_text) + " " + bundle.getInt(getString(R.string.plum)).toString())
        total_text.setText(getString(R.string.total) + ": " + bundle.getDouble(getString(R.string.total)).toString() +"€")
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









