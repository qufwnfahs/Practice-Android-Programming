package com.example.dentalmaterials.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dentalmaterials.Holder.PracticeViewHolder
import com.example.dentalmaterials.Holder.ViewHolder
import com.example.dentalmaterials.Model.ButtonItem
import com.example.dentalmaterials.Model.PracticeItem
import com.example.dentalmaterials.R

class PracticeRVAdapter(val items : ArrayList<PracticeItem>, val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var listener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position : Int);
    }

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.practice_item, parent, false)

        return PracticeViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PracticeViewHolder) {
            var buttonHeight = (recyclerView.height - 20 * 2 * 6) / 6
            holder.onBind(items[position], buttonHeight)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}