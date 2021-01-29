package kr.ukinas.navfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kr.ukinas.navfragmentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val uploadFragment = UploadFragment()
        val favoritesFragment = FavoritesFragment()
        setFragmentView(homeFragment)
        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> setFragmentView(homeFragment)
                R.id.nav_upload -> setFragmentView(uploadFragment)
                R.id.nav_favorites -> setFragmentView(favoritesFragment)
            }
            true
        }
    }

    private fun setFragmentView(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragmant_view, fragment)
            commit()
        }

    }
}
