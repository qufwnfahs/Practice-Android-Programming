package com.shinco.dentalmaterials.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shinco.dentalmaterials.Model.NeedItem
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.item_needs.view.*

class ExperimentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    fun onBind(item : NeedItem, width: Int) {
        itemView.imageView_need.setImageResource(itemView.resources.getIdentifier(item.text, "drawable",  "com.shinco.dentalmaterials"))

        var lp = itemView.imageView_need.layoutParams as FlexboxLayoutManager.LayoutParams

        if (item.text == "mix") {
            lp.flexBasisPercent = 0.77f
            lp.height = 180
        }
        else
        {
            lp.flexBasisPercent = 0.24f
        }
        //itemView.imageView_need.layoutParams = params
    }
}