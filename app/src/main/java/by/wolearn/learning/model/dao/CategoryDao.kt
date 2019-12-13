package by.wolearn.learning.model.dao

import androidx.room.*
import by.wolearn.learning.model.entities.Category


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(categories: List<Category>)

    @Query("SELECT * FROM Category")
    suspend fun read(): List<Category>

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(categories: List<Category>)

}