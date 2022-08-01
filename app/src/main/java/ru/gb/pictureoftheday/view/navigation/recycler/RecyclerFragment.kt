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
    val data = arrayListOf(
        Pair(Data("Заголовок", type = TYPE_HEADER), false),
        Pair(Data("Earth", type = TYPE_EARTH), false),
        Pair(Data("Earth", type = TYPE_EARTH), false),
        Pair(Data("Mars", type = TYPE_MARS), false),
        Pair(Data("Earth", type = TYPE_EARTH), false),
        Pair(Data("Earth", type = TYPE_EARTH), false),
        Pair(Data("Earth", type = TYPE_EARTH), false),
        Pair(Data("Mars", type = TYPE_MARS), false)
    )
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=  RecyclerAdapter(data,callbackAdd,callbackRemove)
        binding.recyclerView.adapter =adapter
    }

    private val callbackAdd = AddItem {
        data.add(it, Pair(Data("Mars(New)", type= TYPE_MARS),false))
        adapter.setListDataAdd(data,it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data,it)
    }
}