package com.emdasoft.myshoplist.domain.repository

import androidx.lifecycle.LiveData
import com.emdasoft.myshoplist.domain.entity.ShopItem

interface Repository {

    fun getShopList(): LiveData<List<ShopItem>>

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(itemId: Int): ShopItem
}