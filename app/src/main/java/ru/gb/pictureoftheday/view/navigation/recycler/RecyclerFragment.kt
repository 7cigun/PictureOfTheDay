package ru.gb.pictureoftheday.view.navigation.recycler

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.gb.pictureoftheday.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf(
            Data("Заголовок",type= TYPE_HEADER),
            Data("Earth",type=TYPE_EARTH),
            Data("Earth",type=TYPE_EARTH),
            Data("Mars", type= TYPE_MARS),
            Data("Earth",type=TYPE_EARTH),
            Data("Earth",type=TYPE_EARTH),
            Data("Earth",type=TYPE_EARTH),
            Data("Mars", type=TYPE_MARS)
        )
        binding.recyclerView.adapter = RecyclerAdapter(data)
    }
}