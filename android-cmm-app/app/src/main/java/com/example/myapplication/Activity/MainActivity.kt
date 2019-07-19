package com.example.myapplication.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.ShincoRVAdapter
import com.example.myapplication.Listener.ShincoTextWatcher
import com.example.myapplication.Listener.ShincoValueEventListener
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().reference.child("customer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recycleView adapter & layoutManager Set
        val RVadapter = ShincoRVAdapter(this)
        val RVLayoutManager = LinearLayoutManager(this)

        recyclerView_table_customer.adapter = RVadapter
        recyclerView_table_customer.layoutManager = RVLayoutManager

        // button 클릭 리스너
        button_add_customer.setOnClickListener(this);
        editText_search_customer.addTextChangedListener(ShincoTextWatcher(RVadapter))

        databaseReference.addValueEventListener(ShincoValueEventListener(RVadapter))
    }

    override fun onClick(v : View?) {
        when(v) {
            button_add_customer -> return // var intent = Intent(this, CustomerAddActivity::class.java)
                                          // startActivity(intent)
        }
    }
}
