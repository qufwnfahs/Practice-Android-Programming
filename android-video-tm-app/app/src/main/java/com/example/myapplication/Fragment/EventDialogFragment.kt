package com.example.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.MainActivity
import com.example.myapplication.Model.VideoObject
import com.example.myapplication.R
import kotlinx.android.synthetic.main.dialog_video_add.*
import kotlinx.android.synthetic.main.dialog_video_add.view.*
import java.text.SimpleDateFormat
import java.util.*

class EventDialogFragment : DialogFragment(), View.OnClickListener {

    companion object {
        fun getInstance() : EventDialogFragment {
            var e = EventDialogFragment()
            return e
        }
        val TAG_EVENT_DIALOG = "dialog_event"
    }

    override fun onClick(v: View?) {
        when (v) {
            button_add -> {
                addVideo()
            }
            button_cancel -> dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.dialog_video_add, container)
        isCancelable = false

        view.button_add.setOnClickListener(this)
        view.button_cancel.setOnClickListener(this)

        return view
    }

    fun addVideo()
    {
        var canAdd = check()

        if (!canAdd) {
            return
        }
        var title = editText_title.text.toString()
        var media_url = editText_link.text.toString()
        var start = editText_start.text.toString()
        var end = editText_end.text.toString()
        var author = editText_author.text.toString()
c
        var index = media_url.lastIndexOf("/")
        var id = media_url.substring(index+1, media_url.length)

        var thumbnail = "https://img.youtube.com/vi/${id}/0.jpg"
        var dateFormat = SimpleDateFormat("yyyy. M. dd. 'at' hh:mm aaa", Locale.US)

        var date = dateFormat.format(Date())

        var videoObject = VideoObject(title, media_url, thumbnail, author, date, start, end)

        MainActivity.databaseReference.child(id).setValue(videoObject)

        dismiss()
    }

    fun check() : Boolean {
        if (editText_title.text.toString().replace(" ", "").equals("")) {
            Toast.makeText(context, "제목을 입력해주세요", Toast.LENGTH_LONG).show()
            editText_title.requestFocus()

            return false
        }
        if (editText_author.text.toString().replace(" ", "").equals("")) {
            Toast.makeText(context, "작성자를 입력해주세요", Toast.LENGTH_LONG).show()
            editText_author.requestFocus()

            return false
        }
        if (editText_link.text.toString().replace(" ", "").equals("")) {
            Toast.makeText(context, "주소를 입력해주세요", Toast.LENGTH_LONG).show()
            editText_link.requestFocus()

            return false
        }

        var start = editText_start.text.toString().split(":")
        var end = editText_end.text.toString().split(":")

        if (start[0].toFloat() * 60 + start[1].toFloat() > end[0].toFloat() * 60 + end[1].toFloat() && !editText_end.text.toString().equals("0:00")) {
            Toast.makeText(context, "시작 시간이 종료 시간보다 늦을 수 없습니다.", Toast.LENGTH_LONG).show()
            editText_start.requestFocus()

            return false
        }

        return true;
    }

}