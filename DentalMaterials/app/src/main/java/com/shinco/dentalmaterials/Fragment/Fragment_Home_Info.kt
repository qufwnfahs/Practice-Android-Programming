package com.shinco.dentalmaterials.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_home_info.view.*



class Fragment_Home_Info(var text : String) : Fragment(), View.OnClickListener {

    internal lateinit var view : View

    init {
        text = text.replace(" ", "").replace(",", "")
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
        var textViews = view.textView_title
        textViews.setTypeface(ResourcesCompat.getFont(context!!, R.font.iropkebatang));

        var texts = resources.getStringArray(resources.getIdentifier(text, "array", activity?.packageName))

        if (texts.size != 0) {
            var points = texts[0].split(" ");

            for (i in 1..texts.size-1) {
                if (points.contains(i.toString())) {
                    textViews.append(Controller.getStringBold(texts[i]));
                }
                else
                {
                    textViews.append(Controller.createdIndent(texts[i]));
                }
            }
        }
    }

    fun setButtonEvent() {
        view.button_close.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }
}