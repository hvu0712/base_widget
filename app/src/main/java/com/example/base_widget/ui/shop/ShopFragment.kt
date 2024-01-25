package com.example.base_widget.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.base_widget.databinding.FragmentShopBinding
import com.example.base_widget.utils.BaseConfig

class ShopFragment : Fragment() {

    private var shopAdapter: ShopAdapter = ShopAdapter()
    private var listener: ShopFragmentListener? = null

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
                    shopAdapter.setData(BaseConfig.getItemPetShop())
                    adapter = shopAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                    addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
                }
                shopAdapter.onItemClick = {it, pos ->
                    listener?.setOnClickListener(it,pos)
                }
            }
            else -> {
                val spacingInPixelsOne = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._26sdp)
                val spacingInPixelsTwo = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._54sdp)
                val spacingInPixelsThree = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
                binding.rvAllShop.apply {
                    shopAdapter.setData(BaseConfig.getItemPlantShop())
                    adapter = shopAdapter
                    layoutManager = GridLayoutManager(requireContext(), 3)
                    addItemDecoration(GridSpacingItemDecoration(3,spacingInPixelsOne, spacingInPixelsTwo, spacingInPixelsThree))
                }
                shopAdapter.onItemClick = {it, pos ->
                    listener?.setOnClickListener(it,pos)
                }
            }
        }
        return binding.root
    }

    fun setListener(listener: ShopFragmentListener) {
        this.listener = listener
    }
}

interface ShopFragmentListener {
    fun setOnClickListener(it: ItemTraining,pos: Int)
}