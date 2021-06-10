package com.example.imageapp.ui.listImages

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.databinding.ItemImageBinding

class ListImagesAdapter(private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ListImagesAdapter.ImageViewHolder>() {

    private var dataSet = emptyList<Bitmap>()

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

        holder.binding.ivImage.setImageBitmap(currentItem)
        holder.itemView.setOnClickListener {
            println(position)
            onClick.invoke(position)
        }

    }

    fun setData(newDataSet: List<Bitmap>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

}