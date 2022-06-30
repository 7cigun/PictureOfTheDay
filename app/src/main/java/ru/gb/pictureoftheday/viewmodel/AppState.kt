package ru.gb.pictureoftheday.viewmodel

import ru.gb.pictureoftheday.model.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}