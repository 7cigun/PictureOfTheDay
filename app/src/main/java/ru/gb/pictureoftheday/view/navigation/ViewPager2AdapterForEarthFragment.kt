package ru.gb.pictureoftheday.view.navigation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.gb.pictureoftheday.view.picture.PictureOfTheDayFragment

class ViewPager2AdapterForEarthFragment(fr: Fragment) : FragmentStateAdapter(fr) {

    private val fragments = arrayOf(PictureOfTheDayFragment.newInstance(), PictureOfTheDayFragment.newInstance(), PictureOfTheDayFragment.newInstance())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}