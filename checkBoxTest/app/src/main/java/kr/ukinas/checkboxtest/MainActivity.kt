package kr.ukinas.checkboxtest

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listener = CompoundButton.OnCheckedChangeListener() { buttonView, isChecked ->
        if (isChecked) {
            when (buttonView.id) {
                R.id.checkApple -> Toast.makeText(this, "Apple checked", Toast.LENGTH_SHORT).show()
                R.id.checkBanana -> Toast.makeText(this, "Banana checked", Toast.LENGTH_SHORT).show()
                R.id.checkOrange -> Toast.makeText(this, "Orange checked", Toast.LENGTH_SHORT).show()
            }
        } else {
            when (buttonView.id) {
                R.id.checkApple -> Toast.makeText(this, "Apple unchecked", Toast.LENGTH_SHORT).show()
                R.id.checkBanana -> Toast.makeText(this, "Banana unchecked", Toast.LENGTH_SHORT).show()
                R.id.checkOrange -> Toast.makeText(this, "Orange unchecked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkApple.setOnCheckedChangeListener(listener)
        checkBanana.setOnCheckedChangeListener(listener)
        checkOrange.setOnCheckedChangeListener(listener)
    }
}