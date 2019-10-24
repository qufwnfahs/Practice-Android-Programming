package com.shinco.dentalmaterials.Controller

import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.*
import androidx.core.content.res.ResourcesCompat
import com.shinco.dentalmaterials.R
import com.shinco.dentalmaterials.Typeface.CustomTypeFaceSpan
import android.text.style.BulletSpan
import android.text.SpannableString




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

        fun getStringBold(str : String) : SpannableStringBuilder {
            var ssb = SpannableStringBuilder(str)

            ssb.setSpan(StyleSpan(Typeface.BOLD), 0, str.length-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            return ssb;
        }

        fun getStringForENGAndKOR(context : Context, str : String) : SpannableStringBuilder {
            var ssb = SpannableStringBuilder(str)

            var font_eng = ResourcesCompat.getFont(context, R.font.iropkebatang)
            var font_kor = ResourcesCompat.getFont(context, R.font.iropkebatang)

            /*
            for (i in 0..str.length-1) {
                // ENG
                if (str.codePointAt(i) >= 65 && str.codePointAt(i) <= 122) {
                    ssb.setSpan(CustomTypeFaceSpan(font_eng!!), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                else {
                    ssb.setSpan(CustomTypeFaceSpan(font_kor!!), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
            */
            ssb.setSpan(CustomTypeFaceSpan(font_eng!!), 0, str.length-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ssb
        }

        fun fromHtml(source : String) : Spanned {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                // noinspection deprecation
                return Html.fromHtml(source);
            }
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        }

        fun createdIndent(source: String) : SpannableStringBuilder {
            val content = SpannableStringBuilder()

            val contentStart = content.length

            content.append(source)

            /*val contentEnd = content.length
            content.setSpan(
               LeadingMarginSpan.Standard(0, 50),
               contentStart,
               contentEnd,
               Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            */
            //content.setSpan(RelativeSizeSpan(1.0f), 0, source.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            content.setSpan(BulletSpan(20), 0, source.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            return content
        }
    }
}