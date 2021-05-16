package dev.danilo.maders.feature.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danilo.maders.feature.products.repository.ProductRepository
import dev.danilo.maders.model.Portion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import dev.danilo.maders.extension.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class ProductViewModel(private val repository: ProductRepository): ViewModel() {

    private val _product = MutableLiveData<Result<List<Portion>>>()
    val product: LiveData<Result<List<Portion>>> get() = _product

    fun fetchPortion() = viewModelScope.launch {
        repository.fetchProducts()
            .onStart { _product.postValue(Result.Loading) }
            .catch { _product.postValue(Result.Error(it)) }
            .collect { _product.postValue(Result.Success(it)) }
    }
}
