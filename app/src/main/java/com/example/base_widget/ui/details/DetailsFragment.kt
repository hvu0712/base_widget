package com.example.base_widget.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.custom.CreatedListener
import com.example.base_widget.databinding.FragmentDetailsBinding
import com.example.base_widget.ui.shop.ItemCommon
import com.example.base_widget.utils.AppUtils

class DetailsFragment : Fragment() {

    private var detailsAdapter: DetailsAdapter = DetailsAdapter()
    private var listener: DetailsFragmentListener? = null

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
                detailsAdapter.onItemClick = { itemCommon, position ->
                    listener?.onDetailsItemSelected(itemCommon, position)
                }
            }

            1 -> {
                binding.rvDetails.apply {
                    detailsAdapter.setData(AppUtils.getItemPetToilet())
                    adapter = detailsAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                }
                detailsAdapter.onItemClick = { itemCommon, position ->
                    listener?.onDetailsItemSelected(itemCommon, position)
                }
            }

            else -> {
                binding.rvDetails.apply {
                    detailsAdapter.setData(AppUtils.getItemPetSleep())
                    adapter = detailsAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                }
                detailsAdapter.onItemClick = { itemCommon, position ->
                    listener?.onDetailsItemSelected(itemCommon, position)
                }
            }
        }
        return binding.root
    }

    fun setListener(listener: DetailsFragmentListener) {
        this.listener = listener
    }
}

interface DetailsFragmentListener {
    fun onDetailsItemSelected(itemCommon: ItemCommon, position: Int)
}