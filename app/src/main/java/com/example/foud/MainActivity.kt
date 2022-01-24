package com.example.foud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.foud.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController= findNavController(R.id.fragmentContainerView)

        var home: ImageView = findViewById(R.id.home_img)
        var fav: ImageView = findViewById(R.id.fav_img)
        var joke: ImageView = findViewById(R.id.joke_img)

        animate(home,true)


        home.setOnClickListener{navToHome(home,fav,joke)}
        fav.setOnClickListener{navToFav(home,fav,joke)}
        joke.setOnClickListener{navToJoke(home,fav,joke)}


    }

    private fun navToFav(home:ImageView,fav:ImageView,joke:ImageView) {
        animate(home,false)
        animate(fav, true)
        animate(joke, false)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_recipes"->navController.navigate(R.id.action_recipesFragment_to_favoritesFragment)
            "fragment_jokes"->navController.navigate(R.id.action_jokesFragment_to_favoritesFragment)
            else -> Log.d("hillo", "na_fav")
        }
    }

    private fun navToJoke(home:ImageView,fav:ImageView,joke:ImageView) {
        animate(home,false)
        animate(fav, false)
        animate(joke, true)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_recipes"->navController.navigate(R.id.action_recipesFragment_to_jokesFragment)
            "fragment_favorites"->navController.navigate(R.id.action_favoritesFragment_to_jokesFragment)
            else -> Log.d("hillo", "na_joke")
        }
    }

    private fun navToHome(home:ImageView,fav:ImageView,joke:ImageView) {
        animate(home,true)
        animate(fav, false)
        animate(joke, false)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_favorites"->navController.navigate(R.id.action_favoritesFragment_to_recipesFragment)
            "fragment_jokes"->navController.navigate(R.id.action_jokesFragment_to_recipesFragment)
            else -> Log.d("hillo", "na_home")
        }
    }


    private fun animate(img: ImageView, b: Boolean){
        if(b){
            val stateSet = intArrayOf(android.R.attr.state_checked)
            img.setImageState(stateSet, true)
        }else{
            val stateSet = intArrayOf(-android.R.attr.state_checked)
            img.setImageState(stateSet, true)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
