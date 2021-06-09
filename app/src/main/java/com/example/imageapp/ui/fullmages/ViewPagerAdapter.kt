package com.example.imageapp.ui.fullmages

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.GlideApp
import com.example.imageapp.R
import com.example.imageapp.data.remote.serp.SerpImage
import com.example.imageapp.databinding.ItemVpImageBinding

class ViewPagerAdapter(private val errorDrawable: Drawable) :
    RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    private var dataSet = emptyList<SerpImage>()

    inner class ImageViewHolder(val binding: ItemVpImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVpImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewPagerAdapter.ImageViewHolder, position: Int) {
        val currentItem = dataSet[position]
        GlideApp.with(holder.itemView)
            .load(currentItem.original)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(holder.binding.vpImage)
            .onLoadFailed(errorDrawable)
    }

    fun setData(newDataSet: List<SerpImage>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }


}