package com.shinco.dentalmaterials.Holder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Adapter.AutonomousRVAdapter
import com.shinco.dentalmaterials.Model.AutonomousItem
import kotlinx.android.synthetic.main.autonomus_item.view.*

class AutonomousViewHolder(itemView: View, val listener : AutonomousRVAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(item : AutonomousItem, buttonHeight : Int) {
        itemView.tv__composite_resin.text = item.text
        itemView.tv__composite_resin.height = buttonHeight

        var layoutParams = itemView.layout__composite_resin.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = item.margin
        layoutParams.bottomMargin = item.margin

        itemView.button__composite_resin.setOnClickListener(this)
    }
}