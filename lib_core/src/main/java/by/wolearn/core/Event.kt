package by.wolearn.core

class Event<T>(private val value: T) {

    private var isSignaled: Boolean = false

    val content: T?
        get() {
            if (isSignaled) return null
            isSignaled = true
            return value
        }
}