package by.wolearn.addword.ui.entities

import androidx.annotation.StringRes

sealed class Item {

    data class Title(val id: String, @StringRes val value: Int) : Item()
    data class Submit(val id: String) : Item()
    data class ButtonAddExample(val id: String) : Item()

    data class Input(
        val id: String,
        val value: String, @StringRes val hint: Int,
        val error: String? = null
    ) : Item()

    data class Example(
        val id: String,
        val value: String,
        val num: Int,
        val isDeletable: Boolean = false,
        val error: String? = null
    ) : Item()

    data class QuizOption(
        val id: String,
        val position: Int,
        var isRight: Boolean = false,
        val value: String = "",
        val error: String? = null
    ) : Item()

    data class Select(
        val id: String, @StringRes val hint: Int,
        val items: List<String>,
        val value: String? = null,
        val error: String? = null
    ) : Item()

    data class Category(
        val id: String,
        val value: by.wolearn.core.ui.entities.Category? = null,
        val error: String? = null
    ) : Item()

}