package com.example.dentalmaterials.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dentalmaterials.Holder.ExperimentViewHolder
import com.example.dentalmaterials.Model.NeedItem
import com.example.dentalmaterials.R

class ExperimentRVAdapter(val items : ArrayList<NeedItem>, val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_needs, parent, false)

        return ExperimentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ExperimentViewHolder) {
            var line = (items.size-1) / 3
            var width = recyclerView.width - (40 * 3)
            var height = recyclerView.height - (40 * (line+1))
            var itemHeight = height / (line+1)
            var itemWidth = width / 3

            var tg = if (itemHeight > itemWidth) itemWidth else itemHeight
            Log.i("itemwidth", tg.toString())
            holder.onBind(items[position], tg)
        }
    }

}