package by.wolearn.learning.model.repositories

import by.wolearn.learning.model.entities.Category
import by.wolearn.network.WolearnApi


class CategoriesRepository(private val api: WolearnApi) {

    suspend fun getCategories(): List<Category> = api.getCategories()

}