package com.example.imageapp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.imageapp.ImageApp
import com.example.imageapp.data.remote.serp.SerpImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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

    private var job: Job? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    fun searchImages(name: String) {
        if (job != null && job!!.isActive) {
            job!!.cancel()
            System.gc()
        }
        job = viewModelScope.launch(Dispatchers.Default) {
            _imageFullList.postValue(emptyList())
            val response = imageApp.serpApi.getImagesList(name).awaitResponse()
            if (response.isSuccessful) {
                listImageInfo = response.body()!!.images

                val listTImages = List(listImageInfo.size) {
                    Glide.with(imageApp)
                        .asBitmap()
                        .load(listImageInfo[it].thumbnail)
                        .submit()
                        .get()
                }
                _imageThumbnailList.postValue(listTImages)
//                val listFImages = mutableListOf<Bitmap>()
//                listImageInfo.forEach {
//                    val bitmap = try {
//                        GlideApp.with(imageApp)
//                            .asBitmap()
//                            .load(it.original)
//                            .submit()
//                            .get()
//                    } catch (e: Exception) {
//                        listTImages[it.position - 1]
//                    }
//                    listFImages.add(bitmap)
//                    _imageFullList.postValue(listFImages)
//                }
            } else {
                println(response.errorBody())
            }
        }
    }
}