package com.emdasoft.myshoplist.domain.usecases

import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.repository.Repository

class EditShopItemUseCase(private val repository: Repository) {

    suspend operator fun invoke(shopItem: ShopItem) {
        repository.editShopItem(shopItem)
    }

}