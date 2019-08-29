package com.example.dentalmaterials.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dentalmaterials.Controller.Controller
import com.example.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_home_info.*
import kotlinx.android.synthetic.main.fragment_home_info.view.*

class Fragment_Home_Info(var text : String) : Fragment(), View.OnClickListener {

    internal lateinit var view : View

    init {
        text = text.replace(" ", "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_home_info, container, false)

        setTexts()
        setButtonEvent()

        return view
    }

    fun setTexts() {
        var textViews = arrayListOf(view.textView_title, view.textView_character, view.textView_time_job, view.textView_time_stick, view.textView_needs)
        var texts = resources.getStringArray(resources.getIdentifier(text, "array", activity?.packageName))

        for (i in 0..textViews.size-1) {
            textViews[i].text = Controller.getStringForENGAndKOR(context!!, texts[i])
        }
    }

    fun setButtonEvent() {
        view.button_close.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }
}