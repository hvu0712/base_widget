package com.example.base_widget.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.databinding.FragmentShopBinding
import com.example.base_widget.utils.AppUtils

class ShopFragment : Fragment() {

    private var shopAdapter: ShopAdapter = ShopAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShopBinding.inflate(inflater, container, false)
        when (arguments?.getInt("page", 0) ?: 0) {
            0 -> {
                val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._26sdp)
                val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._54sdp)
                val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
                binding.rvAllShop.apply {
                    shopAdapter.setData(AppUtils.getItemPetShop())
                    adapter = shopAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                    addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
                }
            }
            else -> {
                val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._26sdp)
                val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._54sdp)
                val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
                binding.rvAllShop.apply {
                    shopAdapter.setData(AppUtils.getItemPlantShop())
                    adapter = shopAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                    addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
                }
            }
        }
        return binding.root
    }
}