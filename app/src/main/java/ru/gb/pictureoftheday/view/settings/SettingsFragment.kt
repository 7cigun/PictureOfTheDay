package ru.gb.pictureoftheday.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.gb.pictureoftheday.R
import ru.gb.pictureoftheday.databinding.FragmentSettingsBinding
import ru.gb.pictureoftheday.view.navigation.BottomBarActivity
import ru.gb.pictureoftheday.view.navigation.SkyTheme
import ru.gb.pictureoftheday.view.navigation.FlowerTheme
import ru.gb.pictureoftheday.view.navigation.LimeTheme

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var parentActivity: BottomBarActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as BottomBarActivity)
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkyTheme.setOnClickListener(this)
        binding.btnFlowerTheme.setOnClickListener(this)
        binding.btnLimeTheme.setOnClickListener(this)

        when (parentActivity.getCurrentTheme()) {
            1 -> binding.radioGroup.check(R.id.btnSkyTheme)
            2 -> binding.radioGroup.check(R.id.btnFlowerTheme)
            3 -> binding.radioGroup.check(R.id.btnLimeTheme)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSkyTheme -> {
                parentActivity.setCurrentTheme(SkyTheme)
                parentActivity.recreate()
            }
            R.id.btnFlowerTheme -> {
                parentActivity.setCurrentTheme(FlowerTheme)
                parentActivity.recreate()
            }
            R.id.btnLimeTheme -> {
                parentActivity.setCurrentTheme(LimeTheme)
                parentActivity.recreate()
            }
        }

    }

    companion object {
        fun newInstance() = SettingsFragment()
    }


}