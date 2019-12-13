package by.wolearn.learning.model.repositories

import by.wolearn.core.model.CategoriesStorage
import by.wolearn.core.view.entities.Resource
import by.wolearn.learning.model.entities.Category
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall
import kotlinx.coroutines.flow.flow


class CategoriesRepository(
    private val api: WolearnApi,
    private val categoriesStorage: CategoriesStorage
) {

    suspend fun getCategories() = flow<Resource<List<Category>>> {
        val categories = categoriesStorage.get()
        if (categories != null) {
            emit(Resource.Success(categories))
            return@flow
        }
        emit(Resource.Loading())
        val resp = safeApiCall { api.getCategories() }
        if (resp is Resource.Success && resp.data != null) {
            categoriesStorage.store(resp.data)
        }
        emit(resp)
    }

    suspend fun updateCategory(category: Category) =
        safeApiCall { api.setCategories(listOf(category)) }
}