package com.shinco.dentalmaterials.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shinco.dentalmaterials.Controller.Controller
import com.shinco.dentalmaterials.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_practice_play.*
import kotlinx.android.synthetic.main.exo_player_control_view.*

class PracticePlayActivity : AppCompatActivity(), View.OnClickListener, Player.EventListener {

    lateinit var text : String
    lateinit var exoPlayer : SimpleExoPlayer

    val identifier = "video__practice_";

    var isPlaying = true
    var isEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_play)

        init()
    }

    fun init() {
        setSubTitle()
        setVideo()
    }

    fun setSubTitle() {
        text = intent.getStringExtra("SubTitle")!!

        var start_to_end_point = HashMap<Int, Int>()
        start_to_end_point.put(0, 1)

        textView_subTitle.text = Controller.getStringForColorString(start_to_end_point, text, ContextCompat.getColor(applicationContext, R.color.accent_word))
    }

    fun setVideo() {
        var condition = setIdentifier(text)
        var videoURL = resources.getString(resources.getIdentifier(condition, "string", this.packageName))

        if (videoURL != "null")
        {
            textView_subtitle.text = text
            exo_play.visibility = View.INVISIBLE
            exo_play.setOnClickListener(this)
            exo_pause.setOnClickListener(this)
            exo_fullscreen_icon.setOnClickListener(this)

            var trackSelector = DefaultTrackSelector()
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

            playerView.player = exoPlayer
            exoPlayer.playWhenReady = true

            var dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "VideoPlayer"))
            var video = resources.getIdentifier(videoURL, "raw", this.packageName)
            var videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(video))

            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            exoPlayer.prepare(videoSource)

            exoPlayer.addListener(this)
        }
        else
        {
            playerView.visibility = View.INVISIBLE;
        }
    }

    fun setIdentifier(str: String) : String {
        var query = str.replace(",", "").replace("(", "").replace(")", "").replace(" ", "_").toLowerCase()
        return identifier + query;
    }

    override fun onPause() {
        if (::exoPlayer.isInitialized) {
            exoPlayer.playWhenReady = false
            isPlaying = false
        }
        super.onPause()
    }

    override fun onDestroy() {
        if (::exoPlayer.isInitialized) {
            exoPlayer.release()
        }
        super.onDestroy()
    }

    fun switch() {
        if (isPlaying) {
            isPlaying = false
            exoPlayer.playWhenReady = false
            exo_play.visibility = View.VISIBLE
            exo_pause.visibility = View.INVISIBLE
        }
        else
        {
            isPlaying = true
            exoPlayer.playWhenReady = true
            exo_play.visibility = View.INVISIBLE
            exo_pause.visibility = View.VISIBLE
        }
    }

    fun fullScreen() {
        var intent = Intent(this, FullScreenActivity::class.java)
        intent.putExtra("SubTitle", text)
        intent.putExtra("Position", exoPlayer.currentPosition)
        startActivityForResult(intent, 1)
    }

    override fun onClick(v: View?) {
        when (v) {
            exo_play -> {
                if (isEnded) {
                    isEnded = false
                    exo_play.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.exo_controls_play))
                    exoPlayer.seekTo(0)
                    switch()
                }
                else
                {
                    switch()
                }
            }
            exo_pause -> switch()
            exo_fullscreen_icon -> fullScreen()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        exoPlayer.seekTo(resultCode.toLong())
        switch()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playWhenReady && playbackState == Player.STATE_ENDED) {
            isPlaying = false
            isEnded = true
            exo_play.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.iconmostr_repeat))
        }
    }
}