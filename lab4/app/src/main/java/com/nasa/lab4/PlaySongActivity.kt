package com.nasa.lab4

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView


class PlaySongActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private val mHandler = Handler()

    lateinit var positionBar: SeekBar
    lateinit var elapsedTimeLabel: TextView
    lateinit var remainingTimeLabel: TextView
    lateinit var playBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_song)

        val audioFile = intent.getParcelableExtra<AudioFile>("audioFile")
        val title = findViewById<TextView>(R.id.title)
        title.text = audioFile!!.displayName

        mp = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            reset()
            setDataSource(applicationContext, audioFile.uri)
            prepare()
            start()
            setVolume(0.5f, 0.5f)
            isLooping = true
        }

        setUIElements()

    }

    private fun setUIElements() {
        positionBar = findViewById(R.id.positionBar)
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel)
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel)
        playBtn = findViewById(R.id.playBtn)

        setPlayBtn()
        setPositionBar()
        setVolumeBar()
        updatePositionBar()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mp.stop()
    }

    private fun updatePositionBar() {
        mHandler.postDelayed(mUpdatePositionBar, 100);
    }

    private val mUpdatePositionBar: Runnable = object : Runnable {
        override fun run() {
            val currentPosition = mp.currentPosition
            positionBar.progress = currentPosition
            elapsedTimeLabel.text = createTimeLabel(currentPosition)
            val remainingTime = createTimeLabel(mp.duration - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"

            mHandler.postDelayed(this, 100)
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        val min = time / 1000 / 60
        val sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    private fun setVolumeBar() {
        val volumeBar = findViewById<SeekBar>(R.id.volumeBar)
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        val volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )
    }

    private fun setPositionBar() {
        positionBar.max = mp.duration
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )
    }

    private fun setPlayBtn() {
        playBtn.setOnClickListener {
            if (mp.isPlaying) {
                mp.pause()
                it.setBackgroundResource(R.drawable.play)
            } else {
                mp.start()
                it.setBackgroundResource(R.drawable.stop)
            }
        }
    }

}