package dev.danilo.maders.feature.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danilo.maders.DatabaseManager
import dev.danilo.maders.feature.products.repository.ProductRepository
import dev.danilo.maders.model.Portion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import dev.danilo.maders.extension.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class ProductViewModel(
    private val repository: ProductRepository,
    private val databaseManager: DatabaseManager,
) : ViewModel() {

    private val _product = MutableLiveData<Result<List<Portion>>>()
    val product: LiveData<Result<List<Portion>>> get() = _product

    fun fetchPortion() = viewModelScope.launch {
        repository.fetchProducts()
            .onStart { _product.postValue(Result.Loading) }
            .catch {
                withContext(Dispatchers.IO) {
                    val products = databaseManager.getPortionDAO().findAll()
                    if (products.isEmpty()) {
                        _product.postValue(Result.Error(it))
                    } else {
                        _product.postValue(Result.Success(products))
                    }
                }
            }
            .collect { _product.postValue(Result.Success(it)) }
    }
}
