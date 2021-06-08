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

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val imageApp = application as ImageApp

    private var _imageList = MutableLiveData<ImageListResponse>()
    val imageList: LiveData<ImageListResponse>
        get() = _imageList

    fun searchImages(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            imageApp.serpApi.getImagesList(name)
        }
    }
}