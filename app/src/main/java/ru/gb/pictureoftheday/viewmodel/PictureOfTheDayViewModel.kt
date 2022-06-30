package ru.gb.pictureoftheday.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.pictureoftheday.BuildConfig
import ru.gb.pictureoftheday.model.PictureOfTheDayResponseData
import ru.gb.pictureoftheday.model.RepositoryImpl

class PictureOfTheDayViewModel(private val liveData: MutableLiveData<AppState>, private val repositoryImpl: RepositoryImpl = RepositoryImpl()):ViewModel() {

    fun getLiveData():MutableLiveData<AppState>{
        return liveData
    }

    fun sendRequest(){
        repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    val callback = object : Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful){
                liveData.postValue(AppState.Success(response.body()!!))
            }else{
                liveData.postValue(AppState.Error(throw IllegalStateException("Ой, ой, ой!")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
}