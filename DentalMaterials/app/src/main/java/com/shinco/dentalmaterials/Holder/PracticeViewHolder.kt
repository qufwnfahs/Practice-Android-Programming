package com.shinco.dentalmaterials.Holder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Adapter.PracticeRVAdapter
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.Model.PracticeItem
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.practice_item.view.*

class PracticeViewHolder(itemView: View, val listener : PracticeRVAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(item : PracticeItem, buttonHeight : Int) {
        itemView.tv__composite_resin.text = item.text
        itemView.tv__composite_resin.height = buttonHeight

        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)

        itemView.button__composite_resin.text = Controller.getStringForColorString(start_to_end_point, "Play", itemView.resources.getColor(R.color.accent_word))

        var layoutParams = itemView.layout__composite_resin.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = item.margin
        layoutParams.bottomMargin = item.margin

        itemView.button__composite_resin.setOnClickListener(this)
    }
}