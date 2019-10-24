package com.shinco.dentalmaterials.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shinco.dentalmaterials.Fragment.Fragment_Autonomous
import com.shinco.dentalmaterials.Fragment.Fragment_Home
import com.shinco.dentalmaterials.Fragment.Fragment_Practice

class ViewPagerAdapter : FragmentPagerAdapter {
    var numOfTabs : Int = 0
    var childFragments : ArrayList<Fragment> = arrayListOf()

    constructor(fm : FragmentManager, numOfTabs : Int) : super(fm) {
        this.numOfTabs = numOfTabs
        childFragments = arrayListOf(Fragment_Home(), Fragment_Practice(), Fragment_Autonomous())
    }

    override fun getItem(position: Int): Fragment {
        return childFragments[position]
    }

    override fun getCount(): Int {
        return numOfTabs
    }
}
