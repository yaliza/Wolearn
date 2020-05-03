package by.wolearn.chooser.data

import by.wolearn.chooser.backend.CategoriesApi
import by.wolearn.chooser.backend.entities.CategoryResponse
import by.wolearn.core.Resource
import by.wolearn.core.safeApiCall
import by.wolearn.core.ui.entities.Category

class CategoriesRepository(
    val api: CategoriesApi
) {

    suspend fun getCategories(): Resource<List<Category>> {
        return safeApiCall { api.getCategories().map { Category(it) } }
    }
}