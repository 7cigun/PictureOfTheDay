package ru.gb.pictureoftheday.view.navigation.recycler

const val TYPE_EARTH= 1
const val TYPE_MARS= 2
const val TYPE_HEADER= 3
data class Data(val name: String = "Name", val someDescription: String? = "Description", val type: Int = TYPE_MARS)