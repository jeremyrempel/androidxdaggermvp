package com.github.jeremyrempel.myapplication.service

import javax.inject.Inject

class MyService @Inject constructor() {
    fun getData(onComplete: (String) -> Unit, onError: (Throwable) -> Unit) {
        // getting data
        return onComplete.invoke("Hello From Service")
    }
}