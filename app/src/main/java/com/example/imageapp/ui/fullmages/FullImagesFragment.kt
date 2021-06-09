package com.example.imageapp.ui.fullmages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.imageapp.R
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

        adapter = ViewPagerAdapter(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.error
            )!!
        )

        with(binding) {
            vpImages.adapter = adapter

            val mainActivity = activity as MainActivity
            mainActivity.viewModel.imageList.observe(viewLifecycleOwner, {
                adapter.setData(it.images)
                vpImages.currentItem = mainActivity.viewModel.currentPosition
            })
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