package com.emdasoft.myshoplist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emdasoft.myshoplist.databinding.FragmentShopItemBinding

class ShopItemFragment : Fragment() {

    private var itemId = UNDEFINE_ID
    private var screenMode = ""

    private val viewModel by lazy {
        ViewModelProvider(this)[ShopItemViewModel::class.java]
    }

    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchRightMode()

        viewModelObserve()

    }

    private fun viewModelObserve() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addShopItem(binding.teName.text?.toString(), binding.teCount.text?.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(itemId)

        viewModel.shopItemLD.observe(viewLifecycleOwner) {
            binding.teName.setText(it.name)
            binding.teCount.setText(it.count.toString())
        }

        binding.saveButton.setOnClickListener {
            viewModel.editShopItem(
                binding.teName.text?.toString(),
                binding.teCount.text?.toString()
            )
        }
    }

    private fun parseArgs() {
        screenMode = requireArguments().getString(SCREEN_MODE)
            ?: throw RuntimeException("Unknown Screen mode")
        if (screenMode == MODE_EDIT) {
            itemId = requireArguments().getInt(ITEM_ID_KEY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val UNDEFINE_ID = -1

        private const val ITEM_ID_KEY = "item_id"
        private const val SCREEN_MODE = "screen_mode"
        private const val MODE_ADD = "add_mode"
        private const val MODE_EDIT = "edit_mode"

        @JvmStatic
        fun newInstanceAddMode(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        @JvmStatic
        fun newInstanceEditMode(itemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID_KEY, itemId)
                }
            }
        }
    }

}