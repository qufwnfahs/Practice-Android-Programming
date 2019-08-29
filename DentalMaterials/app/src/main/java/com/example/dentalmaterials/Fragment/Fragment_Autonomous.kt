package com.example.dentalmaterials.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dentalmaterials.Activity.AutonomousActivity
import com.example.dentalmaterials.Activity.PracticePlayActivity
import com.example.dentalmaterials.Adapter.AutonomousRVAdapter
import com.example.dentalmaterials.Model.AutonomousItem
import com.example.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_autonomous.view.*

class Fragment_Autonomous : Fragment(), AutonomousRVAdapter.OnItemClickListener {

    internal lateinit var view : View
    var autonomousItems = arrayListOf<AutonomousItem>()
    val margin = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_autonomous, container, false)

        setItems()

        return view
    }

    fun setItems() {
        var recyclerView = view.recyclerView_autonomous
        var layoutManager = LinearLayoutManager(context)

        // 각 item 들이 RecyclerView의 전체 크기를 변경하지 않는다면
        // setHasFixedSize() 함수로 성능을 개선할 수 있다.
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager

        var texts = resources.getStringArray(R.array.home_buttons)

        for (item in texts) {
            autonomousItems.add(AutonomousItem(item, margin))
        }

        var recyclerAdapter = AutonomousRVAdapter(autonomousItems, recyclerView)
        recyclerView.adapter = recyclerAdapter

        recyclerAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(position: Int) {
        var intent = Intent(activity, AutonomousActivity::class.java)
        intent.putExtra("SubTitle", autonomousItems[position].text)

        startActivity(intent)
        activity?.overridePendingTransition(0, 0)
    }
}