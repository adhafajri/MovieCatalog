package com.adhafajri.moviecatalog.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val idlingResource = CountingIdlingResource(Constant.RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}
