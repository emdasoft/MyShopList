package com.emdasoft.myshoplist.domain.usecases

import com.emdasoft.myshoplist.domain.repository.Repository

class GetShopListUseCase(private val repository: Repository) {

    operator fun invoke() = repository.getShopList()

}