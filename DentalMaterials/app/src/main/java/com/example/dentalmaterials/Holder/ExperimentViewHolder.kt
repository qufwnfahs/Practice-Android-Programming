package com.example.dentalmaterials.Holder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dentalmaterials.Model.NeedItem
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.AlignSelf
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.autonomus_item.view.*
import kotlinx.android.synthetic.main.item_needs.view.*

class ExperimentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(item : NeedItem, width: Int) {
        itemView.textView_need.text = item.text
        itemView.textView_need.width = width
        itemView.textView_need.height = width

        var lp = itemView.textView_need.layoutParams as FlexboxLayoutManager.LayoutParams
        lp.flexBasisPercent = 0.30f
    }
}