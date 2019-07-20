package com.example.myapplication.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Adapter.ShincoRVAdapter
import com.example.myapplication.Model.Customer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_customer_add.*

class CustomerAddActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_add)

        button_add_customer_call.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button_add_customer_call -> addCustomer()
        }
    }

    fun addCustomer() {
        var no = (ShincoRVAdapter.dataList_clone.size+1).toString()
        var name = editText_add_name.text.toString()
        var birth = editText_add_birth.text.toString()
        var sex = editText_add_sex.text.toString()
        var job = editText_add_job.text.toString()

        var user : Customer = Customer(no, name, birth, sex, job)

        MainActivity.databaseReference.child(name).setValue(user)

        finish()
    }
}
