package kr.ukinas.firebasetesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kr.ukinas.firebasetesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var database = FirebaseDatabase.getInstance().reference
        database.child("message").setValue("Hello World!")

        var getMessage = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var value = snapshot.child("message").value.toString()
                binding.languageDisplayView.setText(value)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        database.addValueEventListener(getMessage)
        database.addListenerForSingleValueEvent(getMessage)
    }
}