package com.example.myapplication.Listener

import android.text.Editable
import android.text.TextWatcher
import com.example.myapplication.Adapter.ShincoRVAdapter

class ShincoTextWatcher(val adapter : ShincoRVAdapter) : TextWatcher {
    override fun afterTextChanged(edit: Editable?) {
        adapter.search(edit.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}