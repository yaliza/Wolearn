package by.wolearn.categories.data

import by.wolearn.core.view.entities.Resource
import by.wolearn.network.safeApiCall


class CategoriesRepository(private val api: CategoriesApi) {

    suspend fun getCategories() : Resource<List<Category>> {
        return safeApiCall { api.getCategories() }
    }

    suspend fun updateCategory(category: Category) =
        safeApiCall { api.setCategories(listOf(category)) }
}