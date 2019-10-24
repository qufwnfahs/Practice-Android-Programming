package com.shinco.dentalmaterials.Holder

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Adapter.RVAdapter
import com.shinco.dentalmaterials.Model.ButtonItem
import kotlinx.android.synthetic.main.button_item.view.*

class ViewHolder(itemView: View, val listener : RVAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(button : ButtonItem, buttonHeight : Int) {
        itemView.button__composite_resin.text = button.text
        itemView.button__composite_resin.height = buttonHeight

        var layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, button.margin, 0, button.margin)

        itemView.button__composite_resin.layoutParams = layoutParams

        itemView.button__composite_resin.setOnClickListener(this)
    }
}