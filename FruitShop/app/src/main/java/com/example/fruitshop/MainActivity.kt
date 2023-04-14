package com.example.fruitshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fruitshop.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

//import com.example.fruitshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    /*private lateinit var fruitShopViewModel: FruitShopViewModel

    var fruits = mutableListOf<String>() //no quitar
    lateinit var images : List<Int> //no quitar*/

    /*@SuppressLint("WrongViewCast", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<MaterialToolbar>(R.id.drawerLayout) //inflamos el action bar
        setSupportActionBar(toolBar) //con esto nos muestra el action var y lo

        //saco el nav controller ya que me da error al recargar la aplicacion
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.contenedor) //lo hacemos de esta manera y np de la de abajo ya que si lop hacemos de la otra sale error
                as NavHostFragment
        val navController = navHostFragment.navController

        // hago la fleca de la aplicacion
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        toolBar.setupWithNavController(navController, appBarConfiguration)


        //para
        val bottomNavView  = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottomNavView.setupWithNavController(navController)
/*
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)*/
    }

   /* override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //con esto meto en mi menu dentro del toolbar, cuando pintes el menu lo inflas
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //cuando pulses un boton voy a hacer algo
        val navController = findNavController(R.id.contenedor)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item) //on bien me retorna el elemento que esta siendp cntrolado por el navController o si no hay un elemento controlado que me devuelva uno
    }


}*/

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean { //con esto meto en mi menu dentro del toolbar, cuando pintes el menu lo inflas
        menuInflater.inflate(R.menu.basket_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }*/
}









