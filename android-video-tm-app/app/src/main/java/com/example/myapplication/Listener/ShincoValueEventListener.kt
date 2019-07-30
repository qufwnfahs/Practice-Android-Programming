package com.example.myapplication.Listener

import android.util.Log
import com.example.myapplication.Adapter.RecyclerViewAdapter
import com.example.myapplication.Model.VideoObject
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ShincoValueEventListener(val adapter: RecyclerViewAdapter) : ValueEventListener {
    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(dataSnapShot : DataSnapshot) {
        val dataList = arrayListOf<VideoObject>()

        for (snapShot in dataSnapShot.children)
        {
            var video = VideoObject(snapShot.child("title").value.toString(),
                snapShot.child("media_url").value.toString(),
                snapShot.child("thumbnail").value.toString(),
                snapShot.child("author").value.toString(),
                snapShot.child("date").value.toString(),
                snapShot.child("start").value.toString(),
                snapShot.child("end").value.toString()
            )

            dataList.add(0, video)
        }

        updateCall(dataList)
    }

    fun updateCall(dataList : ArrayList<VideoObject>) {
        Log.i("Update Call", "msg")
        adapter.update(dataList)
    }

}