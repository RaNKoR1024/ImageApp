package com.example.imageapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imageapp.ImageApp
import com.example.imageapp.data.remote.serp.ImageListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val imageApp = application as ImageApp

    private var _imageList = MutableLiveData<ImageListResponse>()
    val imageList: LiveData<ImageListResponse>
        get() = _imageList

    var currentPosition = 0

    fun searchImages(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = imageApp.serpApi.getImagesList(name).awaitResponse()
            if (response.isSuccessful) {
                _imageList.postValue(response.body()!!)
            } else {
                println(response.errorBody())
            }
        }
    }
}