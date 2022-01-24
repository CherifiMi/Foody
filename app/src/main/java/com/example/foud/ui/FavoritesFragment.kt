package com.example.foud.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foud.R
import com.example.foud.adapter.FavoritesAdapter
import com.example.foud.databinding.FragmentFavoritesBinding
import com.example.foud.databinding.FragmentRecipesBinding
import com.example.foud.viewmodel.MainViewModel


class FavoritesFragment : Fragment() {

    //------------binding
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    //-------------values
    private lateinit var mainViewModel : MainViewModel
    private val rcAdapter by lazy { FavoritesAdapter() }



    //-------------create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root

        getFavList()
        setRecyclerView()

        return view
    }

    //-------------my fun----------
    private fun setRecyclerView() {
        binding.myRc.adapter = rcAdapter
        binding.myRc.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getFavList() {
        mainViewModel.getFavList.observe(viewLifecycleOwner, {response->
            response?.let {
                rcAdapter.setData(it)
                Log.d("favlist", it.size.toString())
            }

        })
    }

}