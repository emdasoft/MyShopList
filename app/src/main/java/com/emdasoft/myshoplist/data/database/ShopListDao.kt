package com.emdasoft.myshoplist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItem: ShopItemDbModel)

    @Delete
    suspend fun deleteShopItem(shopItem: ShopItemDbModel)

    @Query("SELECT * FROM shop_items")
    fun getShopItemList(): LiveData<List<ShopItemDbModel>>

    @Query("SELECT * FROM shop_items WHERE id=:itemId LIMIT 1")
    suspend fun getShopItem(itemId: Int): ShopItemDbModel

}