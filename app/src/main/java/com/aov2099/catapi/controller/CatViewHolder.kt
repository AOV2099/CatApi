package com.aov2099.catapi.controller

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aov2099.catapi.databinding.ItemCatBinding
import com.aov2099.catapi.model.CatResponse
import com.squareup.picasso.Picasso

class CatViewHolder(view: View, private val clickListener: CatsClickListener): RecyclerView.ViewHolder( view ) {

    private val binding = ItemCatBinding.bind(view)

    fun bind ( cat: CatResponse ){
        val catImgId = cat.imgId

        Picasso.get().load("https://cdn2.thecatapi.com/images/$catImgId.jpg").into(binding.ivCatImage)

        binding.ivCatImage.setOnClickListener {
            clickListener.onClick( cat )
        }
    }

}