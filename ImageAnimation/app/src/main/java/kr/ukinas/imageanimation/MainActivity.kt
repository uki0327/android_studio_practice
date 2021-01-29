package kr.ukinas.imageanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.ukinas.imageanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var bartIsShowing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bartImage = binding.bartImage
        bartImage.x = -1000f
    }

    fun fade(view: View) {
        val bartImage = binding.bartImage
        //val homerImage = binding.homerImage
        bartImage.animate().translationXBy(1000f).rotation(360f).setDuration(2000)
    }
}