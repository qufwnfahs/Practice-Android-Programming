package com.example.dentalmaterials.Fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dentalmaterials.Adapter.ExperimentRVAdapter
import com.example.dentalmaterials.Model.NeedItem
import com.example.dentalmaterials.R
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.view.*

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
    lateinit var needItems : ArrayList<NeedItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment__experiment__preparation, container, false)
        text = findNavController().graph.arguments.get("SubTitle")!!.defaultValue.toString().replace(" ", "")
        needItems = arrayListOf<NeedItem>()

        setNeeds()
        setTimerTexts()
        view.button_working_time.setOnClickListener(this)
        view.button_setting_time.setOnClickListener(this)

        return view
    }

    fun setNeeds() {
        var identifier = "Needs" + text
        var needs = resources.getStringArray(resources.getIdentifier(identifier, "array", activity!!.packageName))

        var recyclerView = view.recyclerView_needs
        var layoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.SPACE_AROUND
        }

        recyclerView.layoutManager = layoutManager

        for (item in needs) {
            needItems.add(NeedItem(item, 20))
        }

        var recyclerAdapter = ExperimentRVAdapter(needItems, recyclerView)
        recyclerView.adapter = recyclerAdapter
    }

    fun setTimerTexts() {
        var identifier_wt = "WorkingTime" + text
        var identifier_st = "SettingTime" + text

        var wt = resources.getStringArray(resources.getIdentifier(identifier_wt, "array", activity!!.packageName))
        var st = resources.getStringArray(resources.getIdentifier(identifier_st, "array", activity!!.packageName))

        view.textView_working_time.text = "Working Time\n" + wt[0]
        view.textView_setting_time.text = "Setting Time\n" + st[0]
    }

    override fun onClick(v: View?) {
        when (v) {
            view.button_working_time -> {
                var bundle = Bundle()
                bundle.putString("type", "Working")
                bundle.putString("subject", text)
                findNavController().navigate(R.id.action_fragment_Experiment_Preparation_to_fragment_Timer, bundle)
            }
            view.button_setting_time -> {
                var bundle = Bundle()
                bundle.putString("type", "Setting")
                bundle.putString("subject", text)
                findNavController().navigate(R.id.action_fragment_Experiment_Preparation_to_fragment_Timer, bundle)
            }
        }
    }
}
