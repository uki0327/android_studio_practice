package kr.ukinas.viewbindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kr.ukinas.viewbindingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun clickFunction(view: View) {
        binding.editTextTextPersonName.text
        Log.i("Entered Name", binding.editTextTextPersonName.text.toString())
        Log.i("Info","Button Pressed")
        Toast.makeText(this, "${binding.editTextTextPersonName.text} has Entered", Toast.LENGTH_LONG).show()
    }
}