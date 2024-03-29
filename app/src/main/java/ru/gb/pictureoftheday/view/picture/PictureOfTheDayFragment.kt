package ru.gb.pictureoftheday.view.picture

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.pictureoftheday.MainActivity
import ru.gb.pictureoftheday.R
import ru.gb.pictureoftheday.databinding.FragmentPictureBinding
import ru.gb.pictureoftheday.view.drawer.BottomNavigationDrawerFragment
import ru.gb.pictureoftheday.view.settings.SettingsFragment
import ru.gb.pictureoftheday.viewmodel.AppState
import ru.gb.pictureoftheday.viewmodel.PictureOfTheDayViewModel
import java.time.LocalDate
import java.time.Period
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    val date = LocalDate.parse("2022-02-07")
    val todayDate = date.toString()
    val yesterdayDate = date.minus(Period.of(0, 0, 1)).toString()
    val TDBYDate = date.minus(Period.of(0, 0, 2)).toString()

    lateinit var spannableRainbow: SpannableString

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner
        ) {appState ->
            renderData(appState)
        }
        Toast.makeText(requireContext(), todayDate, Toast.LENGTH_LONG).show()
        viewModel.sendRequest(todayDate)

        getPictureByDate()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.input.text.toString()}")
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {}
            R.id.action_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().hide(this)
                    .add(R.id.container, SettingsFragment.newInstance()).addToBackStack("").commit()
            }
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getPictureByDate(){
        binding.chipYesterday.setOnClickListener {
            Toast.makeText(requireContext(), yesterdayDate, Toast.LENGTH_LONG).show()
            viewModel.sendRequest(yesterdayDate)
        }

        binding.chipToday.setOnClickListener {
            Toast.makeText(requireContext(), todayDate, Toast.LENGTH_LONG).show()
            viewModel.sendRequest(todayDate)
        }

        binding.chipTDBY.setOnClickListener {
            Toast.makeText(requireContext(), TDBYDate, Toast.LENGTH_LONG).show()
            viewModel.sendRequest(TDBYDate)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.Success -> {
                binding.ImageView.load(appState.pictureOfTheDayResponseData.url)
                binding.textView.text = appState.pictureOfTheDayResponseData.explanation
                binding.textView.typeface = Typeface.createFromAsset(requireActivity().assets,"fonts/acme.ttf")
                spannableRainbow = SpannableString(appState.pictureOfTheDayResponseData.explanation)
                rainbow(1)
            }
        }
    }

    fun rainbow(i:Int=1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount>3) 1 else currentCount
            }
            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }


    private fun colorText(colorFirstNumber:Int){
        binding.textView.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.textView.text as SpannableString
        val map = mapOf(
            0 to ContextCompat.getColor(requireContext(), R.color.red),
            1 to ContextCompat.getColor(requireContext(), R.color.orange),
            2 to ContextCompat.getColor(requireContext(), R.color.yellow),
            3 to ContextCompat.getColor(requireContext(), R.color.green),
            4 to ContextCompat.getColor(requireContext(), R.color.blue),
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 0 until binding.textView.text.length) {
            if (colorNumber == 3) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                ForegroundColorSpan(map.getValue(colorNumber)),
                i, i + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}