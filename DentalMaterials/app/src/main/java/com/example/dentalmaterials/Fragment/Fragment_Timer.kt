package com.example.dentalmaterials.Fragment


import android.content.res.ColorStateList
import android.graphics.*
import android.media.session.PlaybackState
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dentalmaterials.Adapter.ExperimentRVAdapter
import com.example.dentalmaterials.Model.NeedItem
import com.example.dentalmaterials.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.exo_player_control_view.*
import kotlinx.android.synthetic.main.exo_player_control_view.view.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.view.*
import kotlinx.android.synthetic.main.fragment__experiment__preparation.view.textView_e_p_title
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_Timer : Fragment(), Player.EventListener {

    internal lateinit var view : View
    lateinit var text : String
    lateinit var type : String
    lateinit var exoPlayer : SimpleExoPlayer
    var duration : Int = 0
    var isPlayed = false
    var mPaused = false
    var handler : Handler? = null
    var thread : Thread? = null
    var mPauseLock : Object? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_timer, container, false)
        text = arguments!!.get("subject").toString()
        type = arguments!!.get("type").toString()

        setTitle()
        setProgress()
        setVideo()

        return view
    }

    fun setTitle() {
        var identifier = "${type}Time${text}"
        var title = resources.getStringArray(resources.getIdentifier(identifier, "array", activity!!.packageName))

        view.textView_e_p_title.text = "${type} Time : ${title[0]}"

        setTimer(title[0])
    }

    fun setTimer(time : String) {
        if (time.contains("sec"))
        {
            duration = time.replace("sec", "").toInt()
        }
        else
        {
            if (time.contains("."))
            {
                var minsec = time.replace("min", "").split(".")
                duration = (minsec[0].toInt() * 60) + minsec[1].toInt()
            }
            else
            {
                duration = time.replace("min", "").toInt() * 60
            }
        }

        view.textView_timer.text = "${duration}sec"
    }

    fun setProgress() {
        view.progressBar.max = 100
        view.progressBar.progress = 100

        if (type == "Working")
        {
            view.progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#FFE375"))
        }
        else
        {
            view.progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#34A853"))
        }
    }

    fun setVideo() {
        view.playerView_timer.useController = false
        view.playerView_timer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        var trackSelector = DefaultTrackSelector()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

        view.playerView_timer.player = exoPlayer
        exoPlayer.playWhenReady = true

        var dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "TimerPlayer"))
        var videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.alginate))

        view.playerView_timer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        exoPlayer.prepare(videoSource)

        exoPlayer.addListener(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        exoPlayer.release()
        thread?.interrupt()

        super.onDestroy()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playWhenReady && playbackState == ExoPlayer.STATE_ENDED){
            thread?.interrupt()
        }
        if (playWhenReady && playbackState == ExoPlayer.STATE_READY)
        {
            startTimer()
        }
    }

    fun startTimer() {
        mPauseLock = Object()
        handler = Handler();
        var runnable = Runnable() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (e: InterruptedException) {
                        break;
                    }
                    handler?.post(Runnable() {
                        view.textView_timer.text = "${exoPlayer.duration / 1000 - exoPlayer.currentPosition / 1000}sec";
                        view.progressBar.max = (exoPlayer.duration / 1000).toInt()
                        view.progressBar.progress = (exoPlayer.duration / 1000 - exoPlayer.currentPosition / 1000).toInt()
                    });
                }
        }
        thread = Thread(runnable);
        thread?.start()
    }
}
