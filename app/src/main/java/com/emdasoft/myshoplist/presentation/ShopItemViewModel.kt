package com.emdasoft.myshoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.myshoplist.data.RepositoryImpl
import com.emdasoft.myshoplist.domain.entity.ShopItem
import com.emdasoft.myshoplist.domain.usecases.AddShopItemUseCase
import com.emdasoft.myshoplist.domain.usecases.EditShopItemUseCase
import com.emdasoft.myshoplist.domain.usecases.GetShopItemUseCase

class ShopItemViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val _shouldCloseScreen = MutableLiveData<Boolean>()
    val shouldCloseScreen: LiveData<Boolean>
        get() = _shouldCloseScreen

    private val _shopItemLD = MutableLiveData<ShopItem>()
    val shopItemLD: LiveData<ShopItem>
        get() = _shopItemLD

    private val _showErrorNameInput = MutableLiveData<Boolean>()
    val showErrorNameInput: LiveData<Boolean>
        get() = _showErrorNameInput

    private val _showErrorCountInput = MutableLiveData<Boolean>()
    val showErrorCountInput: LiveData<Boolean>
        get() = _showErrorCountInput

    fun addShopItem(nameInput: String?, countInput: String?) {
        val name = parseNameInput(nameInput)
        val count = parseCountInput(countInput)
        val isValid = validateInput(name, count)
        if (isValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase(shopItem)
            _shouldCloseScreen.value = true
        }
    }

    fun editShopItem(nameInput: String?, countInput: String?) {
        val name = parseNameInput(nameInput)
        val count = parseCountInput(countInput)
        val isValid = validateInput(name, count)
        if (isValid) {
            _shopItemLD.value?.let {
                val newItem = it.copy(name = name, count = count)
                editShopItemUseCase(newItem)
                _shouldCloseScreen.value = true
            }
        }
    }

    fun getShopItem(itemId: Int) {
        val item = getShopItemUseCase(itemId)
        _shopItemLD.value = item
    }

    private fun parseNameInput(nameInput: String?): String {
        return nameInput?.trim() ?: ""
    }

    private fun parseCountInput(countInput: String?): Int {
        return try {
            countInput?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var valid = true
        if (name.isEmpty()) {
            valid = false
            _showErrorNameInput.value = true
        }
        if (count <= 0) {
            valid = false
            _showErrorCountInput.value = true
        }
        return valid
    }

    fun resetErrorNameInput() {
        _showErrorNameInput.value = false
    }

    fun resetErrorCountInput() {
        _showErrorCountInput.value = false
    }
}
