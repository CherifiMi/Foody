package com.example.foud.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foud.adapter.RecipesAdapter
import com.example.foud.databinding.FragmentRecipesBinding
import com.example.foud.util.Constants.Companion.API_KEY
import com.example.foud.util.Constants.Companion.QUERY_ADD_INFO
import com.example.foud.util.Constants.Companion.QUERY_API_KEY
import com.example.foud.util.Constants.Companion.QUERY_DIET
import com.example.foud.util.Constants.Companion.QUERY_INGREDIENTS
import com.example.foud.util.Constants.Companion.QUERY_NUMBER
import com.example.foud.util.Constants.Companion.QUERY_TYPE
import com.example.foud.util.NetworkResult
import com.example.foud.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.res.TypedArray

import android.R
import androidx.navigation.NavController


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    //-------------values
    private lateinit var mainViewModel : MainViewModel
    private val rcAdapter by lazy { RecipesAdapter() }
    private lateinit var navController: NavController

    //------------binding
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!


    //-------------create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val view = binding.root

        getRecipesList()
        setRecyclerView()

        binding.floatingBtn.setOnClickListener{
            //navController.navigate(com.example.foud.R.id.action_recipesFragment_to_bottomFragment)
        }


        return view
    }


    //-------------my fun----------

    private fun setRecyclerView() {
        binding.myRc.adapter = rcAdapter
        binding.myRc.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getRecipesList() {

        mainViewModel.getRecipes(applyQueries())
        Log.d("testy", "done")

        mainViewModel.recipesListResponse.observe(viewLifecycleOwner, {response ->
            when(response){
                is NetworkResult.Loading->{
                    binding.simmerThing.startShimmer()
                }
                is NetworkResult.Success->{

                    binding.simmerThing.stopShimmer()
                    binding.simmerThing.visibility = View.INVISIBLE
                    response.data?.let { rcAdapter.setData(it) }
                }
                is NetworkResult.Error->{
                    binding.simmerThing.stopShimmer()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("hillo", response.data.toString())
                }
            }
        })




    }

    private fun applyQueries(): Map<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_NUMBER] = "10"
        queries[QUERY_TYPE] = "breakfast"
        queries[QUERY_DIET] = "Vegetarian"
        queries[QUERY_ADD_INFO] = "true"
        queries[QUERY_INGREDIENTS] = "true"

        return queries
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}