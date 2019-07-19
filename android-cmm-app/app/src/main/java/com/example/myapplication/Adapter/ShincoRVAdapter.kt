package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Customer
import com.example.myapplication.R

class ShincoRVAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dataList = arrayListOf<Customer>()
    val dataList_clone = arrayListOf<Customer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false)

        return ShincoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ShincoViewHolder)
        {
            holder.textView_item_no.text = dataList.get(position).no
            holder.textView_item_name.text = dataList.get(position).name
            holder.textView_item_birth.text = dataList.get(position).birth
            holder.textView_item_sex.text = dataList.get(position).sex
            holder.textView_item_job.text = dataList.get(position).job
            holder.button_item_remove.setOnClickListener(View.OnClickListener {view ->  
                removeItem(position)
            })
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    // 미완성
    fun removeItem(position: Int) {
        //dataList.removeAt(position)
        //dataList_clone.removeAt(position)

        //notifyItemChanged(position)
    }

    fun updateItem(dataList: ArrayList<Customer>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)

        notifyDataSetChanged()
    }

    // 미완성
    fun search(str : String) {
        //dataList.clear()
        //dataList.addAll(dataList_clone.filter { it.name.contains(str) } as ArrayList<Customer>)

        //notifyDataSetChanged()
    }

    inner class ShincoViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val textView_item_no = v?.findViewById<TextView>(R.id.textView_item_no)
        val textView_item_name = v?.findViewById<TextView>(R.id.textView_item_name)
        val textView_item_birth = v?.findViewById<TextView>(R.id.textView_item_birth)
        val textView_item_sex = v?.findViewById<TextView>(R.id.textView_item_sex)
        val textView_item_job = v?.findViewById<TextView>(R.id.textView_item_job)
        val button_item_remove = v?.findViewById<Button>(R.id.button_item_remove)
    }
}