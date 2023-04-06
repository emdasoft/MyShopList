package com.emdasoft.myshoplist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.emdasoft.myshoplist.data.database.AppDatabase
import com.emdasoft.myshoplist.data.mapper.Mapper
import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.repository.Repository

class RepositoryImpl(application: Application) : Repository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = Mapper()


    override fun getShopList(): LiveData<List<ShopItem>> {
        return MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDao.getShopItemList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(itemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(shopListDao.getShopItem(itemId))
    }

}