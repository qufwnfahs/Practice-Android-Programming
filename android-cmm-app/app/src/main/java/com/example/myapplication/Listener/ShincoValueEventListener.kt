package com.example.myapplication.Listener

import android.util.Log
import com.example.myapplication.Adapter.ShincoRVAdapter
import com.example.myapplication.Model.Customer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ShincoValueEventListener(val adapter: ShincoRVAdapter) : ValueEventListener {
    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(dataSnapShot : DataSnapshot) {
        val dataList = arrayListOf<Customer>()

        for (snapShot in dataSnapShot.children)
        {
            var customer = Customer(snapShot.child("no").value.toString(),
                snapShot.child("name").value.toString(),
                snapShot.child("birth").value.toString(),
                snapShot.child("sex").value.toString(),
                snapShot.child("job").value.toString())

            dataList.add(customer)
        }

        updateCall(dataList)
    }

    fun updateCall(dataList : ArrayList<Customer>) {
        Log.i("Update Call", "msg")
        adapter.updateItem(dataList)
    }

}