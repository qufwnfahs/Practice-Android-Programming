package com.shinco.dentalmaterials.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shinco.dentalmaterials.Adapter.RVAdapter
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.Model.ButtonItem
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.button__composite_resin

class Fragment_Home : Fragment(), View.OnClickListener {

    internal lateinit var view : View
    val margin : Int = 10
    var buttonItem : ArrayList<Button> = arrayListOf()

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
        setButtonEvent()
    }

    fun setTitle() {
        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)
        start_to_end_point.put(9, 10)

        view.textView_fragment_home.text = Controller.getStringForColorString(start_to_end_point, resources.getString(R.string.tab_home_title), resources.getColor(R.color.accent_word))
    }

    fun setButtonEvent() {
        buttonItem = arrayListOf(
                                    view.button__composite_resin,
                                    view.button__alginate,
                                    view.button__dental_gypsum,
                                    view.button__rubber_impression_materials,
                                    view.button__dental_wax,
                                    view.button__dental_cement,
                                    view.button__dental_cad_cam)

        for (button in buttonItem) {
            button.setOnClickListener(this)
        }
    }

    fun changeFragment(text: String) {
        var transaction = childFragmentManager.beginTransaction()

        transaction.replace(R.id.layout_fragment_home, Fragment_Home_Info(text), text)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClick(v: View) {
        var button : Button = v as Button;
        changeFragment(button.text.toString())
    }
}