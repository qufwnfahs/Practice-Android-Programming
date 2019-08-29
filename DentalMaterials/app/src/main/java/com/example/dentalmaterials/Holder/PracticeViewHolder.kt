package com.example.dentalmaterials.Holder

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dentalmaterials.Adapter.PracticeRVAdapter
import com.example.dentalmaterials.Controller.Controller
import com.example.dentalmaterials.Model.PracticeItem
import com.example.dentalmaterials.R
import kotlinx.android.synthetic.main.practice_item.view.*

class PracticeViewHolder(itemView: View, val listener : PracticeRVAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(item : PracticeItem, buttonHeight : Int) {
        itemView.textView_item.text = item.text
        itemView.textView_item.height = buttonHeight

        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)

        itemView.button_play.text = Controller.getStringForColorString(start_to_end_point, "Play", itemView.resources.getColor(R.color.accent_word))

        var layoutParams = itemView.constraintLayout.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = item.margin
        layoutParams.bottomMargin = item.margin

        itemView.button_play.setOnClickListener(this)
    }
}