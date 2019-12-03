package by.wolearn.learning.model.repositories

import by.wolearn.core.view.entities.Resource
import by.wolearn.learning.model.entities.Category
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class CategoriesRepository(private val api: WolearnApi) {

    suspend fun getCategories(): Resource<List<Category>> = safeApiCall { api.getCategories() }

    suspend fun updateCategory(category: Category) =
        safeApiCall { api.setCategories(listOf(category)) }
}