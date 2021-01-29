package kr.ukinas.sharedpremenu

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kr.ukinas.sharedpremenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var database = FirebaseDatabase.getInstance().reference
        database.child("message").setValue("Hello World!")

        var getMessage = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var value = snapshot.child("message").value.toString()
                binding.languageDisplayView.setText(value)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        database.addValueEventListener(getMessage)
        database.addListenerForSingleValueEvent(getMessage)

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        var lang = sharedPreferences.getString("LANGUAGE", "NONE")

        if (lang == "NONE") {

        } else {
            binding.languageDisplayView.setText("$lang")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setIcon(R.drawable.ic_baseline_add_box)
                alertDialog.setTitle("Language settings")
                alertDialog.setMessage("Select English or Dutch")
                alertDialog.setPositiveButton("English", DialogInterface.OnClickListener { dialog, which ->
                    setLanguage("English")
                })
                alertDialog.setNegativeButton("Dutch", DialogInterface.OnClickListener { dialog, which ->
                    setLanguage("Dutch")
                })
                alertDialog.show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setLanguage(lang: String) {
        val editor = sharedPreferences.edit()
        editor.putString("LANGUAGE", lang)
        editor.apply()

        binding.languageDisplayView.setText("$lang")

        Toast.makeText(this, lang, Toast.LENGTH_LONG).show()
    }
}
