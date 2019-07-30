package com.example.myapplication.Holder

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.Adapter.RecyclerViewAdapter
import com.example.myapplication.Model.VideoObject
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class ViewHolder (itemView: View, adapter: RecyclerViewAdapter, val lifeCycle: Lifecycle) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        YouTubePlayerListener {
    override fun onApiChange(youTubePlayer: YouTubePlayer) {

    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        if (second >= endtime && endtime != 0f) {
            youTubePlayer.seekTo(starttime)
            youTubePlayer.pause()
        }
    }

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {

    }

    override fun onPlaybackQualityChange(
        youTubePlayer: YouTubePlayer,
        playbackQuality: PlayerConstants.PlaybackQuality
    ) {

    }

    override fun onPlaybackRateChange(youTubePlayer: YouTubePlayer, playbackRate: PlayerConstants.PlaybackRate) {

    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {

    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {

    }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
    }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {

    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        val videoId = adapter.getMediaURL(adapterPosition)
        youTubePlayer.loadVideo(videoId, adapter.getStartTime(adapterPosition))
    }

    val date_n_author = itemView.textView_date_n_author
    val play : ImageView = itemView.play
    val thumbnail : ImageView = itemView.thumbnail
    val mediaContainter : FrameLayout = itemView.media_container
    val adapter : RecyclerViewAdapter = adapter
    var starttime: Float = 0.0f
    var endtime: Float = 0.0f
    lateinit var requestManager: RequestManager

    init {
        play.setOnClickListener(this);
    }
    override fun onClick(v: View?) {
        thumbnail.visibility = View.INVISIBLE
        play.visibility = View.INVISIBLE

        var youtubePlayerView = YouTubePlayerView(itemView.context)
        mediaContainter.addView(youtubePlayerView)
        lifeCycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(this)
    }
    fun onBind(videoObject : VideoObject, requestManager : RequestManager) {
        this.requestManager = requestManager

        itemView.setTag(this)
        itemView.title.text = if (videoObject.end.equals("0:00")) "${videoObject.title} (${videoObject.start} ~ 끝)" else "${videoObject.title} (${videoObject.start} ~ ${videoObject.end})"
        itemView.textView_date_n_author.text = "${videoObject.date}    by  ${videoObject.author}"
        requestManager.load(videoObject.thumbnail)
            .into(itemView.thumbnail)

        starttime = adapter.getStartTime(adapterPosition)
        endtime = adapter.getEndTime(adapterPosition)
    }
}