package com.example.imageapp.ui.listImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.GlideApp
import com.example.imageapp.R
import com.example.imageapp.data.remote.serp.SerpImage
import com.example.imageapp.databinding.ItemImageBinding

class ListImagesAdapter(private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListImagesAdapter.ImageViewHolder>() {

    private var dataSet = emptyList<SerpImage>()

    inner class ImageViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = dataSet[position]
        GlideApp.with(holder.itemView)
            .load(currentItem.thumbnail)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(holder.binding.ivImage)
        holder.itemView.setOnClickListener {
            onClick.invoke(position)
        }
    }

    fun setData(newDataSet: List<SerpImage>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

}