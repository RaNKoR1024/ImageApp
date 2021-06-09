package com.example.imageapp.ui.imagesource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imageapp.databinding.FragmentImageSourceBinding
import com.example.imageapp.ui.MainActivity

class ImageSourceFragment : Fragment() {

    private lateinit var binding: FragmentImageSourceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageSourceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        with(mainActivity.viewModel) {
            binding.wvImage.loadUrl(listImageInfo[currentPosition].link)
        }

    }

}