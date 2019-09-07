package com.example.dentalmaterials.Controller

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.ResourcesCompat
import com.example.dentalmaterials.R
import com.example.dentalmaterials.Typeface.CustomTypeFaceSpan

class Controller {
    companion object {
        fun getStringForColorString(start_to_end_point : Map<Int, Int>, str : String, color_id : Int) : SpannableStringBuilder {
            var ssb = SpannableStringBuilder(str)

            for (point in start_to_end_point.entries) {
                var start = point.key
                var end = point.value

                ssb.setSpan(ForegroundColorSpan(color_id), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            return ssb
        }

        fun getStringForENGAndKOR(context : Context, str : String) : SpannableStringBuilder {
            var ssb = SpannableStringBuilder(str)

            var font_eng = ResourcesCompat.getFont(context, R.font.iropkebatang)
            var font_kor = ResourcesCompat.getFont(context, R.font.iropkebatang)

            for (i in 0..str.length-1) {
                // ENG
                if (str.codePointAt(i) >= 65 && str.codePointAt(i) <= 122) {
                    ssb.setSpan(CustomTypeFaceSpan(font_eng!!), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                else {
                    ssb.setSpan(CustomTypeFaceSpan(font_kor!!), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }

            return ssb
        }
    }
}