package com.example.base_widget.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.databinding.FragmentDetailsBinding
import com.example.base_widget.utils.AppUtils

class DetailsFragment : Fragment() {

    private var detailsAdapter: DetailsAdapter = DetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        when (arguments?.getInt("page", 0) ?: 0) {
            0 -> {
                binding.rvDetails.apply {
                    detailsAdapter.setData(AppUtils.getItemPetFood())
                    adapter = detailsAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }

            1 -> {
                binding.rvDetails.apply {
                    detailsAdapter.setData(AppUtils.getItemPetToilet())
                    adapter = detailsAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }

            else -> {
                binding.rvDetails.apply {
                    detailsAdapter.setData(AppUtils.getItemPetSleep())
                    adapter = detailsAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }
        }
        return binding.root
    }
}