package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Adapter.RecyclerViewAdapter
import com.example.myapplication.Fragment.EventDialogFragment
import com.example.myapplication.Listener.ShincoValueEventListener
import com.example.myapplication.Model.VideoObject
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("videos")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.itemId) {
            R.id.action_add -> {
                makeDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();
    }

    fun init() {
        var recyclerView = recyclerView
        var layoutManager = LinearLayoutManager(this)
        // 각 item 들이 RecyclerView의 전체 크기를 변경하지 않는다면
        // setHasFixedSize() 함수로 성능을 개선할 수 있다.
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager

        var recyclerAdapter = RecyclerViewAdapter(lifecycle, initGlide());
        recyclerView.setAdapter(recyclerAdapter)

        databaseReference.orderByChild("date").addValueEventListener(ShincoValueEventListener(recyclerAdapter))
    }

    fun initGlide() : RequestManager {
        var options : RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background);

        return Glide.with(this)
            .setDefaultRequestOptions(options);
    }

    fun makeDialog() {
        var e = EventDialogFragment.getInstance()
        e.show(supportFragmentManager, EventDialogFragment.TAG_EVENT_DIALOG)
    }

}
