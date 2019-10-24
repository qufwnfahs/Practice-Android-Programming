package com.shinco.dentalmaterials.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.R
import kotlinx.android.synthetic.main.activity_splash.*

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getHashKey();
        init()
        transition();
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null")

        for (signature in packageInfo!!.signatures) {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", "Code : " + signature.hashCode())
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }

        }
    }

    fun init() {
        setTitle()
        setAnimation()
    }

    fun setTitle() {
        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)
        start_to_end_point.put(7, 8)

        textView_splash_title.text = Controller.getStringForColorString(start_to_end_point, resources.getString(R.string.app_title), ContextCompat.getColor(applicationContext, R.color.accent_word) )
    }

    fun setAnimation() {
        var anim = AnimationUtils.loadAnimation(applicationContext, R.anim.alpha_anim)

        imageView_logo.startAnimation(anim)
        textView_splash_title.startAnimation(anim)
    }

    fun transition() {
        val intent = Intent(this, MainActivity::class.java)

        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                startActivity(intent)
                overridePendingTransition(0, 0) // 화면 전환 지연 없음
                finish()    // 액티비티 종료
            }
        }
        handler.sendEmptyMessageDelayed(0, 3000)
    }
}