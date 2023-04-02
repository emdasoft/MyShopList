package com.emdasoft.myshoplist.domain.usecases

import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.repository.Repository

class DeleteShopItemUseCase(private val repository: Repository) {

    operator fun invoke(shopItem: ShopItem) {
        repository.deleteShopItem(shopItem)
    }

}