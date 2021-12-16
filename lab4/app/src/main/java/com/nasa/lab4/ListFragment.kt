package com.nasa.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.provider.MediaStore
import android.net.Uri
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        //getAudioFiles()

        //val text = view.findViewById<TextView>(R.id.text)
        //text.text = getAudioFiles()


        val list = getEmptyList()

        val audioList = view.findViewById<RecyclerView>(R.id.audio_list)
        audioList.layoutManager = LinearLayoutManager(view.context)
        audioList.adapter = AudioListAdapter(list) { audio -> adapterOnClick(audio) }
        return view
    }

    fun getAudioFiles(): String {
        var text = ""

        val audioList = arrayListOf<AudioFile>()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)))
                val artist =
                    cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)))
                val duration =
                    cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)))
                //val url = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.DATA)))
                val modelAudio = AudioFile()

                audioList.add(modelAudio)
                text += (title + "\n")
            } while (cursor.moveToNext())
        }

        return text

    }

    fun getEmptyList(): ArrayList<AudioFile> {
        val audioList = arrayListOf<AudioFile>()
        var i = 0
        while (i < 20) {
            i++
            val audioFile = AudioFile()
            audioFile.title = "title$i"
            audioFile.artist = "artist$i"
            audioList.add(audioFile)
        }
        return audioList
    }


    private fun adapterOnClick(audio: AudioFile) {
//        val intent = Intent(this, FlowerDetailActivity()::class.java)
//        intent.putExtra(FLOWER_ID, flower.id)
//        startActivity(intent)
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
    }


}