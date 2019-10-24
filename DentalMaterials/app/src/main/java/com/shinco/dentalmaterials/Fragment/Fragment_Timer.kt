package com.shinco.dentalmaterials.Fragment


import android.content.res.ColorStateList
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.shinco.dentalmaterials.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_timer.view.*
import kotlinx.android.synthetic.main.fragment_timer.view.playerView_timer

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
    lateinit var subject : String
    lateinit var title : String
    lateinit var value : String
    var colorType : Int = 0
    var iov : Int = 0
    lateinit var exoPlayer : SimpleExoPlayer
    var duration : Int = 0
    var isPlayed = false
    var mPaused = false
    var handler : Handler? = null
    var thread : Thread? = null
    var mPauseLock : Object? = null

    val IS_IMAGE = 10
    val IS_VIDEO = 11

    val TYPE_ECTHING = "_etching"
    val TYPE_PHOTOPOLYMERIZATION_1 = "_pho_1"
    val TYPE_PHOTOPOLYMERIZATION_2 = "_pho_2"

    val IDENTIFIER_ECTHING = "Etching"
    val IDENTIFIER_PHOTOPOLYMERIZATION_1 = "Photopolymerization 1"
    val IDENTIFIER_PHOTOPOLYMERIZATION_2 = "Photopolymerization 2"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_timer, container, false)
        subject = arguments!!.get("subject").toString()
        title = arguments!!.get("type").toString()
        value = arguments!!.get("value").toString()
        colorType = arguments!!.get("colorType").toString().toInt()
        iov = arguments!!.get("iov").toString().toInt()

        setTitle()
        setProgress()

        if (iov == IS_IMAGE) {
            setImage()
        }
        else if (iov == IS_VIDEO) {
            setVideo()
        }

        return view
    }

    fun setTitle() {
        view.textView_e_p_title.text = "${title} : ${value}"

        setTimer()
    }

    fun setTimer() {
        if (value.contains("sec"))
        {
            duration = value.replace("sec", "").toInt()
        }
        else
        {
            if (value.contains("."))
            {
                var minsec = value.replace("min", "").split(".")
                duration = (minsec[0].toInt() * 60) + minsec[1].toInt()
            }
            else
            {
                duration = value.replace("min", "").toInt() * 60
            }
        }

        view.textView_timer.text = "${duration}sec"
    }

    fun setProgress() {
        view.progressBar.max = 100
        view.progressBar.progress = 100

        if (colorType == 0)
        {
            view.progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#FFE375"))
        }
        else
        {
            view.progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#34A853"))
        }
    }

    fun setImage() {
        var imageView = ImageView(context)
        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
        var identifier = getVideoIdentifier("timer__")
        var image = resources.getIdentifier(identifier, "drawable", context!!.packageName)
        imageView.setImageResource(image)
        imageView.layoutParams = lp
        imageView.scaleType = ImageView.ScaleType.FIT_XY

        view.playerView_timer.visibility = View.INVISIBLE
        view.constraintLayout2.addView(imageView)

        startTimer2()
    }


    fun setVideo() {
        // Get Video
        var identifier = getVideoIdentifier("practice__")
        var video_id = resources.getIdentifier(identifier, "raw", context!!.packageName)

        view.playerView_timer.useController = false
        view.playerView_timer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        var trackSelector = DefaultTrackSelector()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

        view.playerView_timer.player = exoPlayer
        exoPlayer.playWhenReady = true

        var dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "TimerPlayer"))
        var videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(video_id))

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
        if (::exoPlayer.isInitialized) {
            exoPlayer.release()
        }
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
        view.progressBar.max = (exoPlayer.duration / 1000).toInt()
        view.progressBar.progress = view.progressBar.max
        var runnable = Runnable() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (e: InterruptedException) {
                    break;
                }
                handler?.post(Runnable() {
                    view.textView_timer.text = "${(exoPlayer.duration / 1000 - exoPlayer.currentPosition / 1000) - 1}sec";
                    view.progressBar.progress = ((exoPlayer.duration / 1000 - exoPlayer.currentPosition / 1000) - 1).toInt()
                });
            }
        }
        thread = Thread(runnable);
        thread?.start()
    }

    fun startTimer2() {
        mPauseLock = Object()
        handler = Handler();
        view.progressBar.max = duration
        view.progressBar.progress = view.progressBar.max
        var runnable = Runnable() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (e: InterruptedException) {
                    break;
                }
                handler?.post(Runnable() {
                    if (duration >= 1) {
                        view.textView_timer.text = "${duration-1}sec";
                        view.progressBar.progress = duration-1
                        duration--
                    }
                });
            }
        }
        thread = Thread(runnable);
        thread?.start()
    }

    fun getVideoIdentifier(str: String) : String {
        var identifier = str + subject + "_" + title.replace(" ", "").toLowerCase();
        return identifier
    }

}
