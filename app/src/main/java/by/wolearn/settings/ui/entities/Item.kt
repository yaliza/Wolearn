package by.wolearn.settings.ui.entities

sealed class Item {

    class Label(val label: Int) : Item()
    class Section(val id: String, val items: List<Button>) : Item()
    class Button(val id: String, val text: Int, val image: Int) : Item()

}