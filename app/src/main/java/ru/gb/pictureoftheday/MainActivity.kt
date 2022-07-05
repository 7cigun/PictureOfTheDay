package ru.gb.pictureoftheday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import ru.gb.pictureoftheday.databinding.ActivityMainBinding
import ru.gb.pictureoftheday.view.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setTheme(R.style.SkyTheme)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
            supportFragmentManager.beginTransaction().replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }
}