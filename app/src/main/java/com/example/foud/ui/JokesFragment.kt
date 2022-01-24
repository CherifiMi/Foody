package com.example.foud.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foud.R
import com.example.foud.util.Constants
import com.example.foud.viewmodel.MainViewModel

class JokesFragment : Fragment() {

    //-------------values
    private lateinit var mainViewModel : MainViewModel


    //-------------create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jokes, container, false)



        val joke: TextView = view.findViewById(R.id.joke)

        getJoke(joke)

        return view
    }

    private fun getJoke(joke: TextView) {
        mainViewModel.getJoke(applyQueries())
        mainViewModel.joke.observe(viewLifecycleOwner, Observer {
            joke.text = it.text
        })
    }
    private fun applyQueries(): Map<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[Constants.QUERY_API_KEY] = Constants.API_KEY

        return queries
    }
}