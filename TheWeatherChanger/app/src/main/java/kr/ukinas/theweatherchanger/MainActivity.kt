package kr.ukinas.theweatherchanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.ukinas.theweatherchanger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // var imageIndex = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onButtonClicked(view: View) {
        val image = binding.imageView
        if (image.tag == "fantastic") {
            image.setImageResource(R.drawable.wooden)
            image.tag = "wooden"
        } else if (image.tag == "wooden") {
            image.setImageResource(R.drawable.fantastic)
            image.tag = "fantastic"
        }
    }

}