package com.example.fruitshop

import android.annotation.SuppressLint
import android.content.Context
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




    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()

        fruits = init_fruit()
        images = init_image()
        init_bundle(bundle)


       // val fruits = listOf(R.string.apple.toString(), R.string.pear.toString(), R.string.orange.toString(), R.string.plum.toString())
       // val images = listOf(R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.plum)
        val selected_item = findViewById<Spinner>(R.id.spinner_fruit_shop)
        val adapter = ImageFruitAdapt()
        selected_item.adapter = adapter

        val add_fruit = findViewById<Button>(R.id.add) //boton para añadir la fruta
        val quantity_number = findViewById<EditText>(R.id.quantity_number) //number text para introducir el numero de frutas

        val resultado = findViewById<TextView>(R.id.textView3) //es solo de prueba



        //Log.d(getString(R.string.apple), "VALOR")
        // val adapter = CustomArrayAdapter(this, fruits, images)
        //selected_item.adapter = adapter



        ///vamos que hemos seleccionado con el spinner
        selected_item.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val fruit = selected_item.selectedItem.toString()

                //mostra o no mostra la vista para elegir la cantidad
                if(fruit == getString(R.string.selected_fuit)){
                    add_fruit.visibility = View.GONE //no mostramos la vista
                    quantity_number.visibility = View.GONE
                }else{
                    add_fruit.visibility = View.VISIBLE
                    quantity_number.visibility = View.VISIBLE
                }
                add_fruit.setOnClickListener {
                    resultado.setText(add_fruit(fruit, quantity_number, bundle))

                }



            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


    }
/*
    class CustomArrayAdapter(
        context: Context,
        private val items: List<String>,
        private val images: List<Int>
    ) : ArrayAdapter<String>(context, R.layout.itemspinner, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.itemspinner, parent, false)

            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val textView = view.findViewById<TextView>(R.id.fruit_type)

            imageView.setImageResource(images[position])
            textView.text = items[position]

            return view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.itemspinner, parent, false)

            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val textView = view.findViewById<TextView>(R.id.fruit_type)

            imageView.setImageResource(images[position])
            textView.text = items[position]

            return view
        }
    }*/
    fun init_fruit(): MutableList<String>{
        val fruits = mutableListOf<String>()
        fruits.add(getString(R.string.selected_fuit))
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
    fun init_bundle(bundle: Bundle){
        bundle.putInt(getString(R.string.apple), 0)
        bundle.putInt(getString(R.string.pear), 0)
        bundle.putInt(getString(R.string.orange), 0)
        bundle.putInt(getString(R.string.plum), 0)
    }

    //funcion para añadir el la fruta al bundle
    fun add_fruit(fruit: String, quantity_number: EditText, bundle: Bundle): String{
        var resultado : String
        var quanty = bundle.getInt(fruit) + quantity_number.text.toString().toInt()
        bundle.putInt(fruit, quanty)
        resultado = "El resultado es " + bundle.getInt(fruit).toString()

        return resultado
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









