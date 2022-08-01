package ru.gb.pictureoftheday.view.navigation.recycler

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onItemSelect()
    fun onItemClear()
}

interface ItemTouchHelperViewHolder {
    fun onItemSelect()
    fun onItemClear()
}