package com.emdasoft.myshoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.repository.Repository
import kotlin.random.Random

object RepositoryImpl : Repository {

    private val shopList = mutableListOf<ShopItem>()
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    init {
        for (item in 0..20) {
            addShopItem(ShopItem("$item", item, Random.nextBoolean()))
        }
    }


    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        deleteShopItem(oldItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(itemId: Int): ShopItem {
        return shopList.find { it.id == itemId }
            ?: throw RuntimeException("ShopItem with id: $itemId not found!")
    }

    private fun updateList() {
        shopListLD.value = shopList.toList().sortedBy { it.id }
    }

    private const val UNDEFINED_ID = -1
}