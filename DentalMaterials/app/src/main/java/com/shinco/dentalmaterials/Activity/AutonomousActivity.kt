package com.shinco.dentalmaterials.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.fragment.NavHostFragment
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.activity_autonomous.*

class AutonomousActivity : AppCompatActivity() {
    lateinit var text : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autonomous)

        text = intent.getStringExtra("SubTitle")

        textView_subTitle.text = text

        setGraph()
    }

    fun setGraph() {
        val navHostFragment = navHost as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)

        val bundle = NavArgument.Builder().setDefaultValue(text).build()

        graph.addArgument("SubTitle", bundle)

        navHostFragment.navController.graph = graph
    }
}