package by.wolearn.core

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty


fun <T> didSet(initialValue: T, didSet: () -> Unit) = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = didSet()
}

fun <T> didSetWithValue(initialValue: T, didSet: (newValue: T) -> Unit) = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = didSet(newValue)
}