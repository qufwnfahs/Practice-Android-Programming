package com.example.dentalmaterials.Holder

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dentalmaterials.Adapter.AutonomousRVAdapter
import com.example.dentalmaterials.Adapter.PracticeRVAdapter
import com.example.dentalmaterials.Controller.Controller
import com.example.dentalmaterials.Model.AutonomousItem
import com.example.dentalmaterials.Model.PracticeItem
import kotlinx.android.synthetic.main.autonomus_item.view.*

class AutonomousViewHolder(itemView: View, val listener : AutonomousRVAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(item : AutonomousItem, buttonHeight : Int) {
        itemView.textView_item.text = item.text
        itemView.textView_item.height = buttonHeight

        var layoutParams = itemView.constraintLayout.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = item.margin
        layoutParams.bottomMargin = item.margin

        itemView.button_play.setOnClickListener(this)
    }
}