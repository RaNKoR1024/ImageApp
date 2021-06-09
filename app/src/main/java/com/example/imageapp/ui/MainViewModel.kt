package com.example.imageapp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imageapp.GlideApp
import com.example.imageapp.ImageApp
import com.example.imageapp.R
import com.example.imageapp.data.remote.serp.SerpImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val imageApp = application as ImageApp

    var listImageInfo = emptyList<SerpImage>()

    private var _imageThumbnailList = MutableLiveData<List<Bitmap>>()
    val imageThumbnailList: LiveData<List<Bitmap>>
        get() = _imageThumbnailList

    private var _imageFullList = MutableLiveData<List<Bitmap>>()
    val imageFullList: LiveData<List<Bitmap>>
        get() = _imageFullList

    var currentPosition = 0

    @SuppressLint("UseCompatLoadingForDrawables")
    fun searchImages(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = imageApp.serpApi.getImagesList(name).awaitResponse()
            if (response.isSuccessful) {
                listImageInfo = response.body()!!.images
                val listTImages = List(listImageInfo.size) {
                    GlideApp.with(imageApp)
                        .asBitmap()
                        .load(listImageInfo[it].thumbnail)
                        .submit()
                        .get()
                }
                _imageThumbnailList.postValue(listTImages)
                val listFImages = List(listImageInfo.size) {
                    GlideApp.with(imageApp)
                        .asBitmap()
                        .error(imageApp.getDrawable(R.drawable.error))
                        .load(listImageInfo[it].original)
                        .submit()
                        .get()
                }
                _imageFullList.postValue(listFImages)

            } else {
                println(response.errorBody())
            }
        }
    }
}