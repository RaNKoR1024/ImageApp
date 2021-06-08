package com.example.imageapp.ui.listImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageapp.databinding.FragmentListImagesBinding
import com.example.imageapp.ui.MainActivity


class ListImagesFragment : Fragment() {

    private lateinit var binding: FragmentListImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity
        binding.rvImages.layoutManager = GridLayoutManager(context, 3)
        binding.rvImages.adapter


    }

}