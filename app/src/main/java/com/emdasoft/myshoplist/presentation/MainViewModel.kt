package com.emdasoft.myshoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.myshoplist.data.RepositoryImpl
import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.usecases.DeleteShopItemUseCase
import com.emdasoft.myshoplist.domain.usecases.EditShopItemUseCase
import com.emdasoft.myshoplist.domain.usecases.GetShopListUseCase

class MainViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)


    private val _shopList = getShopListUseCase()
    val shopList: LiveData<List<ShopItem>>
        get() = _shopList

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase(shopItem)
    }

    fun changeEnabled(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase(newItem)
    }
}