package com.emdasoft.myshoplist.data.mapper

import com.emdasoft.myshoplist.data.database.ShopItemDbModel
import com.emdasoft.myshoplist.domain.entity.ShopItem

class Mapper {

    fun mapDbModelToEntity(dbModel: ShopItemDbModel) = ShopItem(
        name = dbModel.name,
        count = dbModel.count,
        enabled = dbModel.enabled,
        id = dbModel.id
    )

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}