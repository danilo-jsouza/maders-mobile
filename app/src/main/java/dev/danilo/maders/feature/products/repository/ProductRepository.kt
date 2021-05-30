package dev.danilo.maders.feature.products.repository

import dev.danilo.maders.DatabaseManager
import dev.danilo.maders.model.Portion
import dev.danilo.maders.service.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepository(
    private val apiService: ApiService,
    private val databaseManager: DatabaseManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun fetchProducts(): Flow<List<Portion>> = flow {
        val portions = apiService.getPortion()
        portions.forEach { databaseManager.getPortionDAO().insert(it) }
        emit(portions)
    }.flowOn(dispatcher)
}
