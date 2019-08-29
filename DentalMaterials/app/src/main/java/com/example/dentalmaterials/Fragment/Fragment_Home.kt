package com.example.dentalmaterials.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dentalmaterials.Adapter.RVAdapter
import com.example.dentalmaterials.Controller.Controller
import com.example.dentalmaterials.Model.ButtonItem
import com.example.dentalmaterials.R
import kotlinx.android.synthetic.main.button_item.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class Fragment_Home : Fragment(), RVAdapter.OnItemClickListener {

    internal lateinit var view : View
    val margin : Int = 10
    var buttonItem : ArrayList<ButtonItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        init()
        return view
    }

    fun init() {
        setTitle()
        setButtons()
    }

    fun setTitle() {
        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)
        start_to_end_point.put(7, 8)
        start_to_end_point.put(17, 18)

        view.textView_fragment_home.text = Controller.getStringForColorString(start_to_end_point, resources.getString(R.string.tab_home_title), resources.getColor(R.color.accent_word))
    }

    fun setButtons() {
        var recyclerView = view.recyclerView
        var layoutManager = LinearLayoutManager(context)

        // 각 item 들이 RecyclerView의 전체 크기를 변경하지 않는다면
        // setHasFixedSize() 함수로 성능을 개선할 수 있다.
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager

        var buttons = resources.getStringArray(R.array.home_buttons)

        for (item in buttons) {
            buttonItem.add(ButtonItem(item, margin))
        }

        var recyclerAdapter = RVAdapter(buttonItem, recyclerView)
        recyclerView.adapter = recyclerAdapter

        recyclerAdapter.setOnItemClickListener(this)
    }

    fun changeFragment(text: String) {
        var transaction = childFragmentManager.beginTransaction()

        transaction.replace(R.id.layout_fragment_home, Fragment_Home_Info(text), text)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onItemClick(position: Int) {
        changeFragment(buttonItem[position].text)
    }
}