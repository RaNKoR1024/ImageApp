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

    private var count = 0

    @SuppressLint("UseCompatLoadingForDrawables")
    fun searchImages(name: String) {
        count += 1
        viewModelScope.launch(Dispatchers.Default) {
            _imageFullList.postValue(emptyList())
            val response = imageApp.serpApi.getImagesList(name).awaitResponse()
            if (response.isSuccessful) {
                listImageInfo = response.body()!!.images
                val coroutineCount = count
                val listTImages = List(listImageInfo.size) {
                    GlideApp.with(imageApp)
                        .asBitmap()
                        .load(listImageInfo[it].thumbnail)
                        .submit()
                        .get()
                }
                if (coroutineCount == count) {
                    _imageThumbnailList.postValue(listTImages)
                }
                val listFImages = mutableListOf<Bitmap>()

                listImageInfo.forEach {
                    if (coroutineCount == count) {
                        val bitmap = try {
                            GlideApp.with(imageApp)
                                .asBitmap()
                                .override(512, 512)
                                .centerInside()
                                .load(it.original)
                                .submit()
                                .get()
                        } catch (e: Exception) {
                            listTImages[it.position - 1]
                        }
                        println(it.position)
                        listFImages.add(bitmap)
                        _imageFullList.postValue(listFImages)
                    }
                }
            } else {
                println(response.errorBody())
            }
        }
    }
}