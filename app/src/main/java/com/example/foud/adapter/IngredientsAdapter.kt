package com.example.foud.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foud.R
import com.example.foud.models.ExtendedIngredient
import com.example.foud.util.Constants.Companion.IMAGE_URL

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var dataList = emptyList<ExtendedIngredient>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val Name: TextView = view.findViewById(R.id.name)
        val Unit: TextView = view.findViewById(R.id.unit)
        val Consistency: TextView = view.findViewById(R.id.consistency)
        val Amount: TextView = view.findViewById(R.id.amount)
        val Image: ImageView = view.findViewById(R.id.ing_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredients_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Name.text = dataList[position].name
        holder.Unit.setText(dataList[position].unit)
        holder.Consistency.setText(dataList[position].consistency)
        holder.Amount.setText(dataList[position].amount.toString())
        holder.Image.load(IMAGE_URL+dataList[position].image)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(ing: List<ExtendedIngredient>){
        this.dataList = ing
        notifyDataSetChanged()
    }
}