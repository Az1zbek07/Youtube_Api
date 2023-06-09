package com.example.youtubeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.databinding.ActivityPlayerBinding
import com.example.youtubeapi.model.Item
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayerActivity : YouTubeBaseActivity() {
    private val binding by lazy { ActivityPlayerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Item>("item") as Item

        binding.tvVideoTitle.text = item.snippet.title
        binding.tvVideoDescription.text = item.snippet.description

        binding.youtubePlayer.initialize(
            "AIzaSyAZ3ALbe4vzKb2UQgb1gnlEyATyKdgAC0Y",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    youtubePlayer: YouTubePlayer?,
                    p2: Boolean
                ) {
                    youtubePlayer?.let {
                        it.loadVideo(item.id.videoId)
                        it.play()
                    }
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.d("@@@", "onInitializationFailure: $")
                }
            })
    }
}