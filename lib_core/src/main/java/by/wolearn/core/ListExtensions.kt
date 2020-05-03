package by.wolearn.core


fun <T> MutableList<T>.replace(oldValue: T, newValue: T) {
    val index = indexOfLast { it == oldValue }
    if (index == -1) throw Exception("Old value not found")
    removeAt(index)
    add(index, newValue)
}