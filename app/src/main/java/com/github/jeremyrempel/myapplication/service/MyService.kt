package com.github.jeremyrempel.myapplication.service

import javax.inject.Inject

/**
 * async service with callback
 */
class MyService @Inject constructor() {
    fun getData(onComplete: (String) -> Unit, onError: (Throwable) -> Unit) {
        return onComplete("Hello From Service")
    }
}