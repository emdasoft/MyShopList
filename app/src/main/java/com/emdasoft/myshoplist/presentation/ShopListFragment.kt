package com.emdasoft.myshoplist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.myshoplist.databinding.FragmentShopListBinding
import com.emdasoft.myshoplist.domain.entity.ShopItem

class ShopListFragment : Fragment(), ShopListAdapter.SetOnClickListener {

    private var _binding: FragmentShopListBinding? = null
    private val binding: FragmentShopListBinding
        get() = _binding ?: throw RuntimeException("FragmentShopListBinding = null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var rvAdapter: ShopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.shopList.observe(viewLifecycleOwner) {
            rvAdapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvShopList.layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            rvAdapter = ShopListAdapter(this@ShopListFragment)
            rvShopList.adapter = rvAdapter
            onSwipeListener()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = ShopListFragment()
    }

    override fun onClickListener(shopItem: ShopItem) {
        Toast.makeText(requireContext(), shopItem.name, Toast.LENGTH_SHORT).show()
    }

    override fun onLongClickListener(shopItem: ShopItem) {

    }

    private fun onSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = rvAdapter.shopList[viewHolder.layoutPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)
    }
}