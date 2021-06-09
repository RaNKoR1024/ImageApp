package com.example.imageapp.ui.fullmages

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.databinding.ItemVpImageBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    private var dataSet = emptyList<Bitmap>()

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
        holder.binding.vpImage.setImageBitmap(currentItem)
    }

    fun setData(newDataSet: List<Bitmap>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }


}