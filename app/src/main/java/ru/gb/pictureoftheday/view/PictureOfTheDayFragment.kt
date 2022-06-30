package ru.gb.pictureoftheday.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.pictureoftheday.databinding.FragmentPictureBinding
import ru.gb.pictureoftheday.viewmodel.AppState
import ru.gb.pictureoftheday.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

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
        viewModel.sendRequest()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.Success -> {
                binding.ImageView.load(appState.pictureOfTheDayResponseData.url)
            }
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