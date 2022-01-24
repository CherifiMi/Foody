package com.example.foud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.datastore.preferences.protobuf.Empty
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foud.adapter.Pageradapter
import com.example.foud.database.FavEntity
import com.example.foud.databinding.ActivityDetailsBinding
import com.example.foud.models.Result
import com.example.foud.ui.IngredientsFragment
import com.example.foud.ui.OverviewFragment
import com.example.foud.ui.WebFrahment
import com.example.foud.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    //----------------values
    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    private var checked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //.....
        sendDataToTabview()

        val gold: ImageView = findViewById(R.id.gold)

        checkIfSaved(gold)

        gold.setOnClickListener{
            checked = !checked

            when(checked){
                true->{
                    animate(gold, true)
                    mainViewModel.insertFav(FavEntity(args.currentfood))
                    Log.d("database", "saved")
                }
                false->{
                    animate(gold, false)
                    mainViewModel.deleteOneFav(FavEntity(args.currentfood))
                    Log.d("database", "deleted")
                }
            }
        }

        binding.goBack.setOnClickListener{finish()}

    }


    private fun checkIfSaved(gold: ImageView) {
        mainViewModel.getFavList.observe(this, {favs->
            for (fav in favs){
                when(fav.result.id){
                    args.currentfood.id-> animate(gold, true)
                    else->animate(gold, false)
                }
            }
        })
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

    private fun sendDataToTabview() {
        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(WebFrahment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.currentfood)

        val adapter = Pageradapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}