package com.shinco.dentalmaterials.Fragment


import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginBottom
import androidx.core.widget.TextViewCompat
import androidx.navigation.fragment.findNavController
import com.shinco.dentalmaterials.Adapter.ExperimentRVAdapter
import com.shinco.dentalmaterials.Model.NeedItem
import com.shinco.dentalmaterials.R
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.view.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.view.frameLayout_exp


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_Experiment_Preparation : Fragment(), View.OnClickListener {

    internal lateinit var view : View
    lateinit var text : String
    lateinit var mapButtons : HashMap<Button, ArrayList<String>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment__experiment__preparation, container, false)
        text = findNavController().graph.arguments.get("SubTitle")!!.defaultValue.toString()

        init()
        return view
    }

    fun init() {
        var condition = setIdentifier("needs__", text)
        var items = resources.getStringArray(resources.getIdentifier(condition, "array", context!!.packageName))

        if (items.size > 0) {
            setNeeds(items)
            setNeedsKR()
            setTimerTexts()
            setButtons()
        }
        else
        {
            view.layout_mainContainer.visibility = View.INVISIBLE;
        }
    }

    fun setIdentifier(identifier : String, str : String) : String {
        var query = str.replace(",", "").replace("(", "").replace(")", "").replace(" ", "_").toLowerCase()
        return identifier + query;
    }

    fun setNeeds(needs : Array<String>) {
        var frameLayout = view.frameLayout_exp
        var imageView = ImageView(context)
        var drawables = ContextCompat.getDrawable(context!!, resources.getIdentifier(needs[0], "drawable", activity?.packageName))
        var layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))

        imageView.setImageDrawable(drawables)
        imageView.layoutParams = layoutParams
        imageView.scaleType = ImageView.ScaleType.FIT_XY

        frameLayout.addView(imageView)
    }

    fun setNeedsKR() {
        var identifier = "NeedsKR" + text.replace(",", "").replace(" ", "").replace("(", "").replace(")", "")
        var needsKR = resources.getStringArray(resources.getIdentifier(identifier, "array", activity?.packageName))

        var textView_needs = view.textView_needs
        var textString = ""

        for (item in needsKR) {
            textString += item + ", "
        }

        textString = textString.removeRange(textString.length-2, textString.length)

        textView_needs.text = textString
    }

    fun setTimerTexts() {
        var identifier = "Time" + text.replace(",", "").replace(" ", "").replace("(", "").replace(")", "")

        var times = resources.getStringArray(resources.getIdentifier(identifier, "array", activity!!.packageName))

        //view.textView_working_time.text = "Etching\n" + "( " + times[0] + " )"
        //view.textView_setting_time.text = "Photopolymerization\n1" + " ( " + times[1] + " )"
        //view.textView_setting_time2.text = "Photopolymerization\n2" + " ( " + times[2] + " )"
    }

    fun setButtons() {
        mapButtons = HashMap<Button, ArrayList<String>>()
        var identifier = setIdentifier("timer__", text)
        var timers = resources.getStringArray(resources.getIdentifier(identifier, "array", context!!.packageName))

        for (time in timers) {
            var items = time.split(",")
            var title = items[0]
            var value = items[1]
            var colorType = items[2].toInt()
            var iov = items[3]

            var viewLayout = ConstraintLayout(context)
            var layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            layoutParams.weight = 1.0f
            layoutParams.setMargins(0,0,0,8)

            viewLayout.id = View.generateViewId()
            viewLayout.layoutParams = layoutParams;

            var tV = TextView(context)
            var tVLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT))

            tV.layoutParams = tVLayoutParams
            tV.id = View.generateViewId()
            tV.text = title + " ( " + value + " ) "
            tV.gravity = Gravity.CENTER
            tV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            tV.setTypeface(ResourcesCompat.getFont(context!!, R.font.iropkebatang), Typeface.BOLD)
            tV.setTextColor(ContextCompat.getColor(context!!, R.color.blue_nights))

            var button = Button(context)
            var buttonLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT))

            button.layoutParams = buttonLayoutParams
            button.text = "Start"
            button.id = View.generateViewId()
            if (colorType == 0) button.background = ContextCompat.getDrawable(context!!, R.drawable.selector_working_time)
            else button.background = ContextCompat.getDrawable(context!!, R.drawable.selector_setting_time)

            viewLayout.addView(tV)
            viewLayout.addView(button)

            var constraintSet = ConstraintSet()
            constraintSet.clone(viewLayout)

            constraintSet.connect(tV.id, ConstraintSet.TOP, viewLayout.id, ConstraintSet.TOP)
            constraintSet.connect(tV.id, ConstraintSet.BOTTOM, viewLayout.id, ConstraintSet.BOTTOM)
            constraintSet.connect(tV.id, ConstraintSet.START, viewLayout.id, ConstraintSet.START)
            constraintSet.connect(tV.id, ConstraintSet.END, button.id, ConstraintSet.START)

            constraintSet.connect(button.id, ConstraintSet.TOP, viewLayout.id, ConstraintSet.TOP)
            constraintSet.connect(button.id, ConstraintSet.BOTTOM, viewLayout.id, ConstraintSet.BOTTOM)
            constraintSet.connect(button.id, ConstraintSet.START, tV.id, ConstraintSet.END)
            constraintSet.connect(button.id, ConstraintSet.END, viewLayout.id, ConstraintSet.END)

            constraintSet.applyTo(viewLayout)

            button.setOnClickListener(this)

            view.layout_buttons.addView(viewLayout)

            // Data Binding
            var arr = arrayListOf(title, value, colorType.toString(), iov)
            mapButtons[button] = arr
        }
    }

    override fun onClick(v: View?) {
        var arr = mapButtons[v]
        var bundle = Bundle()

        bundle.putString("type", arr!![0])
        bundle.putString("value", arr!![1])
        bundle.putString("colorType", arr!![2])
        bundle.putString("iov", arr!![3])
        bundle.putString("subject", text.replace(",", "").replace("(", "").replace(")", "").replace(" ", "_").toLowerCase())

        findNavController().navigate(R.id.action_fragment_Experiment_Preparation_to_fragment_Timer, bundle)
    }
}
