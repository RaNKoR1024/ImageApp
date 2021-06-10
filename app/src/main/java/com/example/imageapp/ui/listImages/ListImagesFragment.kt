package com.example.imageapp.ui.listImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentListImagesBinding
import com.example.imageapp.ui.MainActivity


class ListImagesFragment : Fragment() {

    private lateinit var binding: FragmentListImagesBinding
    private lateinit var adapter: ListImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val mainActivity = requireActivity() as MainActivity

            adapter = ListImagesAdapter {
                mainActivity.viewModel.currentPosition = it
                findNavController().navigate(R.id.action_listImagesFragment_to_fullImagesFragment)
            }
            rvImages.layoutManager = GridLayoutManager(context, 4)
            rvImages.adapter = adapter

            mainActivity.viewModel.imageThumbnailList.observe(viewLifecycleOwner, {
                adapter.setData(it)
                mainActivity.setLoading(false)
            })
        }
    }
}