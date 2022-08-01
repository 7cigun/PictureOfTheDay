package ru.gb.pictureoftheday.view.navigation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.pictureoftheday.databinding.FragmentRecyclerItemEarthBinding
import ru.gb.pictureoftheday.databinding.FragmentRecyclerItemHeaderBinding
import ru.gb.pictureoftheday.databinding.FragmentRecyclerItemMarsBinding

class RecyclerAdapter(private val listData: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding)
            }
            else -> {
                val binding =
                    FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(binding: FragmentRecyclerItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class EarthViewHolder(binding: FragmentRecyclerItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class MarsViewHolder(binding: FragmentRecyclerItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}