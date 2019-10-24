package com.shinco.dentalmaterials.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Holder.AutonomousViewHolder
import com.shinco.dentalmaterials.Model.AutonomousItem
import com.shinco.dentalmaterials.R

class AutonomousRVAdapter(val items : ArrayList<AutonomousItem>, val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var listener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position : Int);
    }

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.autonomus_item, parent, false)

        return AutonomousViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AutonomousViewHolder) {
            var buttonHeight = (recyclerView.height - 20 * 2 * 6) / 6
            holder.onBind(items[position], buttonHeight)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}