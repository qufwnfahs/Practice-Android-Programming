package com.shinco.dentalmaterials.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Holder.ViewHolder
import com.shinco.dentalmaterials.Model.ButtonItem
import com.shinco.dentalmaterials.R

class RVAdapter(val buttons : ArrayList<ButtonItem>, val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var listener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position : Int);
    }

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_item, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            var buttonHeight = (recyclerView.height - 10 * 2 * 6) / 6
            holder.onBind(buttons[position], buttonHeight)
        }
    }

    override fun getItemCount(): Int {
        return buttons.size
    }


}