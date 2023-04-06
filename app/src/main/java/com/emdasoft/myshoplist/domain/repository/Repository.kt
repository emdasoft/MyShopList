package com.emdasoft.myshoplist.domain.repository

import androidx.lifecycle.LiveData
import com.emdasoft.myshoplist.domain.entity.ShopItem

interface Repository {

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(itemId: Int): ShopItem

}