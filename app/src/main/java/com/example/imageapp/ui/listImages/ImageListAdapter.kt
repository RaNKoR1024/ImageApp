package com.example.imageapp.ui.listImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.annotation.GlideModule
import com.example.imageapp.data.remote.serp.SerpImage
import com.example.imageapp.databinding.ItemImageBinding

class ImageListAdapter (
    private val onClick: (SerpImage) -> Unit,
): RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private var dataSet = mutableListOf<SerpImage>()

    class ImageViewHolder(binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

    }

}