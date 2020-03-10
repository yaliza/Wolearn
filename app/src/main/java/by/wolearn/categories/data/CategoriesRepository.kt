package by.wolearn.categories.data

import by.wolearn.categories.backend.CategoriesApi
import by.wolearn.categories.backend.entities.Category
import by.wolearn.core.utils.safeApiCall


class CategoriesRepository(private val api: CategoriesApi) {

    suspend fun getCategories() =
        safeApiCall { api.getCategories() }

    suspend fun updateCategory(category: Category) =
        safeApiCall { api.setCategories(listOf(category)) }
}