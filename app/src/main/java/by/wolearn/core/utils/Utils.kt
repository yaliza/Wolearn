package by.wolearn.core.utils

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty


fun <T> didSet(initialValue: T, didSet: () -> Unit) = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = didSet()
}