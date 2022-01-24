package com.example.foud.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.example.foud.R
import com.example.foud.databinding.FragmentOverviewBinding
import com.example.foud.models.Result
import org.jsoup.Jsoup


class OverviewFragment : Fragment() {

    //------------values
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val view = binding.root

        //data bundle
        val args = arguments
        val data: Result? = args?.getParcelable("recipeBundle")

        val green = ContextCompat.getColor(requireContext(), R.color.main_green)

        //------------setting the data
        binding.titleTxt.text = data?.title
        binding.dicTxt.text = Jsoup.parse(data?.summary).text()
        binding.recipeImg.load(data?.image)

        when(data?.vegan){
            true -> {
                binding.vegan.setColorFilter(green)
                binding.veganTxt.setTextColor(green)
            }
            else -> {}
        }

        when(data?.dairyFree){
            true -> {
                binding.dairy.setColorFilter(green)
                binding.dairyTxt.setTextColor(green)
            }
            else -> {}
        }

        when(data?.veryHealthy){
            true -> {
                binding.healthy.setColorFilter(green)
                binding.healthyTxt.setTextColor(green)
            }
            else -> {}
        }

        when(data?.vegetarian){
            true -> {
                binding.vegata.setColorFilter(green)
                binding.vegataTxt.setTextColor(green)
            }
            else -> {}
        }

        when(data?.glutenFree){
            true -> {
                binding.gluten.setColorFilter(green)
                binding.glutenTxt.setTextColor(green)
            }
            else -> {}
        }

        when(data?.cheap){
            true -> {
                binding.cheap.setColorFilter(green)
                binding.cheapTxt.setTextColor(green)
            }
            else -> {}
        }


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}