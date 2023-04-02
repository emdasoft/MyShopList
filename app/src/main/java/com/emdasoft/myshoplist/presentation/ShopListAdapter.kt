package com.emdasoft.myshoplist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.myshoplist.R
import com.emdasoft.myshoplist.databinding.ShopItemEnabledBinding
import com.emdasoft.myshoplist.domain.entity.ShopItem

class ShopListAdapter(val listener: SetOnClickListener) :
    RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ShopItemEnabledBinding.bind(itemView)

        fun binItem(shopItem: ShopItem, listener: SetOnClickListener) = with(binding) {
            tvItemName.text = shopItem.name
            tvItemCount.text = shopItem.count.toString()
            itemView.setOnClickListener {
                listener.onClickListener(shopItem)
            }
            itemView.setOnLongClickListener {
                listener.onLongClickListener(shopItem)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.shop_item_enabled,
                parent,
                false
            )
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.binItem(shopList[position], listener)
    }

    interface SetOnClickListener {
        fun onClickListener(shopItem: ShopItem)
        fun onLongClickListener(shopItem: ShopItem)
    }
}