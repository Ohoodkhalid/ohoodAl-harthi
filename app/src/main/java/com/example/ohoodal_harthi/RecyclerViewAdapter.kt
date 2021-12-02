package com.example.ohoodal_harthi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter (var result:List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val items = result[position]


        holder.itemView.apply {
            tvText.text = items

        }
    }

    override fun getItemCount() = result.size
}

