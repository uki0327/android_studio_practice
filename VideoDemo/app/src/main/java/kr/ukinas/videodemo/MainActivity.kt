package kr.ukinas.videodemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kr.ukinas.videodemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val video = binding.videoView
        var mediaControls = MediaController(this)
        video.setVideoURI(Uri.parse("android.resource://" + packageName +  "/" + R.raw.testvideo))
        video.setMediaController(mediaControls)
        video.start()
    }
}