package com.aov2099.catapi.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aov2099.catapi.R
import com.aov2099.catapi.model.CatResponse

class CatAdapter(
    private var cats: List<CatResponse>,
    private var clickLister: CatsClickListener
) : RecyclerView.Adapter<CatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        return  CatViewHolder( layoutInflater.inflate( R.layout.item_cat, parent, false ), clickLister )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = cats[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

}