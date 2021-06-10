package com.example.imageapp.ui.fullmages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.imageapp.databinding.FragmentFullImagesBinding
import com.example.imageapp.ui.MainActivity

class FullImagesFragment : Fragment() {

    private lateinit var binding: FragmentFullImagesBinding
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ViewPagerAdapter()

        with(binding) {
            vpImages.adapter = adapter

            val mainActivity = activity as MainActivity
            with(mainActivity) {
                setLoading(true)
                viewModel.imageThumbnailList.observe(viewLifecycleOwner, {
                    adapter.setData(it)
                    vpImages.setCurrentItem(mainActivity.viewModel.currentPosition, false)
                    setLoading(false)
                })
                viewModel.imageFullList.observe(viewLifecycleOwner, {
                    adapter.setUpdatedData(it)
                })
            }
            vpImages.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        mainActivity.viewModel.currentPosition = position
                        super.onPageSelected(position)
                    }
                }
            )
        }


    }

}