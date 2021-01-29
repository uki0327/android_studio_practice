package kr.ukinas.youtubevideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kr.ukinas.youtubevideoplayer.databinding.ActivityMainBinding

class MainActivity : YouTubeBaseActivity() {
    private lateinit var myPlayerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializePlayer()
    }

    private fun initializePlayer() {
        myPlayerView = findViewById(R.id.youtube_view)
        myPlayerView.initialize("my youtube api code", object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                if (p1!= null && p2) { p1.play() }
                else {
                    p1!!.loadVideo("eEZHc8ZCPxk")
                    p1.play()
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

            }

        })
    }
}

