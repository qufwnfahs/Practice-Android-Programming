package com.shinco.dentalmaterials.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shinco.dentalmaterials.Activity.PracticePlayActivity
import com.shinco.dentalmaterials.Adapter.PracticeRVAdapter
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.Model.PracticeItem
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_practice.view.*

class Fragment_Practice : Fragment(), View.OnClickListener {
    internal lateinit var view : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_practice, container, false)

        init()
        return view
    }

    fun init() {
        setButtonColorAndEvent()
    }

    fun setButtonColorAndEvent() {
        var buttons = arrayListOf(
                                    view.button__composite_resin,
                                    view.button__alginate,
                                    view.button__alginate_auto,
                                    view.button__dental_gypsum,
                                    view.button__dental_bleaching_tray,
                                    view.button__rubber_impression_materials,
                                    view.button__dental_wax,
                                    view.button__dental_cement_zoe_zpc,
                                    view.button__dental_cement_pcc_gic,
                                    view.button__dental_cad_cam)

        for (button in buttons) {
            var start_to_end_point = HashMap<Int, Int>();
            start_to_end_point.put(0, 1);

            button.text = Controller.getStringForColorString(start_to_end_point, button.text.toString(), ContextCompat.getColor(context!!, R.color.accent_word))
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        var intent = Intent(activity, PracticePlayActivity::class.java)

        when(v) {
            view.button__composite_resin -> intent.putExtra("SubTitle", getString(R.string.subject__composite_resin) )
            view.button__alginate -> intent.putExtra("SubTitle", getString(R.string.subject__alginate) )
            view.button__alginate_auto -> intent.putExtra("SubTitle", getString(R.string.subject__alginate_auto) )
            view.button__dental_gypsum -> intent.putExtra("SubTitle", getString(R.string.subject__dental_gypsum) )
            view.button__dental_bleaching_tray -> intent.putExtra("SubTitle", getString(R.string.subject__dental_bleaching_tray) )
            view.button__rubber_impression_materials -> intent.putExtra("SubTitle", getString(R.string.subject__rubber_impression_materials) )
            view.button__dental_wax -> intent.putExtra("SubTitle", getString(R.string.subject__dental_wax) )
            view.button__dental_cement_zoe_zpc -> intent.putExtra("SubTitle", getString(R.string.subject__dental_cement_zoe_zpc) )
            view.button__dental_cement_pcc_gic -> intent.putExtra("SubTitle", getString(R.string.subject__dental_cement_pcc_gic) )
            view.button__dental_cad_cam -> intent.putExtra("SubTitle", getString(R.string.subject__dental_cad_cam) )
        }

        startActivity(intent)
        activity?.overridePendingTransition(0, 0)
    }
}