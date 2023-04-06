package com.emdasoft.myshoplist.domain.usecases

import com.emdasoft.myshoplist.domain.repository.Repository

class GetShopItemUseCase(private val repository: Repository) {

    suspend operator fun invoke(itemId: Int) = repository.getShopItem(itemId)

}