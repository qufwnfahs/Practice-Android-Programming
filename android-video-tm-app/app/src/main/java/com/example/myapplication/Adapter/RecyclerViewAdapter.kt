package com.example.myapplication.Adapter

import android.provider.MediaStore
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.RequestManager
import com.example.myapplication.Holder.ViewHolder
import com.example.myapplication.Model.VideoObject
import com.example.myapplication.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.utils.TimeUtilities
import kotlinx.android.synthetic.main.activity_main.view.*


class RecyclerViewAdapter(var lifeCycle : Lifecycle, val requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var videoIds = arrayListOf<VideoObject>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        var holder = ViewHolder(view, this, lifeCycle)

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.onBind(videoIds.get(position), requestManager)
        }
    }

    override fun getItemCount(): Int {
        return videoIds.size
    }

    fun getMediaURL(position : Int): String {
        var url = videoIds[position].media_url
        var index = url.lastIndexOf("/")
        return url.substring(index+1, url.length)
    }

    fun getStartTime(position : Int) : Float {
        var starts = videoIds[position].start.split(":")
        var miniutes = starts[0].toFloat()
        var seconds = starts[1].toFloat()
        return 60 * miniutes + seconds
    }

    fun getEndTime(position : Int) : Float {
        var ends = videoIds[position].end.split(":")
        var miniutes = ends[0].toFloat()
        var seconds = ends[1].toFloat()
        return 60 * miniutes + seconds
    }

    fun update(dataList : ArrayList<VideoObject>)
    {
        videoIds = dataList
        notifyItemRangeChanged(0, itemCount)
    }

}