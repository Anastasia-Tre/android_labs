package com.nasa.lab4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioListAdapter(
    private val audioList: ArrayList<AudioFile>,
    private val onClick: (AudioFile) -> Unit
) :
    RecyclerView.Adapter<AudioListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (AudioFile) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val artist: TextView = view.findViewById(R.id.artist)
        private var currentAudio: AudioFile? = null

        init {
            Log.v("Audio", "in ViewHolder")
            view.setOnClickListener {
                currentAudio?.let {
                    onClick(it)
                }
            }
        }

        fun bind(audio: AudioFile) {
            currentAudio = audio
            Log.v("Audio", "in bind")
            title.text = audio.title
            artist.text = audio.artist
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.v("Audio", "in onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.audio_list, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audio = audioList[position]
        holder.bind(audio)
    }

    override fun getItemCount(): Int {
        return audioList.size
    }

}