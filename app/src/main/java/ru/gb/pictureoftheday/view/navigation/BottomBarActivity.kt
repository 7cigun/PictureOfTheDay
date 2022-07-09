package ru.gb.pictureoftheday.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.gb.pictureoftheday.R
import ru.gb.pictureoftheday.databinding.ActivityBottomBarBinding
import ru.gb.pictureoftheday.view.settings.SettingsFragment

const val SkyTheme = 1
const val FlowerTheme = 2
const val LimeTheme = 3


class BottomBarActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    private lateinit var binding: ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_view_earth ->{navigateTo(EarthFragment()); true}
                R.id.action_view_mars ->{navigateTo(MarsFragment());true}
                R.id.action_view_system ->{navigateTo(SystemFragment());  true}
                R.id.action_settings ->{navigateTo(SettingsFragment());  true}
                else ->true
            }

        }
        binding.bottomNavigationView.selectedItemId = R.id.action_view_earth

    }

    private fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            SkyTheme -> R.style.SkyTheme
            FlowerTheme -> R.style.FlowerTheme
            LimeTheme -> R.style.LimeTheme
            else -> 0
        }
    }

}