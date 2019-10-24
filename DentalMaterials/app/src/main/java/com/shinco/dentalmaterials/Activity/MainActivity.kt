package com.shinco.dentalmaterials.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.shinco.dentalmaterials.Adapter.ViewPagerAdapter
import com.shinco.dentalmaterials.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.*
import kotlinx.android.synthetic.main.tab.view.*

class MainActivity : AppCompatActivity(), TabLayout.BaseOnTabSelectedListener<TabLayout.Tab>, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    lateinit var tabNames : Array<String>
    lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        set_tab()
        setViewPager()
        setDrawerControls()
    }

    fun set_tab() {
        tabNames = resources.getStringArray(R.array.main_tabName)

        for (i in 0..tabNames.size-1) {
            var view = layoutInflater.inflate(R.layout.tab, null)
            view.textView_tab.text = tabNames[i]

            var tab = tabLayout.newTab()
            tab.setCustomView(view)
            tabLayout.addTab(tab)
        }
        tabLayout.getTabAt(0)?.select()

        set_tab_margin()
        setScrollableOrFixed()
    }

    fun set_tab_margin() {
        var tabStrip = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0..tabLayout.tabCount-2) {
            var tab = tabStrip.getChildAt(i)
            var marginLayoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.rightMargin = 15
        }
    }

    fun setDrawerControls() {
        button__setting.setOnClickListener(this)
        navigationView.setNavigationItemSelectedListener(this)
    }

    fun setScrollableOrFixed() {
        tabLayout.post(Runnable() {
            var tabCount = tabLayout.tabCount
            var tabStrip = tabLayout.getChildAt(0) as ViewGroup
            var layoutWidth = tabLayout.width
            var tabWidths = 0

            for (i in 0..tabCount-1) {
                tabWidths += tabStrip.getChildAt(i).width

                if (i < tabCount-1) {
                    tabWidths += 15 // right Margin
                }
            }

            if (layoutWidth > tabWidths) {
                tabLayout.tabMode = TabLayout.MODE_FIXED
                tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            }
            else
            {
                tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

                var singleTabWidths = (layoutWidth - 30) / 3

                for (i in 0..tabCount-1) {
                    tabStrip.getChildAt(i).minimumWidth = singleTabWidths
                }
            }
        })
    }

    fun setViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = viewPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewPager.setCurrentItem(tab!!.position)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        {
            if (viewPagerAdapter.getItem(tabLayout.selectedTabPosition).getChildFragmentManager().getFragments().size > 0) {
                viewPagerAdapter.getItem(tabLayout.selectedTabPosition).getChildFragmentManager().popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            button__setting -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else {
                    drawerLayout.openDrawer((GravityCompat.START))
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.menu__info -> {
                var intent = Intent(this, LibraryInfoActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }
}
