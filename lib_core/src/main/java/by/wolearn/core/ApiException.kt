package by.wolearn.core

import java.lang.Exception


class ApiException(
    val code: String,
    override val message: String
) : Exception()