package com.example.foud.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foud.R
import com.example.foud.database.FavEntity
import com.example.foud.models.RecipesList
import com.example.foud.ui.FavoritesFragmentDirections
import com.example.foud.util.RecipesDiffUtil
import org.jsoup.Jsoup

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var recipes = emptyList<FavEntity>()

    //---------------------
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val card: ConstraintLayout = view.findViewById(R.id.recipes_card_back)

        val tital: TextView = view.findViewById(R.id.food_title)
        val dic: TextView = view.findViewById(R.id.dis_txt)
        val img: ImageView = view.findViewById(R.id.img)

        val hart: TextView = view.findViewById(R.id.hart_txt)
        val time: TextView = view.findViewById(R.id.time_txt)
        val vegan: TextView = view.findViewById(R.id.vegan_txt)

        val veganIcon: ImageView = view.findViewById(R.id.vegan_img)

        //-----------color
        var green = ContextCompat.getColor(view.context, R.color.vegan)
        var nogreen = ContextCompat.getColor(view.context, R.color.notvegan)
    }




    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tital.text = recipes[position].result.title
        holder.dic.text = Jsoup.parse(recipes[position].result.summary).text()
        holder.img.load(recipes[position].result.image)

        when(recipes[position].result.vegan){
            true -> {
                holder.veganIcon.setColorFilter(holder.green)
                holder.vegan.setTextColor(holder.green)
            }
            false->{
                holder.veganIcon.setColorFilter(holder.nogreen)
                holder.vegan.setTextColor(holder.nogreen)
            }
        }

        holder.hart.text = recipes[position].result.aggregateLikes.toString()

        holder.time.text = recipes[position].result.readyInMinutes.toString()

        holder.card.setOnClickListener{
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsActivity(recipes[position].result)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card_layout, parent, false)
        return ViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<FavEntity>){
        this.recipes = data
        notifyDataSetChanged()
    }
}