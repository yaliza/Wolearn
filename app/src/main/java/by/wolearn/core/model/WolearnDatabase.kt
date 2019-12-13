package by.wolearn.core.model

import androidx.room.Database
import androidx.room.RoomDatabase
import by.wolearn.learning.model.dao.CategoryDao
import by.wolearn.learning.model.entities.Category


@Database(entities = [Category::class], version = 1)
abstract class WolearnDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}