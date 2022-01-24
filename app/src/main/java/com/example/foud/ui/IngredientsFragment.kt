package com.example.foud.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foud.R
import com.example.foud.adapter.IngredientsAdapter
import com.example.foud.adapter.RecipesAdapter
import com.example.foud.databinding.FragmentIngredientsBinding
import com.example.foud.databinding.FragmentOverviewBinding
import com.example.foud.models.ExtendedIngredient
import com.example.foud.models.Result


class IngredientsFragment : Fragment() {

    //------------values
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private val rcAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val view = binding.root

        //data bundle
        val args = arguments
        val data: Result? = args?.getParcelable("recipeBundle")

        //------------setting the data
        val ing: List<ExtendedIngredient>? = data?.extendedIngredients
        ing?.let { rcAdapter.setData(it) }

        setRecyclerView()

        return view
    }
    private fun setRecyclerView() {
        binding.ingRc.adapter = rcAdapter
        binding.ingRc.layoutManager = LinearLayoutManager(requireContext())
    }
}