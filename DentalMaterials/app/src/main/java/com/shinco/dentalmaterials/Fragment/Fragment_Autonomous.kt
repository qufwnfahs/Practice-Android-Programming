package com.shinco.dentalmaterials.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shinco.dentalmaterials.Activity.AutonomousActivity
import com.shinco.dentalmaterials.Adapter.AutonomousRVAdapter
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.fragment_autonomous.view.*

class Fragment_Autonomous : Fragment(), View.OnClickListener {

    internal lateinit var view : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_autonomous, container, false)

        init()
        return view
    }

    fun init() {
        setButton()
    }

    fun setButton() {
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
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        var intent = Intent(activity, AutonomousActivity::class.java)

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